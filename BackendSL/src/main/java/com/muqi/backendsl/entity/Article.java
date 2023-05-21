package com.muqi.backendsl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_article
 */
@TableName(value ="t_article")
@Data
public class Article implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;



    private Integer status;

    /**
     * 文章作者
     */
    private Long userID;

    /**
     * 文章缩略图
     */
    private String articleCover;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 0 - 不置顶
1 - 推荐
     */
    private Integer isFeatured;

    /**
     * 0 - 未删除
1 - 已删除
     */
    private Integer isDelete;

    /**
     * 0 - 公开
1 - 私密
2 - 草稿
     */
    private Integer articleStatus;

    /**
     * 0 - 原创
1 - 转载
2 - 翻译
     */
    private Integer articleType;

    /**
     * 原文链接
     */
    private String originalURL;

    /**
     * 发表时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0 独立文章 >= 1 课程文章
     */
    private Long courseID;

    /**
     * 打赏数
     */
    private Integer rewardCount;

    /**
     * 点赞数
     */
    private Integer thumbUpCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 观看数
     */
    private Integer viewCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}