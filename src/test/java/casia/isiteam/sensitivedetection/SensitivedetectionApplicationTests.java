package casia.isiteam.sensitivedetection;

import casia.isiteam.sensitivedetection.filter.IllegalWordsSearch;
import casia.isiteam.sensitivedetection.filter.IllegalWordsSearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class SensitivedetectionApplicationTests {

    @Test
    void test_IllegalWordsSearch() {

        String sentence = "习大大习近平万岁，打到共 产党；国$民党；taiwan；tw；台w；taiwan；台湾；台；湾；" +
                "民民民民民进党；民主党党党党党；民民民主党；民主主主主主党";
        List<String> list = new ArrayList<>();
        list.add("习近平");
        list.add("习大大");
        list.add("习大大习近平");
        list.add("共产党");
        list.add("国民党");
        list.add("台湾");
        list.add("民进党");
        list.add("民主党");

        IllegalWordsSearch words = new IllegalWordsSearch();
        words.SetKeywords(list);

        List<IllegalWordsSearchResult> all = words.FindAll(sentence);
        for (IllegalWordsSearchResult w : all) {
            System.out.println(w.Keyword);
            System.out.println(w.Index);
            System.out.println(w.Start);
            System.out.println(w.End);
            System.out.println(w.BlacklistType);
            System.out.println(w.MatchKeyword);
            System.out.println("--------------");
        }
    }

//    @Test
//    void test_StringSearch() {
//        String sentence = "习大大习近平万岁，打到共 产党";
//        List<String> list = new ArrayList<>();
//        list.add("习近平");
//        list.add("习大大");
//        list.add("习大大习近平");
//        list.add("共产党");
//
//        StringSearch words = new StringSearch();
//        words.SetKeywords(list);
//
//        System.out.println(words.ContainsAny(sentence));
//
//        List<String> ws = words.FindAll(sentence);
//        for (String w : ws) {
//            System.out.println(w);
//        }
//        System.out.println(words.Replace(sentence));
//    }


//    private static void test_save_load() throws IOException {
//        String test = "我是中国人";
//        List<String> list = new ArrayList<String>();
//        list.add("中国");
//        list.add("国人");
//        list.add("zg人");
//        System.out.println("test_save_load run Test.");
//
//        StringSearchEx2 search = new StringSearchEx2();
//        search.SetKeywords(list);
//        search.Save("1.dat");
//
//        StringSearchEx2 iwords = new StringSearchEx2();
//        iwords.Load("1.dat");
//
//        boolean b = iwords.ContainsAny(test);
//        if (b == false) {
//            System.out.println("ContainsAny is Error.");
//        }
//
//        String f = iwords.FindFirst(test);
//        if (f != "中国") {
//            System.out.println("FindFirst is Error.");
//        }
//
//        List<String> all = iwords.FindAll(test);
//        if (all.get(0) != "中国") {
//            System.out.println("FindAll is Error.");
//        }
//        if (all.get(1) != "国人") {
//            System.out.println("FindAll is Error.");
//        }
//        if (all.size() != 2) {
//            System.out.println("FindAll is Error.");
//        }
//
//        String str = iwords.Replace(test, '*');
//        if (str.equals("我是***") == false) {
//            System.out.println("Replace is Error.");
//        }
//    }

}
