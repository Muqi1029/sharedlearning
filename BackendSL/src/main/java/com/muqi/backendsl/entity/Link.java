package com.muqi.backendsl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_link
 */
@TableName(value ="t_link")
@Data
public class Link implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 链接url
     */
    private String url;

    /**
     * 
     */
    private Integer courseID;

    /**
     * 是否公有
     */
    private Integer isOffice;

    /**
     * 该链接属于哪个文章
     */
    private Integer userID;

    /**
     * the name of url
     */
    private String name;

    /**
     * is recommended
     */
    private Integer isRecommend;

    /**
     * 0: github
     * 1: article
     * 2: other
     */
    private Integer source;

    /**
     * 这个link对应的tag
     */
    private Integer tagID;

    private String cover;

    private int linkStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}