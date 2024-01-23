package org.example.myproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene1Controller extends ShoppingCart{
    private String userName;

    private String password;

    @FXML
    private Button loginButton;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    User user = new User("1", "1");
    UserManage userManage = new UserManage();
    public  Scene1Controller(){
        super();
    }

    public void login(ActionEvent event) throws Exception {
        userName = userNameField.getText();
        password = passwordField.getText();
        if (userName.equals(user.getUserName()) && password.equals(user.getPassword())){
            Parent root = FXMLLoader.load(getClass().getResource("Gui1.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene( new Scene(root));
            stage.show();
        }
        else{
            userNameField.clear();
            passwordField.clear();
        }
    }

    public void singUp(){
        userName = userNameField.getText();
        password = passwordField.getText();

    }
}