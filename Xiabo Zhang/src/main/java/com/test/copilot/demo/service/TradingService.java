package com.test.copilot.demo.service;

import com.test.copilot.demo.entity.TradingEntity;
import com.test.copilot.demo.entity.TradingProcessEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradingService {
    public String trade(List<TradingEntity> entity) {
        //创建一个新的TradingProcessEntity列表，并把entity列表中的每个TradingEntity转换成TradingProcessEntity
        List<TradingProcessEntity> tradingProcessEntityList = entity.stream().map(e -> {
            TradingProcessEntity tradingProcessEntity = new TradingProcessEntity();
            tradingProcessEntity.setClient(e.getClient());
            tradingProcessEntity.setType(e.getType());
            tradingProcessEntity.setSize(e.getSize());
            tradingProcessEntity.setAON(e.getAON());
            tradingProcessEntity.setComplete(false);
            return tradingProcessEntity;
        }).collect(Collectors.toList());

        //把TradingProcessEntity列表按AON的值分成两个列表
        List<TradingProcessEntity> tradingProcessEntityListNotAON = tradingProcessEntityList.stream().filter(e -> e.getAON().equals("N")).collect(Collectors.toList());
        List<TradingProcessEntity> tradingProcessEntityListAON = tradingProcessEntityList.stream().filter(e -> e.getAON().equals("Y")).collect(Collectors.toList());

        //按type和size匹配tradingProcessEntityListAON列表

        for (int i = 0; i < tradingProcessEntityListAON.size() - 1; i++) {
            TradingProcessEntity tradingProcessEntity = tradingProcessEntityListAON.get(i);
            for (int j = i + 1; j < tradingProcessEntityListAON.size(); j++) {
                TradingProcessEntity tradingProcessEntity1 = tradingProcessEntityListAON.get(j);
                if (!tradingProcessEntity.getType().equals(tradingProcessEntity1.getType()) && tradingProcessEntity.getSize().equals(tradingProcessEntity1.getSize())) {
                    tradingProcessEntity.setComplete(true);
                    tradingProcessEntity1.setComplete(true);
                    break;
                }
            }

        }

        //找出tradingProcessEntityListAON列表中所有未完成的元素
        tradingProcessEntityListAON.stream().filter(e -> !e.getComplete()).forEach(e -> {
            //在tradingProcessEntityListNotAON列表中查找type不相同且size大于等于当前元素的元素
            TradingProcessEntity tradingProcessEntity = tradingProcessEntityListNotAON.stream().filter(e1 -> !e1.getComplete() && !e1.getType().equals(e.getType()) && e1.getSize() >= e.getSize()).findFirst().orElse(null);
            if (tradingProcessEntity != null) {
                tradingProcessEntity.setSize(tradingProcessEntity.getSize() - e.getSize());
                e.setComplete(true);
            }
        });

        //对于tradingProcessEntityListNotAON列表中的所有type为Buy的元素求和size
        Integer buySize = tradingProcessEntityListNotAON.stream().filter(e -> !e.getComplete() && e.getType().equals("BUY")).mapToInt(TradingProcessEntity::getSize).sum();
        //对于tradingProcessEntityListNotAON列表中的所有type为Sell的元素求和size
        Integer sellSize = tradingProcessEntityListNotAON.stream().filter(e -> !e.getComplete() && e.getType().equals("SELL")).mapToInt(TradingProcessEntity::getSize).sum();

        //如果buySize大于sellSize，对于tradingProcessEntityListNotAON列表中的所有type为Buy的元素，把complete设为true
        if (buySize > sellSize) {
            tradingProcessEntityListNotAON.stream().filter(e -> !e.getComplete() && e.getType().equals("SELL")).forEach(e -> e.setComplete(true));
            while (sellSize>0){
                TradingProcessEntity tradingProcessEntity = tradingProcessEntityListNotAON.stream().filter(e -> !e.getComplete() && e.getType().equals("BUY")).findFirst().orElse(null);
                if (tradingProcessEntity != null) {
                    if (tradingProcessEntity.getSize() > sellSize) {
                        tradingProcessEntity.setSize(tradingProcessEntity.getSize() - sellSize);
                        sellSize = 0;
                    } else {
                        sellSize = sellSize - tradingProcessEntity.getSize();
                        tradingProcessEntity.setComplete(true);
                    }
                }else{
                    break;
                }
            }
        } else {
            //如果buySize小于sellSize，对于tradingProcessEntityListNotAON列表中的所有type为Sell的元素，把complete设为true
            tradingProcessEntityListNotAON.stream().filter(e -> !e.getComplete() && e.getType().equals("BUY")).forEach(e -> e.setComplete(true));
            while(buySize>0){
                TradingProcessEntity tradingProcessEntity = tradingProcessEntityListNotAON.stream().filter(e -> !e.getComplete() && e.getType().equals("SELL")).findFirst().orElse(null);
                if (tradingProcessEntity != null) {
                    if (tradingProcessEntity.getSize() > buySize) {
                        tradingProcessEntity.setSize(tradingProcessEntity.getSize() - buySize);
                        buySize = 0;
                    } else {
                        buySize = buySize - tradingProcessEntity.getSize();
                        tradingProcessEntity.setComplete(true);
                    }
                }else{
                    break;
                }
            }
        }

        //对于tradingProcessEntityList列表中的所有元素，如果complete为true，返回"Client: " + client + " " + "Type: " + type + " " + "Size: " + size + " " + "AON: " + AON + " " + "Complete: " + complete
        return tradingProcessEntityList.stream().filter(e -> e.getComplete()).map(e -> "Client: " + e.getClient() + " " + "Type: " + e.getType() + " " + "Size: " + e.getSize() + " " + "AON: " + e.getAON() + " " + "Complete: " + e.getComplete()).collect(Collectors.joining("\n"));


    }
}
