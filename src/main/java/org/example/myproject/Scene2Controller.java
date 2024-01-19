package org.example.myproject;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Scene2Controller implements Initializable {
    private String[] categories = {"All", "Electronics", "Clothings"};

    @FXML
    private ChoiceBox<String> myChoiceBox;

    List<Product> productList;
    WestministerShoppingManager manager;
    List<Product> newProductList = new ArrayList<>();
    ObservableList<Product> observableProductList;

    public Scene2Controller() {
        this.manager = new WestministerShoppingManager();
        this.productList = manager.loadFile();
        this.observableProductList = FXCollections.observableArrayList(productList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myChoiceBox.getItems().addAll(categories);
        myChoiceBox.setOnAction(this::getCategory);
    }
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
        observableProductList.setAll(newProductList);
    }

    public void switchingToScene3(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Gui2.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
