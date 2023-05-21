package com.muqi.backendsl.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketRequest implements Serializable {

    /**
     * name of this market
     */
    private String name;

    /**
     * owner of this market
     */
    private Integer userID;

    /**
     * the stock of the market
     */
    private Integer stock;

    /**
     * the price of the market
     */
    private Integer price;

    /**
     * tag
     */
    private Integer tag;

    /**
     * buy or sell
     */
    private Integer isSell;



    private static final long serialVersionUID = 1L;
}
