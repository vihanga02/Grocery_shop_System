package org.example.myproject;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String productName;
    private int numberOfProducts;
    private double price;

    public Product(String id, String name, int nOp ,double price){
        this.id = id;
        this.productName = name;
        this.numberOfProducts++;
        this.price = price;
    }

    public Product() {
        this.numberOfProducts++;
    }

    public String getId(){
        return id;
    }

    public String getProductName(){
        return productName;
    }

    public int getNumberOfProducts(){
        return numberOfProducts;
    }

    public double getPrice(){
        return price;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public void setNumberOfProducts(int newNumberOfProducts){
        this.numberOfProducts = numberOfProducts + newNumberOfProducts;
    }

    public void setPrice(double price){
        this.price = price;
    }
}
