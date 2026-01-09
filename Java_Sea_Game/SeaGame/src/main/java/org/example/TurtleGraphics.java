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

        double drawWidth  = turtleEntity.width * 2;
        double drawHeight = turtleEntity.height * 2;

        double centerX = turtleEntity.x + drawWidth / 2;
        double centerY = turtleEntity.y + drawHeight / 2;


        gc.save();
        gc.translate(centerX, centerY);

        if (!turtleEntity.facingRight){
            gc.scale(1, -1);
        }

        gc.rotate(turtleEntity.angle);

        gc.drawImage(turtleEntity.currentimg, -drawWidth / 2, -drawHeight / 2, drawWidth, drawHeight );
        gc.restore();
        //gc.drawImage(turtleEntity.currentimg, turtleEntity.x, turtleEntity.y, turtleEntity.width*2, turtleEntity.height*2);
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