package net.braniumacademy.lab_02.model;

import java.util.Objects;

public class Contact {
    private static int autoId = 1001;
    private int id;
    private String name;
    private String phoneNumber;
    private boolean status;
    private String imageUri;

    public Contact() {
    }

    public Contact(String name, String phoneNumber, boolean status, String imageUri) {
        setId(0);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.imageUri = imageUri;
    }

    public Contact(int id, String name, String phoneNumber, boolean status) {
        setId(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = (id == 0) ? (autoId++) : id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", imageUri='" + imageUri + '\'' +
                '}';
    }
}
