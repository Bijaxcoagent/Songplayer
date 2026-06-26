package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

public class AudioPlayerModel {

    private static final AudioPlayerModel instance = new AudioPlayerModel();

    public static AudioPlayerModel getInstance() {
        return instance;
    }

    private AudioPlayerModel() {}

    private ObservableList<Song> songs = FXCollections.observableArrayList();
    private MediaPlayer mediaPlayer;
    private int currentIndex = -1;
    private PlayerFileHandler fh = new PlayerFileHandler();
    private File selectedFile;
    private File selectedDirectory;

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public String chooseFile() {
        selectedFile = fh.chooseFile();
        if(selectedFile == null) {
            return "";
        }
        return selectedFile.getAbsolutePath();
    }

    public String chooseDirectory() {
        selectedDirectory = fh.chooseDirectory();
        if(selectedDirectory == null){
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

            songs.add(new Song(songName, null, null, "Unbekannt", "Unbekannt", selectedFile.getAbsolutePath()));
            added = true;
        }

        if (selectedDirectory != null) {
            File[] files = selectedDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {
                        songs.add(new Song(file.getName(), null, null, "Unbekannt", "Unbekannt", file.getAbsolutePath()));
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
        String path = song.getFilepath();
        File file = new File(path);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void play() {
        if(mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void pause() {
        if (mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public boolean isPlaying() {
        if (mediaPlayer == null) {
            return false;
        }
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    public double getCurrentTime() {
        if(mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentTime().toSeconds();
    }

    public double getTotalTime() {
        if(mediaPlayer == null) {
            return 1;
        }

        Duration total = mediaPlayer.getTotalDuration();
        if(total == null) {
            return 1;
        }
        return total.toSeconds();
    }
}