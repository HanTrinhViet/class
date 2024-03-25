package net.braniumacademy.onclass.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import net.braniumacademy.onclass.BR;

import java.util.Objects;

public class Contact extends BaseObservable {
    private static int autoId = 1000;
    private int id;
    private String name;
    private String number;
    private String image;

    @BindingAdapter({"image"})
    public static void loadImage(ShapeableImageView imageView, String imagePath) {
        Glide.with(imageView.getContext()).load(imagePath).into(imageView);
    }

    public Contact() {
    }

    public Contact(int id, String name, String number, String image) {
        setId(id);
        this.name = name;
        this.number = number;
        this.image = image;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = (id == 0 ? autoId++ : id);
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
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
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
}
