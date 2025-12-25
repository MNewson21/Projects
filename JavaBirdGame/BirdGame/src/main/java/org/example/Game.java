package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.awt.*;

public class Game {
    private Canvas globalcanvas;
    private GameCanvas canvas;
    private ImageHolder imageholder;
    private EntityController entityController;

    public Game(Canvas globalcanvas){
        this.globalcanvas = globalcanvas;
        canvas = new GameCanvas(globalcanvas, this);
        imageholder = new ImageHolder();
        entityController = new EntityController(this);
        entityController.spawnTurtle();
    }

    public void update(){
        canvas.render();
    }

    public void updateMovement(double dt){
        entityController.getTurtle().updateMovement(dt);
        entityController.getTurtle().update(dt);
    }


    public void onKeyPressed(KeyEvent e) {
        System.out.println("onKeyPressed");
        entityController.getTurtle().onKeyPressed(e);
    }
    public void onKeyReleased(KeyEvent e) {
        entityController.getTurtle().onKeyReleased(e);
    }

    public ImageHolder getImageholder() {
        return imageholder;
    }

    public GameCanvas getCanvas() {
        return canvas;
    }

    public EntityController getEntityController() {
        return entityController;
    }
}
