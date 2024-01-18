package org.example.myproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.nio.file.attribute.AttributeView;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Scene2Controller implements Initializable {
    private String[] categories = {"All", "Electronics", "Clothings"};
    @FXML
    private ChoiceBox<String> myChoiceBox;

    List<Product> productList;
    WestministerShoppingManager manager;
    public Scene2Controller() {
        this.manager = new WestministerShoppingManager();
        this.productList = manager.loadFile();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myChoiceBox.getItems().addAll(categories);
        myChoiceBox.setOnAction(this::getCategory);
    }


    List<Product> newProductList = new ArrayList<>();

    public void getCategory(ActionEvent event){
        String category = myChoiceBox.getValue();
        if (category.equals("Electronics")){
            for (Product product: productList){
                if (product instanceof Electronics){
                    newProductList.add(product);
                }
            }
            System.out.println("el");
        }
        else if(category.equals("Clothings")){
            for (Product product: productList) {
                if(product instanceof Clothing){
                    newProductList.add(product);
                }
            }
            System.out.println("cl");
        }
        else{
            newProductList = productList;
            System.out.println("all");
        }
    }
}
