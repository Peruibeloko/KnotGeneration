package objectbased;

import processing.core.PApplet;
import processing.core.PVector;

class Node {

    static final int PRIMARY = 0;
    static final int SECONDARY = 1;
    static final int CROSSING = 2;

    private PVector pos; // Position of the node in pixels
    private int[] coord = new int[2]; // Position of the node in the grid

    private int type;

    Node(int i, int j, int type, Grid g) {

        pos = new PVector(PApplet.round(g.x + g.size * i), PApplet.round(g.y + g.size * j));

        this.type = type;

        coord[0] = i;
        coord[1] = j;
    }

    PVector getPos(){

        return pos;
    }

    int getType(){

        return type;
    }

    int[] getCoord(){

        return coord;
    }
}