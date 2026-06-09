package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class AudioPlayerModel {
    private FileHandler fh = new FileHandler();
    private ObservableList<Song> songs = FXCollections.observableArrayList();
    private MediaPlayer mediaPlayer;
    private int currentIndex = -1;

    public void addSong(Song song) {
        songs.add(song);
    }

    public String chooseFile(){
        fh.chooseFile();
        return fh.getFilePath();
    }

    public String chooseDirectory(){
        fh.chooseDirectory();
        return fh.getFileDir();
    }

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public Song getSongAt(int index) {
        return songs.get(index);
    }

    public int getSongCount() {
        return songs.size();
    }

    public void playSongAt(int index) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        currentIndex = index;
        Song song = songs.get(index);
        Media media = new Media(new File(song.getFilepath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void play() {
        if (mediaPlayer != null) mediaPlayer.play();
    }

    public void pause() {
        if (mediaPlayer != null) mediaPlayer.pause();
    }

    public void stop() {
        if (mediaPlayer != null) mediaPlayer.stop();
    }

    public void next() {
        if (currentIndex < songs.size() - 1)
            playSongAt(currentIndex + 1);
    }

    public void previous() {
        if (currentIndex > 0)
            playSongAt(currentIndex - 1);
    }

    public void seek(double seconds) {
        if (mediaPlayer != null)
            mediaPlayer.seek(Duration.seconds(seconds));
    }

    public MediaPlayer getMediaPlayer() { return mediaPlayer; }
    public int getCurrentIndex() { return currentIndex; }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    public double getCurrentTime() {
        if (mediaPlayer == null) return 0;
        return mediaPlayer.getCurrentTime().toSeconds();
    }

    public double getTotalTime() {
        if (mediaPlayer == null) return 1;
        Duration total = mediaPlayer.getTotalDuration();
        return total != null ? total.toSeconds() : 1;
    }
}