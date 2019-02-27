package objectbased;

import processing.core.PApplet;

public class ObjectBased extends PApplet {

    int gridColor, nodeColor;

    Grid grid;
    Pilot pilot;
    Knot knot;

    public void settings(){

        size(600, 600);
    }

    public void setup(){

        grid = new Grid(50, 50, 5, 4, 60, this);
        pilot = new Pilot(grid);
        knot = new Knot(pilot, grid, this);
    }

    public void draw() {

        background(35);

        grid.drawGrid(gridColor, nodeColor);
        image(grid.gridImage, 0, 0);

        knot.drawKnot();
        image(knot.knotImage, 0, 0);
    }

    public static void main(String[] args){

        PApplet.main("objectbased.ObjectBased");
    }
}
