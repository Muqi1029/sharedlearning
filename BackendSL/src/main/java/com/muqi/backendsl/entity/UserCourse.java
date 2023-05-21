package com.muqi.backendsl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 记录用户点击课程次数
 * @TableName t_user_course
 */
@TableName(value ="t_user_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourse implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userID;

    /**
     * 课程ID
     */
    private Integer courseID;

    /**
     * 点击数
     */
    private Long clicks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}