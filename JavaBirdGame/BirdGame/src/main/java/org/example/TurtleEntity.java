package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class TurtleEntity {
    private TurtleMovement turtleMovement;
    private long lastRequest = System.nanoTime();

    Image idleimage  = new Image(Objects.requireNonNull(
    getClass().getResource("/Sprites/Turtle/sprite-1-1.png"),
            "Missing resource: /Sprites/Turtle/sprite-1-1.png"
                    ).toExternalForm()
    );
    double x = 900;
    double vx = 0;


    double y = 450;
    double vy = 0;

    double height;
    double width;


    public TurtleEntity() {
        //this.idleimage = new Image(this.getClass().getClassLoader().getResourceAsStream("/Sprites/Turtle/sprite-1-1.png"));
        this.width = idleimage.getWidth();
        this.height = idleimage.getHeight();
        this.turtleMovement = new TurtleMovement(this);

    }


    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void update() {
        turtleMovement.update();
        x += vx;
        y += vy;
        setPosition(x, y);
        if (System.currentTimeMillis() - lastRequest > 500) {
            lastRequest = System.currentTimeMillis();
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(idleimage, x, y, width*2, height*2);
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
}
