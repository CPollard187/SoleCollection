package com.example.codypollard.shoecollection.JavaBeans;

import android.os.Parcel;
import android.os.Parcelable;

public class Shoe implements Parcelable {

    private int id;
    private String name;
    private String brand;
    private String type;
    private String description;
    private String colourWay;
    private String condition;
    private String retailPrice;

    public Shoe(){

    }

    public Shoe(int id, String name, String brand, String type, String description, String colourWay, String condition, String retailPrice) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.colourWay = colourWay;
        this.condition = condition;
        this.retailPrice = retailPrice;
    }

    public Shoe(String name, String brand, String type, String description, String colourWay, String condition, String retailPrice) {
        this.name = name;
        this.brand = brand;
        this.type = type;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.brand);
        dest.writeString(this.type);
        dest.writeString(this.description);
        dest.writeString(this.colourWay);
        dest.writeString(this.condition);
        dest.writeString(this.retailPrice);
    }

    protected Shoe(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.brand = in.readString();
        this.type = in.readString();
        this.description = in.readString();
        this.colourWay = in.readString();
        this.condition = in.readString();
        this.retailPrice = in.readString();
    }

    public static final Parcelable.Creator<Shoe> CREATOR = new Parcelable.Creator<Shoe>() {
        @Override
        public Shoe createFromParcel(Parcel source) {
            return new Shoe(source);
        }

        @Override
        public Shoe[] newArray(int size) {
            return new Shoe[size];
        }
    };
}
