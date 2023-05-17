package org.tailei.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.gson.JsonObject;
import org.tailei.domain.model.Trade;
import org.tailei.domain.model.TradeResp;
import org.tailei.domain.service.impl.TradeService;

import java.util.List;


@RestController
public class SampleController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TradeService tradeService;

    /*
    * restful api
    * @postmapping
    * @param @requestparam list
     */

    @PostMapping("/processTrade")
    public List<TradeResp> processTrade(@RequestBody List<Trade> trades){
        logger.info("getUserById start...");
        return tradeService.processTrades(trades);
    }
    /*
    * convert json to xml
    *
     */
    @PostMapping("/convertJsonToXml")
    public String convertJsonToXml(@RequestBody String o) throws Exception {
        return tradeService.convertJsonToXml(o);
    }

    @PostMapping("/convertXmlToJson")
    public String convertXmlToJson(@RequestBody String xml) throws Exception {
        return tradeService.convertXmlToJson(xml);
    }
}
