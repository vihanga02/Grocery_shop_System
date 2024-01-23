package org.example.myproject;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Scene2Controller extends ShoppingCart implements Initializable {
    private String[] categories = {"All", "Electronics", "Clothings"};

    @FXML
    private ChoiceBox<String> myChoiceBox;

    List<Product> productList;
    WestministerShoppingManager manager;
    Stage stage;

    List<Product> newProductList = new ArrayList<>();
    @FXML
    Label idLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label catLabel;
    @FXML
    Label itemCountLabel;
    @FXML
    Label infoLabel;
    @FXML
    Label infoLabel1;


    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, String> id;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, String> cat;
    @FXML
    private TableColumn<Product, Double> price;
    @FXML
    private TableColumn<Product, String> info;
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

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("productName"));

        cat.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            if (product instanceof Electronics){
                return new ReadOnlyStringWrapper("Electronic");
            }
            else{
                return new ReadOnlyStringWrapper("Clothing");
            }
        });

        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        info.setCellValueFactory(cellData -> {
            Product product = cellData.getValue();
            if (product instanceof Electronics) {
                Electronics electronicsProduct = (Electronics) product;
                return new ReadOnlyStringWrapper("Brand: " + electronicsProduct.getBrandName() + ", Warranty: " + electronicsProduct.getWarrantyPeriod());
            }
            else {
                Clothing clothingProduct = (Clothing) product;
                return new ReadOnlyStringWrapper("Size: " + clothingProduct.getSize() + ", Color: " + clothingProduct.getColor());
            }
        });
        tableView.setItems(observableProductList);
    }
    public void getCategory(ActionEvent event){
        String category = myChoiceBox.getValue();
        newProductList.clear();
        if (category.equals("Electronics")){
            for (Product product: productList) {
                if (product instanceof Electronics) {
                    newProductList.add(product);
                }
            }
        }
        else if(category.equals("Clothings")){
            for (Product product: productList) {
                if(product instanceof Clothing){
                    newProductList.add(product);
                }
            }
        }
        else{
            newProductList = productList;
        }
        observableProductList.setAll(newProductList);
    }

    public void switchingToScene3(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Gui2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        super.workingStage = stage;
        stage.show();
    }

    private Product selectedObject;
    public void displaySelected() throws Exception{
        Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null){
            idLabel.setText("Product ID: " + selectedProduct.getId());
            catLabel.setText("Category: " + selectedProduct.getClass().getName());
            nameLabel.setText("Name: " + selectedProduct.getProductName());
            itemCountLabel.setText("Items Available: " + selectedProduct.getNumberOfProducts());
            if (selectedProduct instanceof Electronics) {
                infoLabel.setText("Brand: " + ((Electronics) selectedProduct).getBrandName());
                infoLabel1.setText("Warranty Period: " + ((Electronics) selectedProduct).getWarrantyPeriod());
            }
            else {
                infoLabel.setText("Color: " + ((Clothing) selectedProduct).getColor());
                infoLabel1.setText("Size: " + ((Clothing) selectedProduct).getSize());
            }
        }
        selectedObject = selectedProduct;
    }

    public void addToCart(ActionEvent event)throws Exception{
        ShoppingCart cart = ShoppingCart.getInstance();
        cart.add(selectedObject);
    }
}