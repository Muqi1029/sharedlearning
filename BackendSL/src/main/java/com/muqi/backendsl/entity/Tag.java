package com.muqi.backendsl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签表
 * @TableName t_tag
 */
@TableName(value ="t_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名
     */
    private String tag_name;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;


    /**
     * 课程ID
     */
    private Integer courseID;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}