package com.muqi.backendsl.controller;

import com.muqi.backendsl.entity.Article;
import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.ArticleDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.model.vo.ArticleVO;
import com.muqi.backendsl.model.vo.ResultVO;
import com.muqi.backendsl.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "文章板块")
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Resource
    private ArticleService articleService;


    @ApiOperation("list articles by courseId")
    @GetMapping("/course/{courseID}")
    public ResultVO<PageResultDTO<ArticleCardDTO>> listArticlesByCourseId(@PathVariable("courseID") Long courseID) {
        return ResultVO.ok(articleService.listArticlesByCourseId(courseID));
    }


    @ApiOperation("get article by articleId")
    @RequestMapping("/{articleId}")
    public ResultVO<ArticleDTO> getArticleById(@PathVariable("articleId") Long articleId) {
        return ResultVO.ok(articleService.getArticleById(articleId));
    }


    @PostMapping("/admin/articles")
    public ResultVO<List<Article>> getPendingArticles(@RequestBody Map<String, Integer> requestBody) {
        int userAuthority = requestBody.getOrDefault("userAuthority", 0);
        if (userAuthority == 1) {
            List<Article> pendingArticles = articleService.getPendingArticles();
            return ResultVO.ok(pendingArticles);
        } else {
            return ResultVO.fail();
        }
    }


    @PostMapping("/import-article")
    public void importArticle(ArticleVO article, Integer userId) {
        article.setArticleStatus(1);

        articleService.saveOrUpdateArticle(article, userId);
    }


    @PostMapping("/change-article-status")
    public boolean changeArticleStatus(ArticleVO article, Integer userId) {

        if (article != null) {
            article.setArticleStatus(0);

            articleService.saveOrUpdateArticle(article, userId);
            return true;
        } else {
            return false;
        }
    }
//    articleStatus  '0 - 公开1 - 私密2 - 草稿',


}
