package com.muqi.backendsl.controller;

import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.ArticleDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.model.vo.ResultVO;
import com.muqi.backendsl.service.ArticleService;
import com.muqi.backendsl.strategy.context.ArticleImportStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(tags = "文章板块")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleImportStrategyContext articleImportStrategyContext;

    @Resource
    private ArticleService articleService;

    @ApiOperation("上传文章")
    @PostMapping("/import/{userID}")
    public ResultVO<?> importArticles(MultipartFile file, @RequestParam(required = false) String type, @PathVariable("userID") String userID) {
        articleImportStrategyContext.importArticles(file, type, Integer.valueOf(userID));
        return ResultVO.ok();
    }


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

}
