package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.awt.*;

public class Game {
    private Canvas globalcanvas;
    private GameCanvas canvas;
    private ImageHolder imageholder;
    private EntityController entityController;
    private Background background;
    private GateManager gateManager;

    public Game(Canvas globalcanvas){
        this.globalcanvas = globalcanvas;
        canvas = new GameCanvas(globalcanvas, this);
        imageholder = new ImageHolder();
        gateManager = new GateManager(canvas);
        entityController = new EntityController(this);
        entityController.spawnTurtle();
        background = new Background(imageholder.background);
    }

    public void update(){
        canvas.render();
        gateManager.update(entityController.getTurtle());
    }

    public void updateMovement(double dt){
        entityController.getTurtle().updateMovement(dt);
        entityController.getTurtle().update(dt);
    }


    public void onKeyPressed(KeyEvent e) {
        //System.out.println("onKeyPressed");
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

    public Background getBackground() {
        return background;
    }

    public GateManager getGateManager() {
        return gateManager;
    }
}
