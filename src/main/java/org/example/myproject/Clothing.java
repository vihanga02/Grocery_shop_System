package org.example.myproject;

import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String color;
    private String size;
    public Clothing(String id, String name, int nOp, double price, String colour, String size){
        super(id, name, nOp, price);
        this.color = colour;
        this.size = size;
    }

    public String getColor(){
        return color;
    }

    public String getSize(){
        return size;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setSize(String size){
        this.size = size;
    }
}