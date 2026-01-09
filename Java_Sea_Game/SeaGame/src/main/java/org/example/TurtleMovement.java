package org.example;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TurtleMovement {
    private final TurtleEntity turtle;
    private final double speed = 25; // pixels per second

    private boolean up, down, left, right;
    private double maxSpeed = 600;
    private double acceleration = 1000;
    private double friction = 300;

    public TurtleMovement(TurtleEntity turtle) {
        this.turtle = turtle;
    }

    public void onKeyPressed(KeyEvent e) {
        KeyCode k = e.getCode();
        if (k == KeyCode.W || k == KeyCode.UP) up = true;
        if (k == KeyCode.S || k == KeyCode.DOWN) down = true;
        if (k == KeyCode.A || k == KeyCode.LEFT) left = true;
        if (k == KeyCode.D || k == KeyCode.RIGHT) right = true;
    }

    public void onKeyReleased(KeyEvent e) {
        KeyCode k = e.getCode();
        if (k == KeyCode.W || k == KeyCode.UP) up = false;
        if (k == KeyCode.S || k == KeyCode.DOWN) down = false;
        if (k == KeyCode.A || k == KeyCode.LEFT) left = false;
        if (k == KeyCode.D || k == KeyCode.RIGHT) right = false;
    }

    public void update(double dt) {
        double ax = 0;
        double ay = 0;

        if (left)  ax -= acceleration;
        if (right) ax += acceleration;
        if (up)    ay -= acceleration;
        if (down)  ay += acceleration;

        turtle.vx += ax * dt;
        turtle.vy += ay * dt;

        //System.out.println("After initial turtle.vx turtle.vy" + turtle.vx + turtle.vy);
        if (!left && !right){
            turtle.vx = applyFriction(turtle.vx, dt);
        }

        if (!up && !down){
            turtle.vy = applyFriction(turtle.vy,dt);
        }


        turtle.vx = clamp(turtle.vx, -maxSpeed, maxSpeed);
        turtle.vy = clamp(turtle.vy, -maxSpeed, maxSpeed);

        //System.out.println("Final turtle.vx, turtle.vy = " + turtle.vx + turtle.vy);
        if (turtle.vx < 0){
            turtle.facingRight = false;
        } else if (turtle.vx > 0){
            turtle.facingRight = true;
        }

        turtle.setVelocity(turtle.vx, turtle.vy);


    }

    private double applyFriction(double v, double dt) {
        if (v > 0) {
            v -= friction * dt;
            if (v < 0) v = 0;
        } else if (v < 0) {
            v += friction * dt;
            if (v > 0) v = 0;
        }
        return v;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }


}
