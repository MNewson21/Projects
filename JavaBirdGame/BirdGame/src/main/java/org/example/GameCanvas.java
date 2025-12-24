package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameCanvas {
    private Canvas javafxcanvas;
    private GraphicsContext gc;
    private Game game;
    public TurtleEntity turtle;

    public GameCanvas(Canvas javafxcanvas, Game game) {
        this.javafxcanvas = javafxcanvas;
        this.gc = this.javafxcanvas.getGraphicsContext2D();
        this.game = game;
        this.turtle = new TurtleEntity();

    }


    public void render() {
        //System.out.println("render frame");
        gc.clearRect(0, 0, javafxcanvas.getWidth(), javafxcanvas.getHeight());
        turtle.update();
        gc.drawImage(turtle.idleimage, turtle.x,  turtle.y, turtle.width *2, turtle.height*2);
    }
}
