package com.muqi.backendsl.model.dto;

import com.muqi.backendsl.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCardDTO {

    private Integer id;

    private String articleCover;

    private String articleTitle;

    private String articleContent;

    private Integer articleType;

    private UserInfo userInfo;

    private Integer articleStatus;

    private Date createTime;

    private Integer courseID;

    private Integer rewardCount;

    private Integer thumbUpCount;

    private Integer viewCount;

    private Integer commentCount;

}
