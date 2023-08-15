package com.muqi.backendsl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muqi.backendsl.entity.Article;
import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.ArticleDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author mq
* @description 针对表【t_article】的数据库操作Mapper
* @createDate 2023-01-16 10:55:43
* @Entity com.muqi.backendsl.entity.Article
*/

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleCardDTO> listArticlesByCourseId(@Param("courseID") Long courseID);

    ArticleDTO getArticleById(@Param("articleID") Long articleID);
    List<Article> findByArticleStatus(@Param("status") Integer articleStatus);


}




