package org.example.myproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Scene1Controller {
    private String userName;

    private String password;

    @FXML
    private Button loginButton;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    User user = new User("pakaya", "123");


    public void login(ActionEvent event) throws Exception {
        userName = userNameField.getText();
        password = passwordField.getText();

        if (userName.equals(user.getUserName()) && password.equals(user.getPassword())){
            System.out.println("ok");
        }
        else{
            userNameField.clear();
            passwordField.clear();
        }
    }
}
