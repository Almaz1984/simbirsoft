package com.almaz.task1.java_training.part_2.classes_block.internet_store;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Order {
    private ArrayList<Product> productsList;
    private int customerId;
    private boolean payment;
    private double price;

    public Order(ArrayList<Product> productsList, double price, int customerId) {
        this.productsList = productsList;
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

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
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
        return this.productsList.stream().map(Object::toString).
                collect(Collectors.joining("\n"));
    }
}
