package net.braniumacademy;

public enum HomeTown {
    HA_NAM("Hà Nam"),
    HA_NOI("Hà Nội"),
    HAI_DUONG("Hải Dương"),
    VUNG_TAU("Vũng Tàu");

    private final String value;

    HomeTown(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
