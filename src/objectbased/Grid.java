package objectbased;

import processing.core.*;

public class Grid extends PApplet {

    float x, y; // X-Y coordinates for the grid
    int width, heigth; // size of the grid in wall units (spaces between nodes)
    float size; // length of each wall

    private Node[] nodesPrimary;
    private Node[] nodesSecondary;
    private Node[] nodesCrossing;

    private Wall[] wallsPrimary;
    private Wall[] wallsCrossing;

    // TODO Add collision logic
    // TODO Add node positioning calculation
    // TODO Add drawing handlers
    // TODO Add knot/pilot integration
}
