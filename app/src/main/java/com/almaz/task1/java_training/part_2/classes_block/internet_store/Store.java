package com.almaz.task1.java_training.part_2.classes_block.internet_store;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Store {
    private final String name;
    private final HashSet<Product> productsList;
    private final ArrayList<Order> customersOrdersList;
    private final ArrayList<Customer> blackList;

    public Store(String name) {
        this.name = name;
        this.productsList = new HashSet<>();
        this.customersOrdersList = new ArrayList<>();
        this.blackList = new ArrayList<>();
    }

    void addProduct(Product product) {
        this.productsList.add(product);
    }

    ArrayList<Order> getCustomerOrdersList(Customer customer) {
        return customersOrdersList.stream().
                filter(order -> order.getCustomerId() == customer.getId()).
                collect(Collectors.toCollection(ArrayList::new));
    }

    ArrayList<Order> getCustomersOrdersList() {
        return customersOrdersList;
    }

    void clearCustomersOrderList(ArrayList<Order> customerOrdersList) {
        customersOrdersList.removeAll(customerOrdersList);
    }

    ArrayList<Customer> getBlackList() {
        return blackList;
    }

    void addCustomerToBlackList(Customer customer) {
        this.blackList.add(customer);
    }

    public HashSet<Product> getProductsList() {
        return productsList;
    }

    public void orderTheProduct(Customer customer, ArrayList<String> productNamesList) {
        if (blackList.contains(customer)) {
            System.out.println("Customer " + customer.getLastName() + " is blacklisted!");
            return;
        }

        ArrayList<Product> orderedProducts = productsList.stream()
                .filter(product -> productNamesList.contains(product.getName())).
                        collect(Collectors.toCollection(ArrayList::new));
        if (orderedProducts.size() > 0) {
            customersOrdersList.add(new Order(orderedProducts,
                    orderedProducts.stream().mapToDouble(Product::getPrice).sum(),
                    customer.getId()));
        } else {
            System.out.println("The products " + productNamesList + " is not in the store. Sorry.");
        }

    }

    public void orderProccessing(Customer customer) {
        double totalCost = customersOrdersList.stream().
                mapToDouble(Order::getPrice).sum();
        if (customer.getAccountBalance() < totalCost) {
            System.out.println("Not enough money!");
        } else {
            customersOrdersList.stream().filter(order -> order.getCustomerId() == customer.getId()).
                    forEach(order -> order.setPayment(true));
            customer.setAccountBalance(customer.getAccountBalance() - totalCost);
        }
    }

    public static class Salesman extends Person {

        public Salesman(String firstName, String lastName) {
            super(firstName, lastName);
        }

        public void addProductToStore(Store store, Product product) {
            store.addProduct(product);
        }

        public void checkOrders(Store store, Customer customer) {
            long unpaidOrderCount;
            ArrayList<Order> customerOrderList = store.getCustomerOrdersList(customer);
            unpaidOrderCount = customerOrderList.stream().
                    filter(order -> !order.isPaid()).count();

            if (unpaidOrderCount > 0) {
                store.addCustomerToBlackList(customer);
                store.clearCustomersOrderList(customerOrderList);
                System.out.println("Order isn't paid!");
            } else {
                System.out.println("Customer " + customer.getLastName() + ", " +
                        "order paid. You can pick up the products from" + store.name + ". Shopping list:");
                customerOrderList.forEach(System.out::println);
                store.clearCustomersOrderList(customerOrderList);
            }
        }


    }
}