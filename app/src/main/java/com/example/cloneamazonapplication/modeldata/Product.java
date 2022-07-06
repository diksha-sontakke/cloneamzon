package com.example.cloneamazonapplication.modeldata;

import java.math.BigDecimal;

public class Product {


    private int pId;
    private String pName;
    private BigDecimal pPrice;
    private String pDescription;

     private String pQuantity;


    private String pImageName;



    //in constant package 3 related problem
    public Product(int pId, String pName, BigDecimal pPrice, String pDescription,String pQuantity,  String pImageName) {
        this.pId = pId;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDescription = pDescription;
        this.pImageName = pImageName;
        this.pQuantity=pQuantity;


    }

    public Product() {
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpImageName() {
        return pImageName;
    }

    public void setpImageName(String pImageName) {
        this.pImageName = pImageName;
    }

    public String getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(String pQuantity) {
        this.pQuantity = pQuantity;
    }
}
