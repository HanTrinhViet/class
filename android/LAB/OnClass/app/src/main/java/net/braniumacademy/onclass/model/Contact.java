package net.braniumacademy.onclass.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import net.braniumacademy.onclass.BR;

public class Contact extends BaseObservable {
    private static int autoId = 1000;
    private String id;
    private String name;
    private String number;
    private String imagePath;

    public Contact() {
    }

    public Contact(String id, String name, String number, String imagePath) {
        setId(id);
        this.name = name;
        this.number = number;
        this.imagePath = imagePath;
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (id == null || id.isEmpty() ? "CONTACT" + autoId : id);
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        notifyPropertyChanged(BR.number);
    }

    @Bindable
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        notifyPropertyChanged(BR.imagePath);
    }
}
