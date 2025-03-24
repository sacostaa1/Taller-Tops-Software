package com.docencia.clase.models;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private long id;
    private String model;
    private double price;
    private List<String> purchaseHistory = new ArrayList<>(); 

    
    public Car() {}

    
    public Car(long id, String model, double price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<String> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }
}
