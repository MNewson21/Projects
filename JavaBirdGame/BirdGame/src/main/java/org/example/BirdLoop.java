package org.example;

import javafx.animation.AnimationTimer;

public class BirdLoop extends AnimationTimer {
    private long last = System.nanoTime();
    private GameController gamecontroller;
    private static final long FRAME_INTERVAL = 16_666_666; // ~60 fps



    public BirdLoop(GameController gamecontroller) {
        this.gamecontroller = gamecontroller;

    }

    @Override
    public void handle(long now) {
        if (last == 0) {
            last = now;
            return;
        }

        double dt = (now-last) / 1_000_000_000.0;
        last = now;

        dt = Math.min(dt, 0.05);
        updateMovement(dt);
        render();
    }



    public void render(){
        gamecontroller.render();
    }
    public void updateMovement(double dt){
        gamecontroller.updateMovement(dt);
        System.out.println("updatemovementdt: " + dt);
    }
}
