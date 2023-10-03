package com.muqi.backendsl.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value = "t_user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 登录账号
     */
    private String loginAccount;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户头像
     */
    private String avatarURL;

    /**
     * 0: male 1: female
     */
    private Integer gender;

    /**
     * 0：普通用户 1：管理员 2：root
     */
    private Integer userAuthority;

    /**
     * 0：未删除 1：删除
     */
    @TableLogic     // 逻辑删除
    private Integer isDelete;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false) // 指定属性对应的字段名
    private static final long serialVersionUID = 1L;
}