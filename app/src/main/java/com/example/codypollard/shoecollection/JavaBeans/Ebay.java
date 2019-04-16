package com.example.codypollard.shoecollection.JavaBeans;

/**
 * Author = Cody Pollard
 * Date = 2019
 */




public class Ebay {

    private int id;
    private String name;
    private String itemId;
    private String image;

    public Ebay (){

    }

    public Ebay(String name, String itemId, String image) {
        this.name = name;
        this.itemId = itemId;
        this.image = image;
    }
    public Ebay(int id, String name, String itemId, String image) {
        this.id = id;
        this.name = name;
        this.itemId = itemId;
        this.image = image;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
