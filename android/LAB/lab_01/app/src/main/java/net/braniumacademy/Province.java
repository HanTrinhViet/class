package net.braniumacademy;

/**
 * Enum class present some province in Viet Nam
 */
public enum Province {
    HA_NAM("Hà Nam"),
    HA_NOI("Hà Nội"),
    HAI_DUONG("Hải Dương"),
    VUNG_TAU("Vũng Tàu");

    private final String value;

    Province(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
