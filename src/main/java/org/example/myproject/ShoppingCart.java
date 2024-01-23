package org.example.myproject;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    public Map<Product ,Integer> productMap;
    public List<Product> productList;
    private static ShoppingCart instance;
    private static User currentUser;
    public static Stage workingStage;
    public ShoppingCart() {
        this.productMap = new HashMap<>();
        this.productList = new ArrayList<>();
    }

    public void add(Product product){
        if (productMap.containsKey(product)){
            productMap.put(product,productMap.get(product)+1);
        }
        else{
            productMap.put(product,1);
            productList.add(product);
        }
    }

    public void remove(Product product){
        if (productMap.get(product) == 1){
            productList.remove(product);
            productMap.remove(product);
        }
        else if (productMap.get(product) != null){
            productMap.put(product, productMap.get(product) - 1);
        }
        else{
            System.out.println("mull");
        }

    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }
    public static User getCurrentUser(){
        return currentUser;
    }

    public double totalCost(){
        double cost = 0;
        for (Product product: productList){
            cost += product.getPrice();
        }
        return cost;
    }
}
