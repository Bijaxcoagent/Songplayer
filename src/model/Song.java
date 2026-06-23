package model;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class Song {
    private final String title;
    private final Duration duration;
    private final Image image;
    private final String artist;
    private final String album;
    private final String filepath;

    public Song(String title, Duration duration, Image image, String artist, String album, String filepath) {
        this.title = title;
        this.duration = duration;
        this.image = image;
        this.artist = artist;
        this.album = album;
        this.filepath = filepath;
    }

    public String getTitle() {
        return title;
    }

    public Duration getDuration() {
        return duration;
    }

    public Image getImage() {
        return image;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getFilepath() {
        return filepath;
    }

    @Override
    public String toString() {
        return getTitle() + " - " + getArtist();
    }
}
