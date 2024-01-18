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
        List<Product> productList = new ArrayList<>();
        launch();
//        productList.add(new Electronics("abc", "MacbookAir", 2, 102, "Apple", 12));
//        productList.add(new Clothing("xyz", "MacbookAir", 1, 102, "red", 1));

//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Data1.ser"))){
//            List<Product> pr = (List<Product>) in.readObject();
//            productList = pr;
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("errorrrrrrrrrrrr");
//            e.printStackTrace();
//        }
//
//        boolean next = true;
//        while (next) {
//            ShopingManager manager1 = new WestministerShoppingManager(productList);
//            manager1.displayMenu();
//            next = manager1.getState();
//            productList = manager1.getList();
//
//            System.out.println(productList.get(0).getNumberOfProducts());
//        }
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
