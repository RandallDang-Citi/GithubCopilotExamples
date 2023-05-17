package org.example.service;

import org.springframework.stereotype.Service;

@Service
public interface ConvertService {

    // create a method to covert xml to json
    public String convertXmlToJson(String xml);

    // create a method to covert json to xml
    public String convertJsonToXml(String json);
}
