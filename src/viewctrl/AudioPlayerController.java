package viewctrl;

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

import java.net.URL;
import java.util.ResourceBundle;

public class AudioPlayerController implements Initializable {
    AudioPlayerModel apmodel;

    private Main main;

    @FXML
    private Button btnAddSong;

    @FXML
    private ImageView ivImage;

    @FXML
    private Label lblGlobalTime;

    @FXML
    private Label lblSongTime;

    @FXML
    private Label lblSongTitle;

    @FXML
    private ListView<Song> lvPlaylist;

    @FXML
    private ProgressBar pbProgress;

    @FXML
    void actionAddSong(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apmodel = new AudioPlayerModel();
    }
}
