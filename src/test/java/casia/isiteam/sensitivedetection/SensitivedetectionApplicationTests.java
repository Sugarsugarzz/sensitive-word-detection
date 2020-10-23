package casia.isiteam.sensitivedetection;

import casia.isiteam.sensitivedetection.model.SensitiveWord;
import casia.isiteam.sensitivedetection.service.SensitiveWordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class SensitivedetectionApplicationTests {

    @Autowired
    SensitiveWordService sensitiveWordService;

//    /** 从数据库获取所有敏感词 */
//    @Test
//    void test_get_all_keywords() {
//        List<SensitiveWord> allKeywords = sensitiveWordService.findAllKeywords();
//        System.out.println(allKeywords);
//    }
//
//    /** dict目录下敏感词和对应级别入库 */
//    @Test
//    void test_save_keywords_from_txt() {
//        List<SensitiveWord> words = new ArrayList<>();
//        List<String> filenames = new ArrayList<>(Arrays.asList("kb_words.txt", "sensitive_dict.txt"));
//        for (String filename : filenames) {
//            try (Stream<String> stream = Files.lines(ResourceUtils.getFile("classpath:dict/" + filename).toPath())) {
//                stream.forEach(line -> {
//                    List<String> collect = Arrays.stream(line.split("\\s")).collect(Collectors.toList());
//                    words.add(new SensitiveWord(collect.get(0), Integer.parseInt(collect.get(1))));
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        sensitiveWordService.saveKeywords(words);
//    }
}
