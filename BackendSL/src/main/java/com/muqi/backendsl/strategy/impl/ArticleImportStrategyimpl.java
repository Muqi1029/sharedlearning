package com.muqi.backendsl.strategy.impl;

import com.muqi.backendsl.service.ArticleService;
import com.muqi.backendsl.strategy.ArticleImportStrategy;
import lombok.extern.slf4j.Slf4j;
import com.muqi.backendsl.exception.BizException;
import com.muqi.backendsl.model.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


@Slf4j

@Service("ArticleImportStrategyimpl")
public class ArticleImportStrategyimpl implements ArticleImportStrategy {
    @Autowired
    private ArticleService articleService;
    @Override
    public void importArticles(MultipartFile file, Integer userID) {
        String articleTitle = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0];
        StringBuilder articleContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            while (reader.ready()) {
                articleContent.append((char) reader.read());
            }
        } catch (IOException e) {
            log.error(StrUtil.format("导入文章失败, 堆栈:{}", ExceptionUtil.stacktraceToString(e)));
            throw new BizException("导入文章失败");
        }



        ArticleVO articleVO = ArticleVO.builder()
                .articleTitle(articleTitle)
                .articleContent(articleContent.toString())
                .build();

        articleService.saveOrUpdateArticle(articleVO, userID);
    }
}
