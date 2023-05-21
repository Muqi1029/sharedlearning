package com.muqi.backendsl.strategy.impl;

//import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.muqi.backendsl.service.ArticleService;
import com.muqi.backendsl.strategy.ArticleImportStrategy;
import lombok.extern.slf4j.Slf4j;
import com.muqi.backendsl.exception.BizException;
import com.muqi.backendsl.model.vo.ArticleVO;
//import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static com.muqi.backendsl.enums.ArticleStatusEnum.DRAFT;


@Slf4j //使用log功能

@Service("ArticleImportStrategyimpl")
public class ArticleImportStrategyimpl implements ArticleImportStrategy {
    @Autowired
    private ArticleService articleService;
    @Override
    public void importArticles(MultipartFile file, Integer userID) {
        String articleTitle = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0];
        //获取上传文件的原始文件名,使用“.”作为分隔符对其进行拆
        StringBuilder articleContent = new StringBuilder();
        //采用结果数组的第一个元素，该元素被假定为文章的标题
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            while (reader.ready()) {
                articleContent.append((char) reader.read());
            }
            //使用 BufferedReader 读取文件的内容，一次读取一个字符并将
            // 其附加StringBuilder
        } catch (IOException e) {
            log.error(StrUtil.format("导入文章失败, 堆栈:{}", ExceptionUtil.stacktraceToString(e)));
            //log见下
            throw new BizException("导入文章失败");
        }


        // create an articleVO depend on the file user upload

        ArticleVO articleVO = ArticleVO.builder()
                .articleTitle(articleTitle)
                .articleContent(articleContent.toString())
                .articleStatus(DRAFT.getStatus())
                //ArticleStatusEnum DRAFT=3
                .build();

        //使用提取的标题、内容和状态为“草稿”创建一个 ArticleVO 对象。然后，它调用传递 ArticleVO 对象的文章服务的 saveOrUpdateArticle 方法
        articleService.saveOrUpdateArticle(articleVO, userID);
    }
}
