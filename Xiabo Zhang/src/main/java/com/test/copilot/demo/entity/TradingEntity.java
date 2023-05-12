package com.test.copilot.demo.entity;

import lombok.Data;


public class TradingEntity {
    private String client;
    private String type;
    private Integer size;
    private String AAA;

    private String AON;

    public String getAAA() {
        return AAA;
    }

    public void setAAA(String AAA) {
        this.AAA = AAA;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getAON() {
        return AON;
    }

    public void setAON(String AON) {
        this.AON = AON;
    }
}
