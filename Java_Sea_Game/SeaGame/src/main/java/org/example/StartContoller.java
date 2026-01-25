package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartContoller {
    private Stage stage;
    private Game game;
    private GameController gameController;

    public StartContoller() {

    }

    public void init(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void OnStartButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/birdgame.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        stage.setScene(new Scene(root));

    }

    public GameController getGameController() {
        return gameController;
    }
}
