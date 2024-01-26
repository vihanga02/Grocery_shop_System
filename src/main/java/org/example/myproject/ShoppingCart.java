package org.example.myproject;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    public Map<Product ,Integer> productMap;
    //private  List<Product> productList;
    private static List<Product> updatedProductList;
    public static Map<Product, Integer> updatedProductMap;
    private static ShoppingCart instance;
    private static User currentUser;
    public static Stage workingStage;

    public ShoppingCart() {
        WestministerShoppingManager manager = new WestministerShoppingManager();
        this.productMap = new HashMap<>();
        //this.productList = new ArrayList<>();
        this.setUpdatedList(manager.loadFile());
    }

    public void add(Product product){
        boolean flag = false;
        for (Product product1 : productMap.keySet()){
            if ((product1.getId()).equals(product.getId())){
                int count = productMap.get(product1);
                productMap.remove(product1);
                productMap.put(product, count + 1);
                flag = true;
                break;
            }
        }
        if (!flag){
            productMap.put(product,1);
            updatedProductList.add(product);
        }
    }
    public void remove(Product product){
        if (productMap.get(product) == 1){
            updatedProductList.remove(product);
            productMap.remove(product);
        }
        else if (productMap.get(product) != null){
            productMap.put(product, productMap.get(product) - 1);
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
    public static void setCurrentUser(User user){
        currentUser = user;
    }

    public double totalCost(){
        double cost = 0;
        for (Product product: updatedProductList){
            cost += product.getPrice();
        }
        return cost;
    }
    public void setUpdatedList(List<Product> lis){
        updatedProductList = lis;
    }

    public void setUpdatedMap(Map<Product , Integer> mapp){
        updatedProductMap = mapp;
    }

    public List<Product> getUpdatedProductList(){
        return updatedProductList;
    }
    public Map<Product, Integer> getUpdatedProductMap(){
        return updatedProductMap;
    }
}
