package model;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class FileHandler {
    private String filePath;
    private String fileDir;

    public String getFilePath() {
        return filePath;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void chooseFile(){
        final FileChooser fc = new FileChooser();
        fc.setTitle("MP3-Datei auswählen");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Dateien (*.mp3)", "*.mp3"));
        fc.setInitialDirectory(new File("."));
        File file = fc.showOpenDialog(null);

        if (file != null) {
            filePath = file.getAbsolutePath();
            //now open the file using a stream ...
        }
    }

    public void chooseDirectory(){
        final DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Ordner mit MP3-Dateien auswählen");
        File directory = dc.showDialog(null);

        File[] files = directory.listFiles();
        boolean isMP3 = false;

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().endsWith(".mp3")) {
                        isMP3 = true;
                        // hier kannst du die MP3 weiterverarbeiten
                    }
                }
            }
        }
        if (isMP3){
            fileDir = directory.getAbsolutePath();
        }
        else{
            fileDir = "In diesem Ordner sind keine MP3-Dateien enthalten";
        }
    }

    public void getSongFile(String pfad, String pfadDir){

    }
}
