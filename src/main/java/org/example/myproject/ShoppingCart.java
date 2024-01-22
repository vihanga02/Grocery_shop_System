package org.example.myproject;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    public Map<Product ,Integer> productMap;
    public List<Product> productList;
    private static ShoppingCart instance;
    public Scene scene1, scene2, scene3;

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
        if (productMap.get(product) == 0){
            productList.remove(product);
            productMap.remove(product);
        }


    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public double totalCost(){
        double cost = 0;
        for (Product product: productList){
            cost += product.getPrice();
        }
        return cost;
    }
}
