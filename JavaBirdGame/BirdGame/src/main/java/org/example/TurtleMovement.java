package org.example;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TurtleMovement {
    private final TurtleEntity turtle;
    private final double speed = 25; // pixels per second

    private boolean up, down, left, right;

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

    public void update() {
        double vx = 0, vy = 0;

        if (left)  vx -= speed;
        if (right) vx += speed;
        if (up)    vy -= speed;
        if (down)  vy += speed;

        turtle.setVelocity(vx, vy);
    }


}
