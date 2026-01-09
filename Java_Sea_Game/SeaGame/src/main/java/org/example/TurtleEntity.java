package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class TurtleEntity {
    private final TurtleMovement turtleMovement;
    private final TurtleGraphics turtleGraphics;
    private final TurtleCollision turtleCollision;

    double x = 900;
    double vx = 0;

    double y = 450;
    double vy = 0;

    double height;
    double width;

    public Image currentimg;
    private EntityController entityController;

    public double angle;
    public boolean facingRight = true;

    public TurtleEntity(double width, double height, EntityController entityController) {
        this.width = width;
        this.height = height;
        this.turtleMovement = new TurtleMovement(this);
        this.entityController = entityController;
        currentimg = entityController.getGame().getImageholder().TurtleIdle[0];
        this.turtleGraphics = new TurtleGraphics(this);
        this.turtleCollision = new TurtleCollision(this, entityController.getGame().getGateManager());
    }


    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void update(double dt) {
        if (turtleCollision.intercept(dt) == false){
            x += vx * dt;
            y += vy * dt;
            angle = Math.toDegrees(Math.atan2(vy, vx));

        }

        //System.out.println("Before redraw x y vx vy dt " + x + " : " + y  + " : "+ vx + " : " + vy + ":" + dt);
        setPosition(x, y);
        updateAnimTimer(dt);
    }

    public void draw(GraphicsContext gc) {
        turtleGraphics.draw(gc);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void onKeyPressed(KeyEvent e){
        turtleMovement.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent e){
        //System.out.println("Key Released");
        turtleMovement.onKeyReleased(e);
    }

    public void updateMovement(double dt){
        turtleMovement.update(dt);
    }

    public EntityController getEntityController() {
        return entityController;
    }

    public void updateAnimTimer(double dt) {
        turtleGraphics.updateAnimTimer(dt);
    }



}
