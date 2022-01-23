package com.almaz.task1.classes_block.internet_store;

import androidx.annotation.NonNull;

public class Order {
    private String productName;
    private int customerId;
    private boolean payment;
    private double price;

    public Order(String productName, double price, int customerId) {
        this.productName = productName;
        this.customerId = customerId;
        this.payment = false;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getProductName();
    }
}
