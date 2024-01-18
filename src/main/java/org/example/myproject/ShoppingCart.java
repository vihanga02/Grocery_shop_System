package org.example.myproject;

import java.util.List;

public class ShoppingCart {
    private List<Product> productList;

    public ShoppingCart(List<Product> productList) {
        this.productList = productList;
    }

    public void add(Product product){
        productList.add(product);
    }

    public void remove(Product product){
        productList.remove(product);
    }

    public double totalCost(List<Product> productList){
        double cost = 0;
        for (Product product: productList){
            cost += product.getPrice();
        }
        return cost;
    }
}
