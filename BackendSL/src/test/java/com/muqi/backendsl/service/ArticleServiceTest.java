package com.muqi.backendsl.service;

import com.muqi.backendsl.mapper.ArticleMapper;

import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.ArticleDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.model.vo.ArticleVO;
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
    public void getArticleByIDTest() {
        int articleID = 1;
        ArticleDTO articleById = articleService.getArticleById((long) articleID);

        System.out.println(articleById.getUserDetailsDTO());
    }

    @Test
    public void getPendingArticleTest() {
        System.out.println(articleService.getPendingArticles());
    }

    @Test
    public void importArticle() {
        ArticleVO article = new ArticleVO(1122256122L, 111633444L, 111666777L,"weq","wdwd","qas",2,0,"wer",1);

        articleService.saveOrUpdateArticle(article,122345);
        System.out.println(article.getArticleStatus());
    }

    @Test
    public void changeArticleStatus() {
        ArticleVO article = new ArticleVO(1122256122L, 111633444L, 111666777L,"weq","wdwd","qas",2,0,"wer",2);

        articleService.saveOrUpdateArticle(article,122345);
        System.out.println(article.getArticleStatus());

    }



    }
