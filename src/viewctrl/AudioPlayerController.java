package viewctrl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.AudioPlayerModel;
import model.Song;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AudioPlayerController implements Initializable {

    private AudioPlayerModel model;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML private Button btnPlaySong;
    @FXML private ImageView ivImage;
    @FXML private Label lblLocalTime;
    @FXML private Label lblSongAlbum;
    @FXML private Label lblSongArtist;
    @FXML private Label lblSongTime;
    @FXML private Label lblSongTitle;
    @FXML private ListView<Song> lvPlaylist;
    @FXML private ProgressBar pbProgress;

    @FXML
    void actionAddSong() throws IOException {
        Main.loadScene("/viewctrl/songAdd.fxml");
    }

    @FXML
    void actionPlayPause() {
        if (model.getMediaPlayer() == null) {
            int index = lvPlaylist.getSelectionModel().getSelectedIndex();

            if (index < 0) {
                index = 0;
            }

            if(model.getSongCount() == 0) {
                return;
            }

            playSongAt(index);
            return;
        }

        if (model.isPlaying()) {
            model.pause();
            btnPlaySong.setText("▶");
        } else {
            model.play();
            btnPlaySong.setText("⏸");
        }
    }

    @FXML
    void actionNext() {
        int nextIndex = model.getCurrentIndex() + 1;

        if (nextIndex < model.getSongCount()) {
            playSongAt(nextIndex);
        }
    }

    @FXML
    void actionPrev() {
        int prevIndex = model.getCurrentIndex() - 1;

        if (prevIndex >= 0) {
            playSongAt(prevIndex);
        }
    }

    private void playSongAt(int index) {
        model.playSongAt(index);
        model.getMediaPlayer().setOnEndOfMedia(() -> actionNext());
        updateSongInfo();
        btnPlaySong.setText("⏸");
    }

    private void updateSongInfo() {
        int index = model.getCurrentIndex();
        if (index < 0) return;

        Song song = model.getSongAt(index);

        lblSongTitle.setText(song.getTitle());
        lblSongArtist.setText(song.getArtist());
        lblSongAlbum.setText(song.getAlbum());

        if (song.getImage() != null) {
            ivImage.setImage(song.getImage());
        }

        lvPlaylist.getSelectionModel().select(index);
    }

    private void startClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateClock() {
        LocalTime now = LocalTime.now();
        String time = now.format(timeFormatter);
        lblLocalTime.setText(time);
        double currentTime = model.getCurrentTime();
        double totalTime = model.getTotalTime();
        double progress = 0;

        if(totalTime > 0) {
            progress = currentTime / totalTime;
        }

        pbProgress.setProgress(progress);
        String currentFormatted = formatTime(currentTime);
        String totalFormatted = formatTime(totalTime);
        lblSongTime.setText(currentFormatted + " / " + totalFormatted);
    }

    private String formatTime(double seconds) {
        int s = (int) seconds;
        return String.format("%d:%02d", s / 60, s % 60);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = AudioPlayerModel.getInstance();
        lvPlaylist.setItems(model.getSongs());
        startClock();
    }
}