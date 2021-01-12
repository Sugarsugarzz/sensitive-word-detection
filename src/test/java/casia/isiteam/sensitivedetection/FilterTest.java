package casia.isiteam.sensitivedetection;

import casia.isiteam.sensitivedetection.algorithm.StringSearch;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterTest {

    /**
     * 调用测试
     */
    public void test() {
        String content = "哈哈 测试测试";
        System.out.println(replaceSpecWord(content));
    }

    /**
     * 过滤器
     *
     * @param content 需要过滤的内容
     * @return 过滤后的内容
     */
    public String replaceSpecWord(String content) {
        // 获取词典
        List<String> keywords = getKeywordsFromTxt("classpath:dict/keywords.txt");
        System.out.println(keywords);
        // 初始化检测器
        StringSearch searcher = new StringSearch();
        // 设置过滤词典
        searcher.SetKeywords(keywords);
        // 替换掉词典中的词
        return searcher.Replace(content, ' ');
    }

    /**
     * 从txt加载词典
     *
     * @param filepath 文件路径
     * @return List<String>
     */
    public List<String> getKeywordsFromTxt(String filepath) {
        List<String> keywords = new ArrayList<>();
        try (Stream<String> stream = Files.lines(ResourceUtils.getFile(filepath).toPath())) {
            keywords = stream.collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("读取txt失败！ " + e);
            e.printStackTrace();
        }
        return keywords;
    }
}
