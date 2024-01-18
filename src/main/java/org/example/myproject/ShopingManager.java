package org.example.myproject;

import java.util.List;

public interface ShopingManager {

    void displayMenu();
    void addAProduct();
    void deleteProduct();
    void saveInAFile();
    void printTheProductList();
    List<Product> getList();
    boolean getState();
}