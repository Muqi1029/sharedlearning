package com.muqi.backendsl.controller;


import com.muqi.backendsl.entity.Market;
import com.muqi.backendsl.model.vo.ResultVO;
import com.muqi.backendsl.service.MarketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Resource
    MarketService marketService;


    @GetMapping("/tab/{tag}")
    public ResultVO<List<Market>> listMarketByTag(@PathVariable("tag") Integer tag) {
        return ResultVO.ok(marketService.listMarketByTag(tag));
    }
}
