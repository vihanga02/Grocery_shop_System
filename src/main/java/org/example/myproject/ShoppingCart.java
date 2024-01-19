package org.example.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private Map<Product ,Integer> productMap;
    private List<Product> productList;

    public ShoppingCart(/*List<Product> productList*/) {
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
        productList.remove(product);
        productMap.remove(product);
    }

    public double totalCost(){
        double cost = 0;
        for (Product product: productList){
            cost += product.getPrice();
        }
        return cost;
    }
}
