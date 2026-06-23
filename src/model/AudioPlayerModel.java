package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

public class AudioPlayerModel {

    private static final AudioPlayerModel instance = new AudioPlayerModel();
    public static AudioPlayerModel getInstance() { return instance; }

    private final ObservableList<Song> songs = FXCollections.observableArrayList();
    private MediaPlayer mediaPlayer;
    private int currentIndex = -1;
    private final PlayerFileHandler fh = new PlayerFileHandler();
    private File selectedFile;
    private File selectedDirectory;

    public ObservableList<Song> getSongs() { return songs; }
    public void addSong(Song song) { songs.add(song); }
    public Song getSongAt(int index) { return songs.get(index); }
    public int getSongCount() { return songs.size(); }
    public int getCurrentIndex() { return currentIndex; }
    public MediaPlayer getMediaPlayer() { return mediaPlayer; }

    public String chooseFile() {
        selectedFile = fh.chooseFile();
        return selectedFile != null ? selectedFile.getAbsolutePath() : "";
    }

    public String chooseDirectory() {
        selectedDirectory = fh.chooseDirectory();
        return selectedDirectory != null ? selectedDirectory.getAbsolutePath() : "";
    }

    public boolean addSelectedItems(String songName) {
        boolean added = false;

        if (selectedFile != null) {
            if (songName == null || songName.isEmpty())
                songName = selectedFile.getName();
            songs.add(new Song(songName, null, null, "Unbekannt", "Unbekannt",
                    selectedFile.getAbsolutePath()));
            added = true;
        }

        if (selectedDirectory != null) {
            File[] files = selectedDirectory.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile() && f.getName().toLowerCase().endsWith(".mp3")) {
                        songs.add(new Song(f.getName(), null, null, "Unbekannt", "Unbekannt",
                                f.getAbsolutePath()));
                        added = true;
                    }
                }
            }
        }

        selectedFile = null;
        selectedDirectory = null;
        return added;
    }

    public void playSongAt(int index) {
        if (index < 0 || index >= songs.size()) return; // FIX: Bounds-Check

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        currentIndex = index;
        String path = songs.get(index).getFilepath();
        mediaPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
        mediaPlayer.play();
    }

    public void play() { if (mediaPlayer != null) mediaPlayer.play(); }
    public void pause() { if (mediaPlayer != null) mediaPlayer.pause(); }
    public void stop() { if (mediaPlayer != null) mediaPlayer.stop(); }

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

    public boolean isPlaying() {
        return mediaPlayer != null &&
                mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    public double getCurrentTime() {
        return mediaPlayer != null ? mediaPlayer.getCurrentTime().toSeconds() : 0;
    }

    public double getTotalTime() {
        if (mediaPlayer == null) return 1;
        Duration d = mediaPlayer.getTotalDuration();
        return d != null ? d.toSeconds() : 1;
    }
}