package org.example.service.impl;

import org.example.pojo.TradePojo;
import org.example.service.TradeService;

import java.util.ArrayList;
import java.util.List;

public class TradeServiceImpl implements TradeService {

    @Override
    public int createTrade(List<TradePojo> trade) {

        List<TradePojo> buyList = new ArrayList<>();
        List<TradePojo> sellList = new ArrayList<>();
        Integer buySize = 0;
        for (TradePojo pojo : trade) {
            if (pojo.getClientType().equals("BUY")) {
                buyList.add(pojo);
            }else {
                sellList.add(pojo);
            }
        }

        for (TradePojo buy : buyList) {
            if (buy.getAon().equals("Y")) {
                for (TradePojo sell : sellList) {
                   if (sell.getAon().equals("Y")) {
                          if (buy.getSize() == sell.getSize()) {
                            buySize = buySize + sell.getSize();
                          }
                   }else {
                       if (buy.getSize() <= sell.getSize()) {
                           buySize = buySize + sell.getSize();
                       }
                   }
                }
            }else {
                for (TradePojo sell : sellList) {
                    if (sell.getAon().equals("Y")) {
                        if (buy.getSize() == sell.getSize()) {
                            buySize = buySize + sell.getSize();
                        }
                    }else {
                        if (buy.getSize() <= sell.getSize()) {
                            buySize = buySize + sell.getSize();
                        }
                    }
                }
            }
        }
        return buySize;
    }
}
