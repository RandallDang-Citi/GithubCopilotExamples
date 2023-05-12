package com.connect;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "adbate")
public class ClassKetValueConfig {

    private List<ClassKetValue> list;

    public List<ClassKetValue> getList() {
        return list;
    }

    public void setList(List<ClassKetValue> list) {
        this.list = list;
    }
}
