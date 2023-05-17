package org.example.controller;

import org.example.pojo.TradePojo;
import org.example.service.ConvertService;
import org.example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* question3:
*        /convertXmlToJson
*        /convertJsonToXml
* question4:
*        /trade
*
* */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private ConvertService convertService;

    @Autowired
    private TradeService tradeService;

    // create a method to covert xml to json
    @PostMapping("/convertXmlToJson")
    public String convertXmlToJson(String xml) {

        String json = convertService.convertXmlToJson(xml);
        System.out.println(json);
        return json;
    }

    @PostMapping("/convertJsonToXml")
    public String convertJsonToXml(String json) {

        String xml = convertService.convertJsonToXml(json);

        // print the result
        System.out.println(xml);
        return xml;

    }

    @PostMapping("/trade")
    public int trade(@RequestBody List<TradePojo> trade) {

        int num = tradeService.createTrade(trade);
        return num;
    }
}
