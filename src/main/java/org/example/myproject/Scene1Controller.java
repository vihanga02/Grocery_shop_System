package org.example.myproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Scene1Controller extends ShoppingCart{
    private String userName;

    private String password;

    @FXML
    private Label msgLabel;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    UserManage userManage = new UserManage();
    public  Scene1Controller(){
        super();
    }

    public void login(ActionEvent event) throws Exception {
        userName = userNameField.getText();
        password = passwordField.getText();
        boolean flag = true;
        for (User user: userManage.getUserList()) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                Parent root = FXMLLoader.load(getClass().getResource("Gui1.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                User curCuser = ShoppingCart.getCurrentUser();
                curCuser = user;
                flag = false;
                break;
            }
        }
        if (flag){
            userNameField.clear();
            passwordField.clear();
            msgLabel.setText("Wrong user name or password.");
        }
    }

    public void singUp(ActionEvent event)throws Exception{
        userName = userNameField.getText();
        password = passwordField.getText();
        boolean flag = true;
        for (User user:userManage.getUserList()){
            if (user.getUserName().equals(userName)){
                msgLabel.setText("This account already exists");
                flag = false;
                break;
            }
        }
        if (flag){
            userManage.setUserList(new User(userName,password));
            Parent root = FXMLLoader.load(getClass().getResource("Gui1.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.show();
        }
    }
}