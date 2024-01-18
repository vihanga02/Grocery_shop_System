package org.example.myproject;

import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String color;
    private double size;
    public Clothing(String id, String name, int nOp, double price, String colour, double size){
        super(id, name, nOp, price);
        this.color = colour;
        this.size = size;
    }

    public String getColor(){
        return color;
    }

    public double getSize(){
        return size;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setSize(double size){
        this.size = size;
    }
}