
package com.muqi.backendsl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muqi.backendsl.entity.Article;
import com.muqi.backendsl.model.dto.*;
import com.muqi.backendsl.model.vo.*;

import java.io.IOException;
import java.util.List;


/**
* @author mq
* @description 针对表【t_article】的数据库操作Service
* @createDate 2023-01-16 10:55:43
*/
public interface ArticleService extends IService<Article> {



    void saveOrUpdateArticle(ArticleVO articleVO, Integer userID);


    /**
     * 根据文章ID获取对应的文章
     * @param articleId 文章ID
     * @return ArticleDTO
     */
    ArticleDTO getArticleById(Long articleId);

    PageResultDTO<ArticleCardDTO> listArticlesByCourseId(Long courseID);

//    List<ArticleSearchDTO> listArticlesBySearch(ConditionVO condition);

    List<Article> getPendingArticles();
//    ResultVO<User> userLogin(String loginAccount, String loginPassword);


}

