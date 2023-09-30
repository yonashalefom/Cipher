package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage mainWindow = null;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        primaryStage.setTitle("Cipher");
        primaryStage.setScene(new Scene(root, 1200, 645));
        primaryStage.setMaxHeight(685);
        primaryStage.setMaxWidth(1200);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
