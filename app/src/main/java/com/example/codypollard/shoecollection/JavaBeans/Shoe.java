package com.example.codypollard.shoecollection.JavaBeans;

public class Shoe {

    private int id;
    private String name;
    private String description;
    private String colourWay;
    private String condition;
    private String retailPrice;

    public Shoe(){

    }

    public Shoe(int id, String name, String description, String colourWay, String condition, String retailPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colourWay = colourWay;
        this.condition = condition;
        this.retailPrice = retailPrice;
    }

    public Shoe(String name, String description, String colourWay, String condition, String retailPrice) {
        this.name = name;
        this.description = description;
        this.colourWay = colourWay;
        this.condition = condition;
        this.retailPrice = retailPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColourWay() {
        return colourWay;
    }

    public void setColourWay(String colourWay) {
        this.colourWay = colourWay;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }
}