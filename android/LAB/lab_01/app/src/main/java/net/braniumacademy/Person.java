package net.braniumacademy;

import androidx.annotation.NonNull;

/**
 * Person class to present data of person
 */
public class Person {
    private String fullName;
    private String phoneNumber;
    private String gender;
    private String hobby;
    private String homeTown;
    private String imageUri;

    public Person() {
    }


    public Person(String fullName, String phoneNumber, String gender, String hobby, String homeTown) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.hobby = hobby;
        this.homeTown = homeTown;
    }

    public Person(String fullName, String phoneNumber, String gender, String hobby, String homeTown, String imageUri) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.hobby = hobby;
        this.homeTown = homeTown;
        this.imageUri = imageUri;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @NonNull
    @Override
    public String toString() {
        return fullName + " - " + phoneNumber + " - "  + gender + " - " + homeTown + " - " + hobby;
    }
}
