package com.example.cloneamazonapplication.modeldata;

public class AddProductModel {

    String pid;
    String name;
    String price;
    String description;
    String category;
    String img;
    String date;
    String time;
    String quantity;  //dont add this

    public AddProductModel() {
    }

    public AddProductModel(String pid, String name, String price, String description, String category, String img, String date, String time,String quantity) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.img = img;
        this.date = date;
        this.time = time;
        this.quantity=quantity;

    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
