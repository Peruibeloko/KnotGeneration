package objectbased;

import processing.core.PVector;

public class Pilot {

    // A pilot is a moving point

    private boolean overUnder = true;

    PVector pos; // X and Y positions
    PVector vel; // X and Y speeds

    Grid g;

    /**
     * Uses first grid crossing node as starting condition
     *
     * @param g
     * Grid Reference
     */

    public Pilot(Grid g) {

        this.g = g;

        pos = new PVector(g.getNode(2,1).getPos().x, g.getNode(2,1).getPos().y);
        vel = new PVector(1, 1);
    }

    public boolean isOverUnder() {

        return overUnder;
    }

    void movePilot(){

        pos.x += vel.x;
        pos.y += vel.y;
    }

    /**
     * Collides the pilot in some way
     * @param direction
     * 0 = Vertical collision
     * 1 = Horizontal collision
     * -1 = No collision
     */

    void collidePilot(int direction){

        if (direction == 0) {

            vel.x = -vel.x;

        } else if (direction == 1) {

            vel.y = -vel.y;
        }
    }

    boolean setFlag(){

        for (int j = 0; j < g.h; j++) {
            for (int i = 0; i < g.w; i++) {

                if ((pos == g.getNode(i, j).getPos()) && (g.getNode(i, j).getType() == Node.CROSSING)) {

                    overUnder = !overUnder;
                }
            }
        }

        return overUnder;
    }
}
