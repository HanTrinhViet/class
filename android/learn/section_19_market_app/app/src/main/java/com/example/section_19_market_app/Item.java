package com.example.section_19_market_app;

public class Item {
    private int itemImage;
    private String itemTitle;
    private String itemDescription;

    public Item() {
    }

    public Item(int itemImage, String itemTitle, String itemDescription) {
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
