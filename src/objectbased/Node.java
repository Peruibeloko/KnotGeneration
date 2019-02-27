package objectbased;

import processing.core.PApplet;
import processing.core.PVector;

public class Node {

    public static final int PRIMARY = 0;
    public static final int SECONDARY = 1;
    public static final int CROSSING = 2;

    private PVector pos; // Position of the node in pixels
    private int[] coord = new int[2]; // Position of the node in the grid

    private int type;

    PApplet parent;

    public Node(int i, int j, int type, Grid g, PApplet parent) {

        pos = new PVector(parent.round(g.x + g.size * i), parent.round(g.y + g.size * j));

        this.type = type;

        coord[0] = i;
        coord[1] = j;

        this.parent = parent;
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