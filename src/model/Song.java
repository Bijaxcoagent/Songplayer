package model;

import java.time.Duration;

public class Song {
    private String title;
    private Duration time;

    public Song(String title, Duration time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public Duration getTime() {
        return time;
    }

    @Override
    public String toString() {
        return title + ";" + time;
    }
}
