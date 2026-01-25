package org.example;

public class EntityController {
    static boolean TestMode;
    private Game game;
    private TurtleEntity turtle;

    public EntityController(Game game) {
        this.game = game;
    }


    public void spawnTurtle(){
        this.turtle = new TurtleEntity(game.getImageholder().TurtleIdle[0].getWidth(), game.getImageholder().TurtleIdle[0].getHeight(), this);
    }

    public TurtleEntity getTurtle(){
        return this.turtle;
    }

    public Game getGame(){
        return this.game;
    }
}
