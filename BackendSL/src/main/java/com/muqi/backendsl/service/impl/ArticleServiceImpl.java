
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author mq
 * @description 针对表【t_article】的数据库操作Service实现
 * @createDate 2023-01-16 10:55:43
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {



    @Autowired
    private ArticleMapper articleMapper;


    /**
     * tag of classes received from the sql
     **/
    private final List<String> tags = null;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateArticle(ArticleVO articleVO, Integer userID) {
        Article article = BeanCopyUtil.copyObject(articleVO, Article.class);
        article.setUserID(Long.valueOf(userID));
        article.setCourseID(1L);
        // save the article to t_article
        this.saveOrUpdate(article);
    }

    @Override
    public ArticleDTO getArticleById(Long articleId) {

        return articleMapper.getArticleById(articleId);
    }

    @SneakyThrows
    @Override
    public PageResultDTO<ArticleCardDTO> listArticlesByCourseId(Long courseID) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("courseID", courseID);

        CompletableFuture<Long> asyncCount = CompletableFuture.supplyAsync(() ->
                articleMapper.selectCount(queryWrapper)
        );

        List<ArticleCardDTO> articleCardDTOList = articleMapper.listArticlesByCourseId(courseID);

        return new PageResultDTO<>(articleCardDTOList, asyncCount.get().intValue());
    }


//    @Override
//    public List<ArticleSearchDTO> listArticlesBySearch(ConditionVO condition) {
//        return searchStrategyContext.executeSearchStrategy(condition.getKeywords());
//    }

}





