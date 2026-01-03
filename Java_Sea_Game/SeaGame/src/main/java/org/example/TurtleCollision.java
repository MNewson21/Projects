package org.example;

public class TurtleCollision{
    private TurtleEntity turtleEntity;
    private  GateManager gateManager;

    public TurtleCollision(TurtleEntity turtleEntity, GateManager gateManager){
        this.turtleEntity = turtleEntity;
        this.gateManager = gateManager;
    }




    public boolean intercept(double dt){
        //Checks if it collides with the boundaries of the screen and will reverse the respective directions velocity if true
        double tempx = turtleEntity.x;
        double tempy = turtleEntity.y;

        tempx += turtleEntity.vx *dt;
        tempy += turtleEntity.vy * dt;



        //System.out.println("intercept tempx: " +  tempx + " intercept tempy: " + tempy);
        //        if ((tempx < (150+turtleEntity.width)) || (tempx > 1690 - turtleEntity.width)){
        if ((tempx < (150+turtleEntity.width)) || (tempx > 1710 - turtleEntity.width)){
            turtleEntity.vx = turtleEntity.vx * -1;
            return true;

        } else if((tempy < 50 + turtleEntity.height) || (tempy > 910 - turtleEntity.height)){
            turtleEntity.vy = turtleEntity.vy * -1;
            return true;
        }

        // --- Gate collision ---
        for (Gate gate : gateManager.getGates()) {
            if (gate.collides(
                    tempx,
                    tempy,
                    turtleEntity.width,
                    turtleEntity.height,
                    930
            )) {
                turtleEntity.vx *= -1; // bounce horizontally
                return true;
            }
        }
        return false;
    }



}