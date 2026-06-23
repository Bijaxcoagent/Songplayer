package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import model.AudioPlayerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AudioPlayerSecondController implements Initializable {

    private AudioPlayerModel model;

    @FXML private Button btnCancel;
    @FXML private TextField tfDir;
    @FXML private TextField tfName;
    @FXML private TextField tfPath;
    @FXML private Label lblError;

    @FXML
    void actionPath(ActionEvent event) {
        String path = model.chooseFile();
        if (!path.isEmpty()) tfPath.setText(path);
    }

    @FXML
    void actionDir(ActionEvent event) {
        String dir = model.chooseDirectory();
        if (!dir.isEmpty()) tfDir.setText(dir);
    }

    @FXML
    void actionChoose(ActionEvent event) throws IOException {
        boolean success = model.addSelectedItems(tfName.getText());

        if (success) {
            lblError.setText("Song(s) erfolgreich hinzugefügt.");
            tfPath.clear();
            tfDir.clear();
            tfName.clear();
            Main.loadScene("/viewctrl/main.fxml");
        } else {
            lblError.setText("Bitte zuerst eine Datei oder einen Ordner auswählen.");
        }
    }

    @FXML
    void actionCancel(ActionEvent event) throws IOException {
        Main.loadScene("/viewctrl/main.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = AudioPlayerModel.getInstance();
    }
}