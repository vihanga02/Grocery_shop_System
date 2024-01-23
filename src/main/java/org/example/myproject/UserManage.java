package org.example.myproject;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UserManage implements Serializable {
    private List<User> userList;
    Scanner scanner = new Scanner(System.in);

    public UserManage() {

    }

    public List<User> getUserList(){
        return userList;
    }
    public void loadUsers(){
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

    public void addUser(){
        String name,password;
        name = scanner.next();
        password = scanner.next();
        userList.add(new User(name, password));
    }
}
