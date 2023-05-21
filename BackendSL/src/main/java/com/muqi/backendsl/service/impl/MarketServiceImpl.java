package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.Market;
import com.muqi.backendsl.model.request.MarketRequest;
import com.muqi.backendsl.service.MarketService;
import com.muqi.backendsl.mapper.MarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mq
 * @description 针对表【t_market】的数据库操作Service实现
 * @createDate 2023-03-26 16:26:12
 */
@Service
public class MarketServiceImpl extends ServiceImpl<MarketMapper, Market>
        implements MarketService {

    @Autowired
    private MarketMapper marketMapper;

    @Override
    public List<Market> listMarketByTag(Integer tag) {
        List<Market> marketList = null;
        QueryWrapper<Market> marketQueryWrapper = new QueryWrapper<>();

        if (tag != 0) {
            marketQueryWrapper.eq("tag", tag);
        }
        marketList = marketMapper.selectList(marketQueryWrapper);
        return marketList;

    }

    @Override
    public boolean addMarket(MarketRequest request) {
        String name = request.getName();
        Integer userID = request.getUserID();
        Integer stock = request.getStock();
        Integer price = request.getPrice();
        Integer tag = request.getTag();

        Market market = new Market();

        market.setTag(tag);
        market.setName(name);
        market.setPrice(price);
        market.setStock(stock);
        market.setUserID(userID);

        int insert = marketMapper.insert(market);
        if (insert > 0) {
            return true;
        }
        return false;
    }
}




