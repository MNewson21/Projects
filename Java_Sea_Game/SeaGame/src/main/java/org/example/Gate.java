package org.example;

import javafx.scene.canvas.GraphicsContext;

public class Gate {

    public enum Side { LEFT, RIGHT }

    private Side side;
    private double x;
    private double gapY;
    private double gapHeight;

    private boolean scored = false;

    public Gate(Side side, double x, double gapY, double gapHeight) {
        this.side = side;
        this.x = x;
        this.gapY = gapY;
        this.gapHeight = gapHeight;
    }

    public void draw(GraphicsContext gc, double screenHeight) {
        double wallWidth = 40; // seaweed thickness

        //Top wall
        gc.fillRect(x, 0, wallWidth, gapY);

        //Bottom wall
        gc.fillRect(
                x,
                gapY + gapHeight,
                wallWidth,
                screenHeight - (gapY + gapHeight)
        );
    }

    public double getGapY() { return gapY; }
    public double getGapHeight() { return gapHeight; }
    public double getX() { return x; }
    public Side getSide() { return side; }

    public boolean hasScored() { return scored; }
    public void setScored(boolean scored) { this.scored = scored; }

    public boolean collides(double tx, double ty, double tw, double th, double screenHeight) {
        //Screenheight used as I am developing on a laptop and javafx scales weirdly
        //Once it works on laptop, I can then transfer it over to main desktop
        double wallWidth = 40;

        boolean hitsX = tx + tw > x - wallWidth && tx < x + wallWidth;

        boolean hitsTop = ty < gapY;
        boolean hitsBottom = ty + th > gapY + gapHeight;

        return hitsX && (hitsTop || hitsBottom);
    }
}
