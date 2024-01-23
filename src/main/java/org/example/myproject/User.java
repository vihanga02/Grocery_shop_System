package org.example.myproject;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;

    public User(String userName, String password){
        this.password = password;
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public void setUserName(String name){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
