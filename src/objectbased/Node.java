package objectbased;

import processing.core.*;

public class Node extends PApplet {

    private PVector pos;
    private NodeType type;
    private Wall[] walls;

    public Node(float x, float y) {

        pos = new PVector(round(x), round(y));
    }

    public Wall[] getWalls() {

        return walls;
    }

    public int hasWalls(){

        return walls.length;
    }

    PVector getPos(){

        return pos;
    }

    NodeType getType(){

        return type;
    }

    private void addWall(Wall wall){

        walls[walls.length + 1] = wall;
    }
}