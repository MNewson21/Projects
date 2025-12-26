package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class TurtleEntity {
    private TurtleMovement turtleMovement;


    double x = 900;
    double vx = 0;

    double animdt = 0;

    double y = 450;
    double vy = 0;

    double height;
    double width;

    public Image currentimg;
    private EntityController entityController;

    public TurtleEntity(double width, double height, EntityController entityController) {
        this.width = width;
        this.height = height;
        this.turtleMovement = new TurtleMovement(this);
        this.entityController = entityController;
        currentimg = entityController.getGame().getImageholder().TurtleIdle[0];
    }


    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void update(double dt) {
        animdt += dt;
        x += vx * dt;
        y += vy * dt;
        System.out.println("Before redraw x y vx vy dt " + x + " : " + y  + " : "+ vx + " : " + vy + ":" + dt);
        setPosition(x, y);
        if (animdt > 0.050){
            animdt = 0;
            currentimg = nextAnimFrame();
        }
    }

    public void draw(GraphicsContext gc) {
        System.out.println("draw att : " + " x: " + x + " y: " + y + " width: " + width + " height: " + height);
        gc.drawImage(currentimg, x, y, width*2, height*2);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void onKeyPressed(KeyEvent e){
        turtleMovement.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent e){
        System.out.println("Key Released");
        turtleMovement.onKeyReleased(e);
    }

    public void updateMovement(double dt){
        turtleMovement.update(dt);
    }

    public Image nextAnimFrame(){
        if (entityController.getGame().getImageholder().turtleidleframe == 5){
            entityController.getGame().getImageholder().turtleidleframe = 0;
        }
        else{
            entityController.getGame().getImageholder().turtleidleframe++;
        }

        return entityController.getGame().getImageholder().TurtleIdle[entityController.getGame().getImageholder().turtleidleframe];
    }
}
