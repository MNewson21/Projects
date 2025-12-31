package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TurtleGraphics{
    private TurtleEntity turtleEntity;
    double animdt = 0;


    public TurtleGraphics(TurtleEntity turtleEntity){
        this.turtleEntity = turtleEntity;
    }

    public void draw(GraphicsContext gc) {
        //System.out.println("draw att : " + " x: " + x + " y: " + y + " width: " + width + " height: " + height);
        gc.drawImage(turtleEntity.currentimg, turtleEntity.x, turtleEntity.y, turtleEntity.width*2, turtleEntity.height*2);
    }

    public void updateAnimTimer(double dt){
        animdt += dt;
        if (animdt > 0.050){
            animdt = 0;
            turtleEntity.currentimg = nextAnimFrame();
        }
    }

    public Image nextAnimFrame(){
        if (turtleEntity.getEntityController().getGame().getImageholder().turtleidleframe == 5){
            turtleEntity.getEntityController().getGame().getImageholder().turtleidleframe = 0;
        }
        else{
            turtleEntity.getEntityController().getGame().getImageholder().turtleidleframe++;
        }

        return turtleEntity.getEntityController().getGame().getImageholder().TurtleIdle[turtleEntity.getEntityController().getGame().getImageholder().turtleidleframe];
    }

}