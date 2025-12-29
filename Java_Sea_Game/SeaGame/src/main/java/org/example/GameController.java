package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class GameController {
    private Game game;

    @FXML
    Text TimerText;

    @FXML
    AnchorPane globalpane;

    @FXML
    Canvas globalcanvas;

    public GameController(){
    }

    @FXML
    public void initialize(){
        this.game = new Game(globalcanvas);
        BirdLoop temploop = new BirdLoop(this);
        globalpane.setFocusTraversable(true);
        globalpane.addEventFilter(KeyEvent.KEY_PRESSED, e->game.onKeyPressed(e));
        globalpane.addEventFilter(KeyEvent.KEY_RELEASED, e->game.onKeyReleased(e));

        Platform.runLater(() -> globalpane.requestFocus());
        temploop.start();


    }

    public void render(){
        game.update();
    }

    public void updateMovement(double dt){
        game.updateMovement(dt);
    }

}
