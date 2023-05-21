package com.muqi.backendsl.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * DTO: Data Transfer Object
 * 数据传输对象，Service或Manager向外传输的对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {
    /**
     * 文章ID
     */
    private Long id;

    /**
     * record the info of author of this article
     */
    private UserDetailsDTO userDetailsDTO;


    /**
     * record whether this article is featured or not
     */
    private Integer isFeatured;

    private Integer viewCount;

    private Integer rewardCount;

    /**
     * the original url
     */
    private String originalUrl;

    /**
     * previous article
     */
    private ArticleDTO preArticleCard;

    /**
     * next article
     */
    private ArticleDTO nextArticleCard;

    /**
     * the article title
     */
    private String articleTitle;

    /**
     * the article content
     */
    private String articleContent;

    /**
     * the article cover
     */
    private String articleCover;


    /**
     * create time
     */
    private Date createTime;

    /**
     * term
     */
    private String term;

    /**
     * category
     */
    private String category;



}
