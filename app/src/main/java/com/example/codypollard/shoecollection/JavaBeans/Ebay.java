package com.example.codypollard.shoecollection.JavaBeans;

import android.media.Image;

public class Ebay {

    private String name;
    private String itemId;
    private Image image;

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
