package com.muqi.backendsl.service;

import com.muqi.backendsl.model.request.MarketRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @Test
    public void listMarketByTag() {
        int tag = 3;
        marketService.listMarketByTag(tag);
    }

    @Test
    public void addMarketTag3() {
        MarketRequest marketRequest = new MarketRequest();
        marketRequest.setName("显示器");
        marketRequest.setUserID(12);
        marketRequest.setStock(1);
        marketRequest.setPrice(700);
        marketRequest.setTag(3);

        assert marketService.addMarket(marketRequest);
        
    }

    @Test
    public void addMarketTag2() {
        MarketRequest marketRequest = new MarketRequest();
        marketRequest.setName("算法书");
        marketRequest.setUserID(12);
        marketRequest.setStock(1);
        marketRequest.setPrice(10);
        marketRequest.setTag(2);
        assert marketService.addMarket(marketRequest);
    }

    @Test
    public void addMarketTag1() {
        MarketRequest marketRequest = new MarketRequest();
        marketRequest.setName("纸巾");
        marketRequest.setUserID(12);
        marketRequest.setStock(1);
        marketRequest.setPrice(5);
        marketRequest.setTag(1);

        assert marketService.addMarket(marketRequest);

    }
}