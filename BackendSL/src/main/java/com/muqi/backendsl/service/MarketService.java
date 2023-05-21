package com.muqi.backendsl.service;

import com.muqi.backendsl.entity.Market;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muqi.backendsl.model.request.MarketRequest;

import java.util.List;

/**
* @author mq
* @description 针对表【t_market】的数据库操作Service
* @createDate 2023-03-26 16:26:12
*/
public interface MarketService extends IService<Market> {

    /**
     * 根据tag获取对应的商品
     * @param tag tag
     * @return list containing markets
     */
    List<Market> listMarketByTag(Integer tag);

    /**
     * 添加商品信息
     * @param request the info of market
     * @return the result of add
     */
    boolean addMarket(MarketRequest request);
}
