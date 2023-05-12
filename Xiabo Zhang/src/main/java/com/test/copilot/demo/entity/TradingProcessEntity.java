package com.test.copilot.demo.entity;

import lombok.Data;

@Data
public class TradingProcessEntity {
    private String client;
    private String type;
    private Integer size;
    private String AON;
    private Boolean complete;
}
