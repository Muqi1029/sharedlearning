package com.muqi.backendsl.controller;

import com.muqi.backendsl.model.dto.ArticleDTO;
import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.model.vo.ResultVO;

import com.muqi.backendsl.model.vo.ConditionVO;
import com.muqi.backendsl.service.ArticleService;
import com.muqi.backendsl.strategy.context.ArticleImportStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 1. 参数传递不要使用HashMap, 建议使用数据模型定义
 * 2. 可以做参数校验，异常抛出等操作，但不要放太多业务逻辑，业务逻辑尽量放到Service层做
 */


@Api(tags = "文章板块")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleImportStrategyContext articleImportStrategyContext;

    @Autowired
    private ArticleService articleService;

    //TODO import article
    @ApiOperation("上传文章")
    @PostMapping("/import/{userID}")
    public ResultVO<?> importArticles(MultipartFile file, @RequestParam(required = false) String type, @PathVariable("userID") String userID) {
        articleImportStrategyContext.importArticles(file, type, Integer.valueOf(userID));
        return ResultVO.ok();
    }


    //TODO search article

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
