package com.muqi.backendsl.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 课程类
 * @TableName t_course
 */
@TableName(value ="t_course")
@Data
@AllArgsConstructor
public class Course implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 课程所在学期
     */
    private String term;

    /**
     * 课程类型
     */
    private String category;

    /**
     * 课程教授
     */
    private String professor;

    /**
     * 课程简介
     */
    private String intro;

    /**
     * 课程标题
     */
    private String title;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 课程是否删除（逻辑删除）
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 0 公开 1 私密 
     */
    private Integer status;

    /**
     * 课程封面
     */
    private String courseCover;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}