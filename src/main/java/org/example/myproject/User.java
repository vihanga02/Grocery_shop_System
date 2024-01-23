package org.example.myproject;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private int buysCount;

    public User(String userName, String password){
        this.password = password;
        this.userName = userName;
        this.buysCount = 0;
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

    public int getBuysCount() {
        return buysCount;
    }
    public void setBuysCount(){
        this.buysCount++;
    }
}
