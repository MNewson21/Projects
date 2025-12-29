package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.Objects;


public class Background {
    private Image background1;

    public Background(Image image){
        this.background1 = image;

    }


    public void draw(GraphicsContext gc){
        gc.drawImage(background1, 0,0,1920,1080);
    }

}