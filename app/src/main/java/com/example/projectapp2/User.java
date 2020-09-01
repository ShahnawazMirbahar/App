package com.example.projectapp2;

import com.google.firebase.database.Exclude;

public class User {

    private String UserName;
    private String name;
    private String packageName;
    private String area;
    private String price;
    private String contact;
    private String key;
    private String payment;

    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public User() {
    }




    public User(String name, String packageName, String area, String price, String contact, String userName,String payment) {
        this.name = name;
        this.packageName = packageName;
        this.area = area;
        this.price = price;
        this.contact = contact;
        this.UserName=userName;
        this.payment=payment;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public String toString(){
       return this.name+"= Area= "+area+", Package= "+packageName+", price= "+price;
    }
}