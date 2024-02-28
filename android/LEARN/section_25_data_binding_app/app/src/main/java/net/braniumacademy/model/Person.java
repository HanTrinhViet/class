package net.braniumacademy.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import net.braniumacademy.BR;

public class Person extends BaseObservable {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
