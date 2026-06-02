package model;

import javafx.scene.image.Image;

import java.time.Duration;

public class Song {
    private String title;
    private Duration time;
    private Image logo;

    public Song(String title, Duration time, Image logo) {
        this.title = title;
        this.time = time;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public Duration getTime() {
        return time;
    }

    public Image getLogo() {
        return logo;
    }

    @Override
    public String toString() {
        return title + ";" + time;
    }
}
