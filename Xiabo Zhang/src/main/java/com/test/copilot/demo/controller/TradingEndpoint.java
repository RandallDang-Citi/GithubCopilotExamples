package com.test.copilot.demo.controller;

import com.alibaba.fastjson.JSON;
import com.test.copilot.demo.entity.TradingEntity;
import com.test.copilot.demo.service.TradingService;

import java.util.List;


public class TradingEndpoint {

    private TradingService tradingService = new TradingService();

    public String trading() {
        String jsonstr="[{\"client\":\"X\",\"type\":\"BUY\",\"size\":\"1000\",\"AON\":\"Y\"},{\"client\":\"Y\",\"type\":\"BUY\",\"size\":\"800\",\"AON\":\"N\"},{\"client\":\"Z\",\"type\":\"BUY\",\"size\":\"500\",\"AON\":\"N\"},{\"client\":\"A\",\"type\":\"SELL\",\"size\":\"1200\",\"AON\":\"N\"},{\"client\":\"B\",\"type\":\"SELL\",\"size\":\"600\",\"AON\":\"N\"},{\"client\":\"C\",\"type\":\"SELL\",\"size\":\"400\",\"AON\":\"Y\"}]";
        // 把jsonstr转成entity
        List<TradingEntity> entity = JSON.parseArray(jsonstr, TradingEntity.class);
        return tradingService.trade(entity);
    }

    public static void main(String[] args) {
        TradingEndpoint tradingEndpoint = new TradingEndpoint();
        System.out.println(tradingEndpoint.trading());
    }
}
