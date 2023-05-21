package com.muqi.backendsl.service;

import com.muqi.backendsl.mapper.ArticleMapper;

import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.ArticleDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.strategy.context.ArticleImportStrategyContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class ArticleServiceTest {


    @Autowired
    private ArticleImportStrategyContext articleImportStrategyContext;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void listArticlesByCourseIdTest() {
        PageResultDTO<ArticleCardDTO> articleCardDTOPageResultDTO = articleService.listArticlesByCourseId(1L);
        System.out.println(articleCardDTOPageResultDTO);

    }

    @Test
    public void importTest() {
        byte[] content = "hello, world".getBytes();
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";

        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);
        articleImportStrategyContext.importArticles(result, null, 12);
    }

    @Test
    public void getArticleByIDTest() {
        int articleID = 1;
        ArticleDTO articleById = articleService.getArticleById((long) articleID);

        System.out.println(articleById.getUserDetailsDTO());
    }
}
