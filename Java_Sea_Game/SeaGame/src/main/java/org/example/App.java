package org.example;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class App extends Application {
    private Stage stage;
    private StartContoller controller;

    public App() throws IOException {

    }




    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/birdmain.fxml"));
        Parent root = fxmlLoader.load();

        controller = fxmlLoader.getController();
        controller.init(stage);

        stage.setScene(new Scene(root));
        stage.show();


    }

    public StartContoller getController() {
        return controller;
    }
}
