package org.example.myproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManage implements Serializable {
    private List<User> userList = new ArrayList<>();
    public UserManage() {
        this.loadFile();
    }

    public List<User> getUserList(){
        return userList;
    }
    public void saveUserList(){
        try{
            FileOutputStream data = new FileOutputStream("Data2.ser");
            ObjectOutputStream obj = new ObjectOutputStream(data);
            obj.writeObject(userList);
            obj.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<User> loadFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Data2.ser"))){
            List<User> pr = (List<User>) in.readObject();
            userList = pr;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("errorrrrrrrrrrrr");
            e.printStackTrace();
        }
        return userList;
    }

    public void setUserList(User user){
        this.userList.add(user);
        this.saveUserList();
        this.loadFile();
    }
}
