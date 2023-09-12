
package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.Article;
import com.muqi.backendsl.mapper.ArticleMapper;
import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.ArticleDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.model.vo.ArticleVO;
import com.muqi.backendsl.service.ArticleService;
import com.muqi.backendsl.utils.BeanCopyUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mq
 * @description 针对表【t_article】的数据库操作Service实现
 * @createDate 2023-01-16 10:55:43
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Resource
    private ArticleMapper articleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateArticle(ArticleVO articleVO) {
        Article article = BeanCopyUtil.copyObject(articleVO, Article.class);
        return this.saveOrUpdate(article);
    }

    @Override
    public ArticleDTO getArticleById(Long articleId) {

        return articleMapper.getArticleById(articleId);
    }

    @SneakyThrows
    @Override
    public PageResultDTO<ArticleCardDTO> listArticlesByCourseId(Long courseID) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("courseID", courseID).eq("articleStatus", 2);

        CompletableFuture<Long> asyncCount = CompletableFuture.supplyAsync(() ->
                articleMapper.selectCount(queryWrapper)
        );

        List<ArticleCardDTO> articleCardDTOList = articleMapper.listArticlesByCourseId(courseID);

        return new PageResultDTO<>(articleCardDTOList, asyncCount.get().intValue());
    }

    @Override
    public List<Article> getPendingArticles() {
        return articleMapper.findByArticleStatus(1);
    }

    @Override
    public boolean changeArticleStatus(Integer articleId, Integer articleStatus) {
        Article article = articleMapper.selectById(articleId);
        if (article == null) {
            return false;
        }
        article.setArticleStatus(articleStatus);
        return articleMapper.updateById(article) == 1;
    }

}





