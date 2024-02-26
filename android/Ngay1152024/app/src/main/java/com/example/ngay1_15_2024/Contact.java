package com.example.ngay1_15_2024;

public class Contact {
    private static int autoId = 1000;
    private int id;
    private int images;
    private String name;
    private String phone;

    public Contact(int images, String name, String phone) {
        setId(0);
        this.images = images;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = (id == 0) ? autoId++ : id;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
