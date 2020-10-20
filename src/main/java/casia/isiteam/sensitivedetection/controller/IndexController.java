package casia.isiteam.sensitivedetection.controller;

import casia.isiteam.sensitivedetection.common.Result;
import casia.isiteam.sensitivedetection.filter.IllegalWordsSearch;
import casia.isiteam.sensitivedetection.filter.IllegalWordsSearchResult;
import casia.isiteam.sensitivedetection.filter.StringSearch;
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

        Set<String> result = new HashSet<>();
        List<IllegalWordsSearchResult> words = illegalWordsSearch.FindAll(content);
        words.forEach(word -> {
            result.add(word.MatchKeyword);
        });

        String replace = illegalWordsSearch.Replace(content);

        System.out.println(content);
        System.out.println(replace);
        System.out.println("===========");
        return Result.success(result.size());
    }

    @PostMapping("/filter")
    @ApiOperation("非跳词版")
    public Result index2(String content) {

        List<String> words = stringSearch.FindAll(content);
        String replace = stringSearch.Replace(content);
        System.out.println(content);
        System.out.println(words);
        System.out.println(replace);
        System.out.println("======================");

        return Result.success(words.size());
    }
}
