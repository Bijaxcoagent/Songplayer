package viewctrl;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.AudioPlayerModel;
import model.Song;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AudioPlayerController implements Initializable {
    private AudioPlayerModel apmodel;
    private boolean isPlaying = false;
    private Main main;
    private AnimationTimer clockTimer;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private Button btnAddSong;

    @FXML
    private Button btnPlaySong;

    @FXML
    private ImageView ivImage;

    @FXML
    private Label lblLocalTime;

    @FXML
    private Label lblSongAlbum;

    @FXML
    private Label lblSongArtist;

    @FXML
    private Label lblSongTime;

    @FXML
    private Label lblSongTitle;

    @FXML
    private ListView<Song> lvPlaylist;

    @FXML
    private ProgressBar pbProgress;

    @FXML
    void actionAddSong() throws IOException {
        Main.loadScene("/viewctrl/songAdd.fxml");
    }

    @FXML
    void actionNext() {
        apmodel.next();
    }

    @FXML
    void actionPrev() {
        apmodel.previous();
        updateSongInfo();
        isPlaying = true;
        btnPlaySong.setText("⏸");
    }

    public void addSong(Song song) {
        apmodel.addSong(song);
    }

    private void playSongAt(int index) {
        apmodel.playSongAt(index);

        apmodel.getMediaPlayer().setOnEndOfMedia(this::actionNext);

        updateSongInfo();
        isPlaying = true;
        btnPlaySong.setText("⏸");
    }

    private void updateSongInfo() {
        int index = apmodel.getCurrentIndex();
        if (index < 0) return;

        Song song = apmodel.getSongAt(index);
        lblSongTitle.setText(song.getTitle());
        lblSongArtist.setText(song.getArtist());
        lblSongAlbum.setText(song.getAlbum());

        if (song.getImage() != null) {
            ivImage.setImage(song.getImage());
        }

        lvPlaylist.getSelectionModel().select(index);
    }

    private void startClock() {
        clockTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                lblLocalTime.setText(LocalTime.now().format(timeFormatter));

                double current = apmodel.getCurrentTime();
                double total   = apmodel.getTotalTime();

                pbProgress.setProgress(total > 0 ? current / total : 0);
                lblSongTime.setText(formatTime(current) + " / " + formatTime(total));
            }
        };
    }

    private String formatTime(double seconds) {
        int total = (int) seconds;
        int minutes = total / 60;
        int secs = total % 60;
        return String.format("%d:%02d", minutes, secs);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> lvPlaylist.setItems(apmodel.getSongs()));

        apmodel = new AudioPlayerModel();
    }
}