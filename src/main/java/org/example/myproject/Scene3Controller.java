package org.example.myproject;

import javafx.beans.property.*;
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
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Scene3Controller extends ShoppingCart implements Initializable{
    @FXML
    private TableColumn<Map.Entry<Product, Integer>, String> priceColumn;
    @FXML
    private TableColumn<Map.Entry<Product, Integer>, String> productColumn;
    @FXML
    private TableColumn<Map.Entry<Product, Integer>, Integer> quantityColumn;
    @FXML
    private TableView<Map.Entry<Product, Integer>> tableView;
    @FXML
    private Label totalLabel;
    @FXML
    private Label PPDLabel;
    @FXML
    private Label TIISCDLabel;
    @FXML
    private Label FinalTotalLabel;
    Stage stage;
    private int electronicCount;
    private int clothingCount;

    private ObservableList<Map.Entry<Product, Integer>> observableMap;
    ShoppingCart cart = ShoppingCart.getInstance();
    WestministerShoppingManager manager = new WestministerShoppingManager();

    public Scene3Controller() {
        super();
        this.observableMap = FXCollections.observableArrayList(cart.productMap.entrySet());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productColumn.setCellValueFactory(cellData -> {
            Product selected = cellData.getValue().getKey();
            if (selected instanceof Electronics){
                return new SimpleStringProperty(selected.getProductName() + "\nElectronics\n" + ((Electronics) selected).getBrandName() + ", " + ((Electronics) selected).getWarrantyPeriod());
            }
            else{
                return new SimpleStringProperty(selected.getProductName() + "\nClothing\n" + ((Clothing) selected).getSize() + ", " + ((Clothing) selected).getColor());
            }
        });
        productColumn.setStyle("-fx-alignment: Center;");

        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getValue()).asObject());

        priceColumn.setCellValueFactory(cellData -> {
            Double onePrice = cellData.getValue().getKey().getPrice();
            int quantity = cellData.getValue().getValue();
            Double totalPrice = onePrice*quantity;
            return new SimpleStringProperty(Double.toString(totalPrice));
        });
        tableView.setItems(observableMap);
        updateCategoryCount();
        displayTotal();
    }

    public void updateCategoryCount(){
        for (Map.Entry<Product, Integer> entry : cart.productMap.entrySet()) {
            if (entry.getKey() instanceof Electronics){
                electronicCount += entry.getValue();
            }
            else{
                clothingCount += entry.getValue();
            }
        }
    }

    public void backToShop(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Gui1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        workingStage = stage;
        stage.show();
    }

    public void removeASelected(ActionEvent event) throws Exception{
        Product selected = tableView.getSelectionModel().getSelectedItem().getKey();
        cart.remove(selected);
        selected.setNumberOfProducts(1);
        List<Product> tempList = cart.getUpdatedProductList();
        tempList.set(tempList.indexOf(selected), selected);
        cart.setUpdatedList(tempList);
        observableMap.clear();
        observableMap.setAll(cart.productMap.entrySet());
    }

    public void quitToExit(ActionEvent event) throws Exception{
        if (workingStage != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit Alert");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure, do you want to quit?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    super.workingStage.close();
                    ShoppingCart.getCurrentUser().setBuysCount();
                    manager.saveInAFile(cart.getUpdatedProductList());
                }
            });
        }
    }
    public void displayTotal(){
        Double cost = cart.totalCost();
        DecimalFormat df = new DecimalFormat("#.###");
        boolean flag1 = true;
        boolean flag2 = true;
        int count = ShoppingCart.getCurrentUser().getBuysCount();
        totalLabel.setText(Double.toString(cost));
        if ((clothingCount > 3) || (electronicCount > 3)){
            TIISCDLabel.setText("-" + cost*0.2);
            flag1 = false;
        }
        else {
            TIISCDLabel.setText("0.00");
        }

        if (count == 0){
            PPDLabel.setText("-" + cost*0.1);
            flag2 = false;
        }

        if (flag1 && flag2){
            FinalTotalLabel.setText(Double.toString(cost*0.7));
        }
        else if(flag1 && !flag2){
            FinalTotalLabel.setText(Double.toString(cost*0.8));
        } else if (!flag1 && flag2){
            FinalTotalLabel.setText(Double.toString(cost*0.9));
        }
        else {
            FinalTotalLabel.setText(Double.toString(cost));
        }
    }
}