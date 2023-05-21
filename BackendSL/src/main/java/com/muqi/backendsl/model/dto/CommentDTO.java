package com.muqi.backendsl.model.dto;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class CommentDTO implements Serializable {
    private Integer id;

    /**
     * 用户iD（表示该条评论由哪条用户发出）
     */
    private Integer userID;

    /**
     * 父评论ID
     */
    private Integer parentID;

    /**
     * 是否删除
     */
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


    private List<CommentDTO> replyDTOS;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}