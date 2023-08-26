package com.muqi.backendsl.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "文章")
public class ArticleVO {

    @ApiModelProperty(name = "id", value = "文章id", dataType = "Integer")
    private Long id;

    private Long userID;

    private Long courseID;

    @NotBlank(message = "文章标题不能为空")
    @ApiModelProperty(name = "articleTitle", value = "文章标题", required = true, dataType = "String")
    private String articleTitle;

    @NotBlank(message = "文章内容不能为空")
    @ApiModelProperty(name = "articleContent", value = "文章内容", required = true, dataType = "String")
    private String articleContent;

    @ApiModelProperty(name = "articleCover", value = "文章缩略图", dataType = "String")
    private String articleCover;

    @ApiModelProperty(name = "isFeatured", value = "否", dataType = "Integer")
    private Integer isFeatured;

    @ApiModelProperty(name = "articleType", value = "文章类型", dataType = "Integer")
    private Integer articleType;

    @ApiModelProperty(name = "originalURL", value = "原文链接", dataType = "String")
    private String originalURL;

    @ApiModelProperty(name = "articleStatus", value = "文章状态", dataType = "Integer")
    private Integer articleStatus;



}
