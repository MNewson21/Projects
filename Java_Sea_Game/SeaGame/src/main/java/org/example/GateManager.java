package org.example;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
import java.util.List;
public class GateManager {

    private Gate leftGate;
    private Gate rightGate;

    private final double screenWidth;
    private final double screenHeight;

    private final double gapHeight = 120; // 3 blocks
    private final double wallWidth = 40;

    private final Random random = new Random();

    public GateManager(GameCanvas canvas) {
        this.screenWidth = canvas.getWidth() - 200;
        this.screenHeight = canvas.getHeight() - 150;
        leftGate = createGate(Gate.Side.LEFT,  200);
        rightGate = createGate(Gate.Side.RIGHT, screenWidth - wallWidth);
    }

    private Gate createGate(Gate.Side side, double x) {
        double minGapY = 100;
        double maxGapY = screenHeight - gapHeight - 100;

        double gapY = minGapY + random.nextDouble() * (maxGapY - minGapY);

        return new Gate(side, x, gapY, gapHeight);
    }

    public void update(TurtleEntity turtle) {
        checkGate(turtle, leftGate);
        checkGate(turtle, rightGate);
        //System.out.println("Left gate X: " + leftGate.getX());
        //System.out.println("Right gate X: " + rightGate.getX());
    }

    private void checkGate(TurtleEntity turtle, Gate gate) {
        if (turtlePassedGate(turtle, gate) && !gate.hasScored()) {
            gate.setScored(true);
            System.out.println("Scored at gate : " + gate.getSide());
            // score++
            //score will be incremented here, just need to implement score system.

        }

        if (gate.hasScored() && turtleMovedAway(turtle, gate)) {
            regenerateGate(gate);
            System.out.println("regenerated gate : " + gate.getSide());
        }
    }

    private boolean turtlePassedGate(TurtleEntity turtle, Gate gate) {
        if (gate.getSide() == Gate.Side.LEFT) {
            return turtle.x < gate.getX() + wallWidth;
        } else {
            return turtle.x > gate.getX() - wallWidth;
        }
    }

    private boolean turtleMovedAway(TurtleEntity turtle, Gate gate) {
        if (gate.getSide() == Gate.Side.LEFT) {
            return turtle.x > screenWidth * 0.25;
        } else {
            return turtle.x < screenWidth * 0.75;
        }
    }

    private void regenerateGate(Gate gate) {
        double offset = random.nextDouble() * 200 - 100;

        double newGapY = gate.getGapY() + offset;
        newGapY = Math.max(100, Math.min(newGapY, screenHeight - gapHeight - 100));

        if (gate.getSide() == Gate.Side.LEFT) {
            leftGate = new Gate(Gate.Side.LEFT, 200, newGapY, gapHeight);
        } else {
            rightGate = new Gate(Gate.Side.RIGHT, screenWidth - wallWidth, newGapY, gapHeight);
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.DARKGREEN);
        leftGate.draw(gc, screenHeight);
        rightGate.draw(gc, screenHeight);
    }

    public Gate[] getGates() {
        return new Gate[]{ leftGate, rightGate };
    }


    public Gate getLeftGate() {
        return leftGate;
    }

    public Gate getRightGate() {
        return rightGate;
    }
}
