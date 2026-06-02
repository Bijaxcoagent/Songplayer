package viewctrl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AudioPlayerSecondController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}