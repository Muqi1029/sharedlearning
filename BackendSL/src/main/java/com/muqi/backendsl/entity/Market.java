package com.muqi.backendsl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_market
 */
@TableName(value ="t_market")
@Data
public class Market implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品类型 1:生活用品 2: book 3. 电子产品
     */
    private Integer tag;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 数量
     */
    private Integer stock;

    /**
     * 商家ID
     */
    private Integer userID;

    /**
     * 商品图
     */
    private String profile;

    /**
     * buy or sell
     */
    private Integer isSell;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}