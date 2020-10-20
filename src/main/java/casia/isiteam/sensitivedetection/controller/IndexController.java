package casia.isiteam.sensitivedetection.controller;

import casia.isiteam.sensitivedetection.common.lang.Result;
import casia.isiteam.sensitivedetection.algorithm.IllegalWordsSearch;
import casia.isiteam.sensitivedetection.algorithm.IllegalWordsSearchResult;
import casia.isiteam.sensitivedetection.algorithm.StringSearch;
import casia.isiteam.sensitivedetection.model.Sensitivity;
import casia.isiteam.sensitivedetection.service.SensitiveWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@Api(tags = "文本敏感度接口")
public class IndexController {

    @Autowired
    SensitiveWordService sensitiveWordService;


    @PostMapping("/filter2")
    @ApiOperation("跳词版")
    public Result index(String content) {
        // 初始化搜索器
        IllegalWordsSearch searcher = new IllegalWordsSearch();
        searcher.SetKeywords(sensitiveWordService.findAllKeywords());
        // 检测敏感词
        List<String> keywords = new ArrayList<>();
        List<IllegalWordsSearchResult> words = searcher.FindAll(content);
        words.forEach(word -> keywords.add(word.MatchKeyword));
        String replace = searcher.Replace(content);
        return Result.success(new Sensitivity(content, replace, keywords, 90.0));
    }

    @PostMapping("/filter")
    @ApiOperation("非跳词版")
    public Result index2(String content) {
        // 初始化搜索器
        StringSearch searcher = new StringSearch();
        searcher.SetKeywords(sensitiveWordService.findAllKeywords());
        // 检测敏感词
        List<String> words = searcher.FindAll(content);
        String replace = searcher.Replace(content);
        return Result.success(new Sensitivity(content, replace, words, 90.0));
    }

    @RequestMapping("/keywords")
    @ApiOperation("获取所有敏感词")
    public Object keywords() {
        return sensitiveWordService.findAllKeywords();
    }
}
