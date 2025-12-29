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

    }


    public void render() {
        gc.clearRect(0, 0, javafxcanvas.getWidth(), javafxcanvas.getHeight());
        game.getBackground().draw(gc);
        game.getEntityController().getTurtle().draw(gc);

    }



    public Game getGame() {
        return game;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}
