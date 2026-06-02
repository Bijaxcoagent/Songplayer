package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/viewctrl/main.fxml"));
        scene = new Scene(root);
        primaryStage.setTitle("Audioplayer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void loadScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxml));
        scene.setRoot(root);
    }
}
