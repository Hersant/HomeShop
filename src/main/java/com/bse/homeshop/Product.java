package com.bse.homeshop;

public class Product {
    private String name;
    private String description;
    private double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Display a full description of the product
     */
    public void look() {
        System.out.println(String.format(name + " : " + price + "%n" + description));
    }

    /**
     * Add the product to a bill
     * @param bill The customer's bill
     * @param quantity The quantity ot the product
     */
    public void buy(Bill bill, Integer quantity) {

    }
}
