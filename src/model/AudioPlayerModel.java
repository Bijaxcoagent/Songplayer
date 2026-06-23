package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class AudioPlayerModel {

    private static final AudioPlayerModel instance =
            new AudioPlayerModel();

    public static AudioPlayerModel getInstance() {
        return instance;
    }

    private AudioPlayerModel() {
    }

    private ObservableList<Song> songs = FXCollections.observableArrayList();
    private MediaPlayer mediaPlayer;
    private int currentIndex = -1;
    private PlayerFileHandler fh = new PlayerFileHandler();
    private File selectedFile;
    private File selectedDirectory;

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public String chooseFile() {
        selectedFile = fh.chooseFile();
        if (selectedFile == null) {
            return "";
        }

        return selectedFile.getAbsolutePath();
    }

    public String chooseDirectory() {
        selectedDirectory = fh.chooseDirectory();
        if (selectedDirectory == null) {
            return "";
        }

        return selectedDirectory.getAbsolutePath();
    }

    public boolean addSelectedItems(String songName) {
        boolean added = false;

        if (selectedFile != null) {
            if (songName == null || songName.isEmpty()) {
                songName = selectedFile.getName();
            }

            Song song = new Song(songName, null, null, "Unbekannt", "Unbekannt", selectedFile.getAbsolutePath());
            songs.add(song);
            added = true;
        }

        if (selectedDirectory != null) {
            File[] files = selectedDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {
                        Song song = new Song(file.getName(), null, null, "Unbekannt", "Unbekannt", file.getAbsolutePath());
                        songs.add(song);
                        added = true;
                    }
                }
            }
        }

        selectedFile = null;
        selectedDirectory = null;
        return added;
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
        if (mediaPlayer != null)
            mediaPlayer.play();
    }

    public void pause() {
        if (mediaPlayer != null)
            mediaPlayer.pause();
    }

    public void stop() {
        if (mediaPlayer != null)
            mediaPlayer.stop();
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

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public boolean isPlaying() {

        return mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    public double getCurrentTime() {

        if (mediaPlayer == null)
            return 0;

        return mediaPlayer
                .getCurrentTime()
                .toSeconds();
    }

    public double getTotalTime() {

        if (mediaPlayer == null)
            return 1;

        Duration total = mediaPlayer.getTotalDuration();

        return total != null ? total.toSeconds() : 1;
    }
}
