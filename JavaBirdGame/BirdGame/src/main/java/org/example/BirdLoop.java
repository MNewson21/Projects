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

        if (now - last >= FRAME_INTERVAL) {
            last = now;
            render();
        }
    }



    public void render(){
        gamecontroller.render();
    }
}
