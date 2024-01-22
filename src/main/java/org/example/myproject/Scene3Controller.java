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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
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
    private Button backToSelection;
    @FXML
    private Button quit;

    private final ObservableList<Map.Entry<Product, Integer>> observableMap;
    ShoppingCart cart = ShoppingCart.getInstance();

    public Scene3Controller(){
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
            System.out.println(quantity);
            return new SimpleStringProperty(Double.toString(totalPrice));
        });

        tableView.setItems(observableMap);
    }

    public void backToShop(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Gui1.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void removeASelected(ActionEvent event) throws Exception{
        Product selected = tableView.getSelectionModel().getSelectedItem().getKey();
        cart.remove(selected);
    }
}
