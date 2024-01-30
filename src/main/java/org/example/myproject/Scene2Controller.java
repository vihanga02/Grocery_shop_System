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
    private final String[] categories = {"All", "Electronics", "Clothings"};

    @FXML
    private ChoiceBox<String> myChoiceBox;

    //List<Product> productList = new ArrayList<>();
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
    Product selectedProduct, selectedProduct1;

    public Scene2Controller() {
        WestministerShoppingManager manager = new WestministerShoppingManager();
        //this.productList = manager.loadFile();
        //setUpdatedList(productList);
        this.observableProductList = FXCollections.observableArrayList(ShoppingCart.getInstance().getUpdatedProductList());
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
            if (product instanceof Electronics electronicsProduct) {
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
            for (Product product: getUpdatedProductList()) {
                if (product instanceof Electronics) {
                    newProductList.add(product);
                }
            }
        }
        else if(category.equals("Clothings")){
            for (Product product: getUpdatedProductList()) {
                if(product instanceof Clothing){
                    newProductList.add(product);
                }
            }
        }
        else{
            newProductList = getUpdatedProductList();
        }
        observableProductList.setAll(newProductList);
    }

    public void switchingToScene3(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Gui2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        workingStage = stage;
        stage.show();
    }
    boolean flag = true;
    public void displaySelected() throws Exception{
        if (flag) {
            selectedProduct = tableView.getSelectionModel().getSelectedItem();
            selectedProduct1 = selectedProduct;
            flag = false;
        }
        else{
            selectedProduct = selectedProduct1;
            flag = true;
        }
        if (selectedProduct != null){
            idLabel.setText(selectedProduct.getId());
            catLabel.setText(selectedProduct.getClass().getName());
            nameLabel.setText(selectedProduct.getProductName());
            itemCountLabel.setText(Integer.toString(selectedProduct.getNumberOfProducts()));
            if (selectedProduct instanceof Electronics) {
                infoLabel.setText("Brand: " + ((Electronics) selectedProduct).getBrandName());
                infoLabel1.setText("Warrant Period: " + ((Electronics) selectedProduct).getWarrantyPeriod());
            }
            else {
                infoLabel.setText("Color: " + ((Clothing) selectedProduct).getColor());
                infoLabel1.setText("Size: " + ((Clothing) selectedProduct).getSize());
            }
        }
    }
    public void addToCart(ActionEvent event)throws Exception{
        if (selectedProduct != null){
            ShoppingCart cart = ShoppingCart.getInstance();
            List<Product> tempProductList = getUpdatedProductList();
            int ind = tempProductList.indexOf(selectedProduct);
            selectedProduct.setNumberOfProducts(-1);
            cart.add(selectedProduct);
            tempProductList.set(ind, selectedProduct);
            setUpdatedList(tempProductList);
            observableProductList.clear();
            observableProductList.setAll(getUpdatedProductList());
            displaySelected();
        }
    }
}