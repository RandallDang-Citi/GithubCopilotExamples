package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MappingConfig mappingConfig;

    //判断字符串是否是json格式
    public static boolean isJson(String value) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(value);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //判断字符串是否是xml格式
    public static boolean isXml(String value) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.readTree(value);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //将list转成map
    public static Map<String, String> listToMapJson(List<MappingBo> list) {
        Map<String, String> map = new HashMap<>();
        for (MappingBo mappingBo : list) {
            map.put(mappingBo.getJson(), mappingBo.getXml());
        }
        return map;
    }

    public static Map<String, String> listToMapXml(List<MappingBo> list) {
        Map<String, String> map = new HashMap<>();
        for (MappingBo mappingBo : list) {
            map.put(mappingBo.getXml(), mappingBo.getJson());
        }
        return map;
    }

    @GetMapping("/jsonToXMl")
    public String jsonToXMl(String value) throws IOException {
        String str = "";
        if(isXml(value)){
            Map<String, String> xmlToJsonFieldNameMappings = listToMapJson(mappingConfig.getList());
            str = convertXmlToJson(value, xmlToJsonFieldNameMappings);
        }
        if (isJson(value)){
            Map<String, String> jsonToXmlFieldNameMappings = listToMapXml(mappingConfig.getList());
            str = convertJsonToXml(value, jsonToXmlFieldNameMappings);
        }
        return str;

    }

    public static String convertXmlToJson(String xml, Map<String, String> fieldNameMappings) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        Object json = xmlMapper.readValue(xml, Object.class);
        applyFieldNameMappings(json, fieldNameMappings);
        return objectMapper.writeValueAsString(json);
    }

    public static String convertJsonToXml(String json, Map<String, String> fieldNameMappings) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        XmlMapper xmlMapper = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
        Object jsonObject = objectMapper.readValue(json, Object.class);
        applyFieldNameMappings(jsonObject, fieldNameMappings);
        return xmlMapper.writeValueAsString(jsonObject);
    }

    private static void applyFieldNameMappings(Object object, Map<String, String> fieldNameMappings) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(object);
        String modifiedJson = applyFieldNameMappingsToJson(json, fieldNameMappings);
        objectMapper.readerForUpdating(object).readValue(modifiedJson);
    }

    private static String applyFieldNameMappingsToJson(String json, Map<String, String> fieldNameMappings) {
        for (Map.Entry<String, String> entry : fieldNameMappings.entrySet()) {
            String oldFieldName = entry.getKey();
            String newFieldName = entry.getValue();
            json = json.replace("\"" + oldFieldName + "\":", "\"" + newFieldName + "\":");
        }
        return json;
    }

}
