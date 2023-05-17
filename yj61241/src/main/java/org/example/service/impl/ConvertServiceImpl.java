package org.example.service.impl;

import org.example.service.ConvertService;

public class ConvertServiceImpl implements ConvertService {
    @Override
    public String convertXmlToJson(String xml) {
        String json = org.json.XML.toJSONObject(xml).toString();

        return json;
    }

    @Override
    public String convertJsonToXml(String json) {
        String xml = org.json.XML.toString(json);

        return xml;
    }
}
