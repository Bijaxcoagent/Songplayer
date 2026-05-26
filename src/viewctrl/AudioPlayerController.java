package viewctrl;

import javafx.fxml.Initializable;
import model.AudioPlayerModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AudioPlayerController implements Initializable {
    AudioPlayerModel apmodel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apmodel = new AudioPlayerModel();
    }
}
