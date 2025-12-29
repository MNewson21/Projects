package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TurtleGraphics{
    private TurtleEntity turtleEntity;

    public TurtleGraphics(TurtleEntity turtleEntity){
        this.turtleEntity = turtleEntity;
    }

    public void draw(GraphicsContext gc) {
        //System.out.println("draw att : " + " x: " + x + " y: " + y + " width: " + width + " height: " + height);
        gc.drawImage(turtleEntity.currentimg, turtleEntity.x, turtleEntity.y, turtleEntity.width*2, turtleEntity.height*2);
    }

}