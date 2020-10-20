package casia.isiteam.sensitivedetection.controller;

import casia.isiteam.sensitivedetection.common.Result;
import casia.isiteam.sensitivedetection.filter.IllegalWordsSearch;
import casia.isiteam.sensitivedetection.filter.IllegalWordsSearchResult;
import casia.isiteam.sensitivedetection.filter.StringSearch;
import casia.isiteam.sensitivedetection.model.Sensitivity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

@RestController
@Api(tags = "文本敏感度接口")
public class IndexController {

    static IllegalWordsSearch illegalWordsSearch = new IllegalWordsSearch();
    static StringSearch stringSearch = new StringSearch();

    static {
        List<String> sensitive_words = new ArrayList<>();

        List<String> filenames = new ArrayList<>(Arrays.asList("kb_words.txt", "sensitive_dict.txt"));
//        List<String> filenames = new ArrayList<>(Arrays.asList("words.txt"));
        for (String filename : filenames) {
            try (Stream<String> stream = Files.lines(ResourceUtils.getFile("classpath:dict/" + filename).toPath())) {
                stream.forEach(sensitive_words::add);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        illegalWordsSearch.SetKeywords(sensitive_words);
        stringSearch.SetKeywords(sensitive_words);
    }

    @PostMapping("/filter2")
    @ApiOperation("跳词版")
    public Result index(String content) {
        List<String> keywords = new ArrayList<>();
        List<IllegalWordsSearchResult> words = illegalWordsSearch.FindAll(content);
        words.forEach(word -> keywords.add(word.MatchKeyword));
        String replace = illegalWordsSearch.Replace(content);
        return Result.success(new Sensitivity(content, replace, keywords, 90.0));
    }

    @PostMapping("/filter")
    @ApiOperation("非跳词版")
    public Result index2(String content) {
        List<String> words = stringSearch.FindAll(content);
        String replace = stringSearch.Replace(content);
        return Result.success(new Sensitivity(content, replace, words, 90.0));
    }
}
