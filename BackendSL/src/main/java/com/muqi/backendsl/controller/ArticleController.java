package com.muqi.backendsl.controller;

import com.muqi.backendsl.entity.Article;
import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.ArticleDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.model.request.ArticleRequest;
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
    public ResultVO<Boolean> importArticle(@RequestBody ArticleRequest articleRequest) {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setArticleStatus(1);
        articleVO.setArticleContent(articleRequest.getArticleContent());
        articleVO.setCourseID(articleRequest.getCourseId());
        articleVO.setUserID(articleRequest.getUserId());
        return ResultVO.ok(articleService.saveOrUpdateArticle(articleVO));
    }


    @PostMapping("/changeStatus")
    public ResultVO<Boolean> changeArticleStatus(@RequestBody Map<String, Integer> requestBody) {
        int aId = requestBody.getOrDefault("articleId", 0);
        int sId = requestBody.getOrDefault("articleStatus", -1);
        if (aId == 0 || sId == -1) {
            return ResultVO.fail();
        }
        if (articleService.changeArticleStatus(aId, sId)) {
            return ResultVO.ok();
        }
        return ResultVO.fail();
    }
}
