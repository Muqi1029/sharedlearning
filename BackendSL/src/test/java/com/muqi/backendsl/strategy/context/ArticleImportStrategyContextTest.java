package com.muqi.backendsl.strategy.context;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class ArticleImportStrategyContextTest {

    @Autowired
    private ArticleImportStrategyContext articleImportStrategyContext;


    @Test
    public void testArticleImport() {
        String teststr = "hello world!";
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = teststr.getBytes();
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);
        articleImportStrategyContext.importArticles(result, null, 12);
//        new ArticleImportStrategyContext().importArticles(result, null);
    }


}
