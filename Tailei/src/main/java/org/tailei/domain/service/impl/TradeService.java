package org.tailei.domain.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tailei.adapter.util.JsonXmlConverter;
import org.tailei.domain.model.Trade;
import org.tailei.domain.model.TradeResp;

import java.util.ArrayList;
import java.util.List;

@Component
public class TradeService {

    private static final String BUY = "BUY";
    private static final String SELL = "SELL";
    private static final Logger logger = LoggerFactory.getLogger(TradeService.class);

    public List<TradeResp> processTrades(List<Trade> trades) {
        logger.info("processTrades start...");
        List<Trade> buyTrades = Trade.filterTrades(trades, BUY);
        List<Trade> sellTrades = Trade.filterTrades(trades, SELL);
        return matchTrades(buyTrades, sellTrades);
    }
    public List<TradeResp> matchTrades(List<Trade> buyTrades, List<Trade> sellTrades) {
        List<TradeResp> tradeResps = new ArrayList<>();
        for (Trade buyTrade : buyTrades) {
            for (Trade sellTrade : sellTrades) {
                int quantity = Math.min(buyTrade.getQuantity(), sellTrade.getQuantity());
                if (buyTrade.getAon().equals("Y") && buyTrade.getQuantity() > sellTrade.getQuantity()) {
                    continue;
                }
                if (sellTrade.getAon().equals("Y") && sellTrade.getQuantity() > buyTrade.getQuantity()) {
                    continue;
                }
                buyTrade.setQuantity(buyTrade.getQuantity() - quantity);
                sellTrade.setQuantity(sellTrade.getQuantity() - quantity);
                if (buyTrade.getQuantity() == 0) {
                    logger.info("buyTrade {} deal..., buyTrade.getQuantity() = {}", buyTrade.getClientId(), buyTrade.getQuantity());

                }
                if (sellTrade.getQuantity() == 0) {
                    logger.info("sellTrade {} deal..., sellTrade.getQuantity() = {}", sellTrade.getClientId(), sellTrade.getQuantity());
                }
            }
        }
        for (Trade buyTrade : buyTrades) {
            TradeResp tradeResp = new TradeResp();
            tradeResp.setClientId(buyTrade.getClientId());
            tradeResp.setMatched(buyTrade.getQuantity() == 0 ? "Y" : "N");
            if(tradeResp.getMatched().equals("Y")) {
                tradeResps.add(tradeResp);
            }
        }
        for (Trade sellTrade : sellTrades) {
            TradeResp tradeResp = new TradeResp();
            tradeResp.setClientId(sellTrade.getClientId());
            tradeResp.setMatched(sellTrade.getQuantity() == 0 ? "Y" : "N");
            if(tradeResp.getMatched().equals("Y")) {
                tradeResps.add(tradeResp);
            }

        }
        return tradeResps;
    }

    public String convertJsonToXml(String json) throws Exception {
        return JsonXmlConverter.convertJsonToXml(json);
    }

    public String convertXmlToJson(String json) throws Exception {
        return JsonXmlConverter.convertXmlToJson(json);
    }


}
