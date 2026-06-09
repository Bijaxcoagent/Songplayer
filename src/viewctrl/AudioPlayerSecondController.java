package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import model.AudioPlayerModel;
import model.FileHandler;

public class AudioPlayerSecondController implements Initializable {

    private AudioPlayerModel model;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnChoose;

    @FXML
    private Button btnDir;

    @FXML
    private Button btnPath;

    @FXML
    private CheckBox cbInPlaylist;

    @FXML
    private Label lblDir;

    @FXML
    private Label lblError;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPath;

    @FXML
    private Label lblPlaylist;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField tfDir;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPath;

    @FXML
    void actionCancel(ActionEvent event) throws IOException {
        Main.loadScene("/viewctrl/main.fxml");
    }

    @FXML
    void actionDir(ActionEvent event) {
        tfDir.setText(model.chooseDirectory());
    }

    @FXML
    void actionPath(ActionEvent event) {
        tfPath.setText(model.chooseFile());
    }

    @FXML
    void actionChoose(ActionEvent event) {
        String directory = tfDir.getText();
        String nameSong = tfName.getText();
        String path = tfPath.getText();

        if (directory.isEmpty() && path.isEmpty()){
            lblError.setText("Es wurden keine MP3-Dateien ausgewählt!");
        }
        else{
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new AudioPlayerModel();
    }
}