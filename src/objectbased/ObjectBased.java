package objectbased;

import processing.core.PApplet;

public class ObjectBased extends PApplet {

    int gridColor = color(255, 127, 0);
    int nodeColor = color(0, 127, 255);

    int w = 10, h = 8, size = 30;

    Grid grid;
    Pilot pilot;
    Knot knot;

    public void settings(){

        size(600, 600);
    }

    public void setup(){

        grid = new Grid(50, 50, w, h, size, this);
        pilot = new Pilot(grid);
        knot = new Knot(pilot, grid, this);

        grid.addWall(grid.getNode(0,0), grid.getNode(w,0), pilot);
        grid.addWall(grid.getNode(0,0), grid.getNode(0,h), pilot);
        grid.addWall(grid.getNode(w,h), grid.getNode(w,0), pilot);
        grid.addWall(grid.getNode(w,h), grid.getNode(0,h), pilot);

        grid.addWall(grid.getNode(w/2,2), grid.getNode(w/2, 6), pilot);
    }

    public void draw() {

        background(35);

        grid.drawGrid(gridColor, nodeColor);
        image(grid.gridImage, 0, 0);

        knot.drawKnot();
        image(knot.knotImage, 0, 0);

        grid.checkCollision(pilot);

        stroke(255);
        strokeWeight(12);
        point(pilot.pos.x, pilot.pos.y);
    }

    public static void main(String[] args){

        PApplet.main("objectbased.ObjectBased");
    }
}
