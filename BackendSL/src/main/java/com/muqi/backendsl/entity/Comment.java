package com.muqi.backendsl.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户iD（表示该条评论由哪条用户发出）
     */
    private Integer userID;

    /**
     * 文章ID
     */
    private Integer articleID;

    /**
     * 父评论ID
     */
    private Integer parentID;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否审核
     */
    private Integer isReview;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}