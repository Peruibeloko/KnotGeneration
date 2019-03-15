package objectbased;

import processing.core.PApplet;
import processing.core.PVector;

class Node {

    static final int PRIMARY = 0;
    static final int SECONDARY = 1;
    static final int CROSSING = 2;

    private PVector pos; // Position of the node in pixels
    private int[] coord = new int[2]; // Position of the node in the grid
    private Pilot pilot;

    private int type;
    private boolean wasCrossed = false;

    Node(int i, int j, int type, Grid g) {

        pos = new PVector(PApplet.round(g.x + g.size * i), PApplet.round(g.y + g.size * j));

        this.type = type;

        coord[0] = i;
        coord[1] = j;
    }

    PVector getPos() {

        return pos;
    }

    int getType() {

        return type;
    }

    int[] getCoord() {

        return coord;
    }

    public boolean wasCrossed() {
        return wasCrossed;
    }

    public void setWasCrossed(boolean wasCrossed) {
        this.wasCrossed = wasCrossed;
    }

    void setPilot(Pilot pilot) {

        this.pilot = pilot;
    }

    void checkDraw() {

        if ((type == CROSSING) &&
                (PApplet.round(pilot.pos.x) <= PApplet.round(pos.x)) &&
                (PApplet.round(pilot.overhead.x) >= PApplet.round(pos.x)) &&
                (PApplet.round(pilot.pos.y) <= PApplet.round(pos.y)) &&
                (PApplet.round(pilot.overhead.y) >= PApplet.round(pos.y))
                )

            pilot.collisionEvent(2);
    }

    void collisionEmmit() {

        if (type == CROSSING)
            pilot.crossingEvent(this);
    }
}