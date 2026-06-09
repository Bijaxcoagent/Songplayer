package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FileHandler;

public class AudioPlayerModel {

    private ObservableList<String> liste = FXCollections.observableArrayList();
    private FileHandler fh = new FileHandler();

    public void getData(String pfad, String pfadDir, String name){
        fh.getSongFile(pfad, pfadDir);
        liste.add(name);
    }

    public String chooseFile(){
        fh.chooseFile();
        return fh.getFilePath();
    }

    public String chooseDirectory(){
        fh.chooseDirectory();
        return fh.getFileDir();
    }
}
