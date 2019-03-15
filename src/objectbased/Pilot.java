package objectbased;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import static processing.core.PApplet.abs;

public class Pilot {

    // A pilot is a moving point

    private boolean isOver = true;
    int colCount = 0;

    PVector pos; // X and Y positions
    PVector overhead; // overhead of collision
    private PVector vel; // X and Y speeds

    private float space = 5;

    public Pilot(Grid g) {

        pos = new PVector(g.getNode(2, 1).getPos().x, g.getNode(2, 1).getPos().y);
        vel = new PVector(5, 5);

        updateOverhead();
    }

    void movePilot() {

        pos.x += vel.x;
        pos.y += vel.y;

        updateOverhead();
    }

    void collisionEvent(int type) {

        if (type == 0) {

            vel.x = -vel.x;
            updateOverhead();
            PApplet.println("Collided with vertical wall");
            colCount++;

        } else if (type == 1) {

            vel.y = -vel.y;
            updateOverhead();
            PApplet.println("Collided with horizontal wall");
            colCount++;

        }
    }

    void crossingEvent(Node node){

        if(!node.wasCrossed()){

            node.setWasCrossed(true);

        } else {


        }

        isOver = !isOver;
    }

    boolean getFlag() {

        return isOver;
    }

    void updateOverhead(){

        overhead = new PVector(pos.x + (vel.x / abs(vel.x)) * space, pos.y + (vel.y / abs(vel.y)) * space);
    }
}
