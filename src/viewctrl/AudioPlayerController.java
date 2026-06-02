package viewctrl;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import model.AudioPlayerModel;
import model.Song;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AudioPlayerController implements Initializable {
    private AudioPlayerModel apmodel;
    private Main main;
    private AnimationTimer clockTimer;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private Button btnAddSong;

    @FXML
    private ImageView ivImage;

    @FXML
    private Label lblLocalTime;

    @FXML
    private Label lblSongTime;

    @FXML
    private Label lblSongTitle;

    @FXML
    private ListView<String> lvPlaylist;

    @FXML
    private ProgressBar pbProgress;

    @FXML
    void actionAddSong(ActionEvent event) throws IOException {
        Main.loadScene("/viewctrl/songAdd.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apmodel = new AudioPlayerModel();
    }
}