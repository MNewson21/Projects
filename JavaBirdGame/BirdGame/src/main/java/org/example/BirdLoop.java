package org.example;

import javafx.animation.AnimationTimer;

public class BirdLoop extends AnimationTimer {
    private long last = System.nanoTime();
    private GameController gamecontroller;

    public BirdLoop(GameController gamecontroller) {
        this.gamecontroller = gamecontroller;

    }

    @Override
    public void handle(long now) {
        if (now - last >= 120.0){
            last = now;
            render();
        }
    }



    public void render(){

    }
}
