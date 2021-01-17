package casia.isiteam.sensitivedetection.controller;

import casia.isiteam.sensitivedetection.common.lang.Result;
import casia.isiteam.sensitivedetection.algorithm.StringSearch;
import casia.isiteam.sensitivedetection.model.SensitiveWord;
import casia.isiteam.sensitivedetection.model.Sensitivity;
import casia.isiteam.sensitivedetection.service.SensitiveWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@Api(tags = "文本敏感度接口")
public class IndexController {

    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    SensitiveWordService sensitiveWordService;

    @PostMapping("/sensitive")
    @ApiOperation("敏感度检测与计算（非跳词版）")
    public Result index(@RequestParam("content") String content) {

        logger.info("============== Request Incoming！！ ==============");

        // 获取敏感词
        List<SensitiveWord> allKeywords = sensitiveWordService.findAllKeywords();
        // 初始化检测器
        StringSearch searcher = new StringSearch();
        searcher.SetKeywords(allKeywords.stream().map(SensitiveWord::getKeyword).collect(Collectors.toList()));
        // 检测敏感词
        List<String> words = searcher.FindAll(content);
        String replace = searcher.Replace(content);
        // 获取最大敏感级别对应值
        List<SensitiveWord> keywords = allKeywords.stream().filter(word -> words.contains(word.getKeyword())).collect(Collectors.toList());
        double maxSensitiveVal = keywords.stream().mapToDouble(SensitiveWord::getVal).max().orElse(0.0);
        // 统计 * 字符的占比
        double ratio = count_star(replace) / content.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "").length();
        // 计算敏感值（ 最大敏感值 * 85% + 占比 * 15%）,保留两位小数
        double result = (double) Math.round((maxSensitiveVal * 0.85 + ratio * 100 * 0.15) * 100) / 100;

        logger.info("待检测文本：" + content);
        logger.info("敏感词：" + words);
        logger.info("个数：" + count_star(replace));
        logger.info("总数：" + content.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "").length());
        logger.info("最大敏感值：" + maxSensitiveVal);
        logger.info("占比：" + ratio);
        logger.info("敏感度：" + result);
        logger.info("================================================");
        return Result.success(new Sensitivity(content, replace, words, result));
    }

    @GetMapping("/keywords")
    @ApiOperation("获取所有敏感词")
    public Object keywords() {
        return sensitiveWordService.findAllKeywords();
    }

    /**
     * 统计 * 的数量
     */
    private double count_star(String replace) {
        double count = 0.0;
        for (int i = 0; i < replace.length(); i++)
            if (replace.charAt(i) == '*') count++;
        return count;
    }
}
