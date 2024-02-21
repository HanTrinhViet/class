package net.braniumacademy.section_20_card_view_the_sports_app;

public class Sport {
    private String sportName;
    private int sportImage;

    public Sport() {
    }

    public Sport(String sportName, int sportImage) {
        this.sportName = sportName;
        this.sportImage = sportImage;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public int getSportImage() {
        return sportImage;
    }

    public void setSportImage(int sportImage) {
        this.sportImage = sportImage;
    }
}
