package org.example.myproject;

import java.io.Serializable;

public class Electronics extends Product implements Serializable {
    private String brandName;
    private int warrantyPeriod;
    public Electronics(String id, String name, int nOp, double price, String brandName, int warrantyPeriod){
        super(id, name, nOp, price);
        this.brandName = brandName;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrandName(){
        return brandName;
    }

    public int getWarrantyPeriod(){
        return warrantyPeriod;
    }

    public void setBrandName(String brandName){
        this.brandName = brandName;
    }

    public void setWarrantyPeriod(int warrantyPeriod){
        this.warrantyPeriod = warrantyPeriod;
    }
}

