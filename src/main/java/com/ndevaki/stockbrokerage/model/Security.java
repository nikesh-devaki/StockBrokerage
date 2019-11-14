package com.ndevaki.stockbrokerage.model;

public class Security {
    private double qty;
    private double price;

    public Security() {
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate(){
        return qty*price;
    }
}
