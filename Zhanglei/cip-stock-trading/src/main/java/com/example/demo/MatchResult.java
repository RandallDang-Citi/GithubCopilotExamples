package com.example.demo;

public class MatchResult {

    private String buyerId;

    private String sellerId;

    private int quantity;

    private double price;

    // getter && setter
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    public String getBuyerId() {
        return buyerId;
    }
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public String getSellerId() {
        return sellerId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

}
