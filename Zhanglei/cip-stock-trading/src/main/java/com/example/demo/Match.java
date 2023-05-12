package com.example.demo;

public class Match {

    private Seller seller;

    private Buyer buyer;


    //constructor
    public Match(Buyer buyer, Seller seller) {
        this.buyer = buyer;
        this.seller = seller;
    }

    //getters and setters
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    public Seller getSeller() {
        return seller;
    }
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
    public Buyer getBuyer() {
        return buyer;
    }

}
