package model;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class PlayerFileHandler {

    public File chooseFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("MP3-Datei auswählen");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Dateien", "*.mp3"));

        return fc.showOpenDialog(null);
    }

    public File chooseDirectory() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Ordner mit MP3-Dateien auswählen");

        return dc.showDialog(null);
    }
}