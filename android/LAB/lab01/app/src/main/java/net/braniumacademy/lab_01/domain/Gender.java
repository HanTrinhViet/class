package net.braniumacademy.lab_01.domain;

public enum Gender {
    MALE("Nam"),
    FEMALE("Nữ");


    public final String gender_vn;

    private Gender(String gender_vn) {
        this.gender_vn = gender_vn;
    }
}
