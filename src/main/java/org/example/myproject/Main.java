package org.example.myproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        ShopingManager manager1 = new WestministerShoppingManager();
        List<Product> productlist1 = manager1.loadFile();
        boolean next = true;
        while (next) {
            manager1.displayMenu();
            next = manager1.getState();
            List<Product> productList1 = manager1.getList();
            System.out.println(productList1.get(0).getNumberOfProducts());
        }
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.setTitle("Shopping Gui");
        stage.show();
    }
}
