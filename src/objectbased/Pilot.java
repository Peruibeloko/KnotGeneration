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
    private PVector vel; // X and Y speeds

    public Pilot(Grid g) {

        pos = new PVector(g.getNode(2, 1).getPos().x, g.getNode(2, 1).getPos().y);
        vel = new PVector(5, 5);
    }

    PVector nextPos() {

        return new PVector(pos.x + vel.x, pos.y + vel.y);
    }

    void movePilot() {

        pos.x += vel.x;
        pos.y += vel.y;
    }

    void collisionEvent(int type) {

        if (type == 0) {

            vel.x = -vel.x;
            PApplet.println("Collided with vertical wall");
            colCount++;

        } else if (type == 1) {

            vel.y = -vel.y;
            PApplet.println("Collided with horizontal wall");
            colCount++;

        }
    }

    void collisionEvent(int type, Wall w) {

        if (type == 0) {

            PVector newPos = getIntersection(w);
            pos.x = newPos.x;
            pos.y = newPos.y;

            vel.x = -vel.x;
            PApplet.println("Inside vertical wall");
            colCount++;

        } else if (type == 1) {

            PVector newPos = getIntersection(w);
            pos.x = newPos.x;
            pos.y = newPos.y;

            vel.y = -vel.y;
            PApplet.println("Inside horizontal wall");
            colCount++;
        }
    }

    PVector getIntersection(Wall w) {

        PVector nextPos = nextPos();

        float x1 = pos.x;
        float y1 = pos.y;

        float x2 = nextPos.x;
        float y2 = nextPos.y;

        float x3 = w.getNode(0).getPos().x;
        float y3 = w.getNode(0).getPos().y;

        float x4 = w.getNode(1).getPos().x;
        float y4 = w.getNode(1).getPos().y;
        float commFactor = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        float px = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        px /= commFactor;

        float py = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);
        py /= commFactor;

        return new PVector(px, py);
    }

    boolean getFlag() {

        return isOver;
    }
}
