package org.example.service;

import org.example.pojo.TradePojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TradeService {


    // receive a trade pojo and return a string
    public int createTrade(List<TradePojo> trade);
}
