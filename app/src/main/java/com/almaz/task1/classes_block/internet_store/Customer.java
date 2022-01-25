package com.almaz.task1.classes_block.internet_store;

import java.util.ArrayList;

    /*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */

public class Customer extends Person {
    private int id;
    private double accountBalance;

    public Customer(int id, String firstName, String lastName, double accountBalance) {
        super(firstName, lastName);
        this.id = id;
        this.accountBalance = accountBalance;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void payForOrder(Store store) {
        store.orderProccessing(this);
    }

    public void buyProduct(Store store, ArrayList<String> productNamesList) {
        store.orderTheProduct(this, productNamesList);
    }
}
