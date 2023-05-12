package com.example.demo;

public class Seller {
    private String clientId;
    private double quality;
    private boolean AON;

    //constructor
    public Seller(String clientId, double quality, boolean AON) {
        this.clientId = clientId;
        this.quality = quality;
        this.AON = AON;
    }

    // 构造函数和Getter/Setter方法省略
    //generate getter and setter for all fields
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getClientId() {
        return clientId;
    }
    public void setQuality(double quality) {
        this.quality = quality;
    }
    public double getQuality() {
        return quality;
    }
    public void setAON(boolean AON) {
        this.AON = AON;
    }
    public boolean getAON() {
        return AON;
    }
}
