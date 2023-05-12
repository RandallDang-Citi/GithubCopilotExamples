package com.example.demo;

class Order {
    private String clientId;
    int quantity;

    public Order(String clientId, int quantity) {
        this.clientId = clientId;
        this.quantity = quantity;
    }

    public String getClientId() {
        return clientId;
    }

    public int getQuantity() {
        return quantity;
    }
}
