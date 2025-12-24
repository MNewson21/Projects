package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class Game {
    private Canvas globalcanvas;
    private GameCanvas canvas;

    public Game(Canvas globalcanvas){
        this.globalcanvas = globalcanvas;
        canvas = new GameCanvas(globalcanvas, this);

    }

    public void update(){
        canvas.render();
    }


    public void onKeyPressed(KeyEvent e) {
        System.out.println("onKeyPressed");
        canvas.turtle.onKeyPressed(e);
    }
    public void onKeyReleased(KeyEvent e) {
        canvas.turtle.onKeyReleased(e);
    }

}
