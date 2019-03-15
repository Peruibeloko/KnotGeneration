package objectbased;

import processing.core.PApplet;

public class ObjectBased extends PApplet {

    private int gridColor = color(255, 127, 0);
    private int nodeColor = color(0, 127, 255);

    private int w = 10, h = 8, size = 50;

    private Grid grid;
    private Pilot pilot;
    private Knot knot;

    public void settings(){

        size((w+4)*size, (h+4)*size);
    }

    public void setup(){

        grid = new Grid(size*2, size*2, w, h, size, this);
        pilot = new Pilot(grid);
        grid.addPilotToNodes(pilot);
        knot = new Knot(pilot, this);

        grid.addWall(grid.getNode(0,0), grid.getNode(w,0), pilot);
        grid.addWall(grid.getNode(0,0), grid.getNode(0,h), pilot);
        grid.addWall(grid.getNode(w,h), grid.getNode(w,0), pilot);
        grid.addWall(grid.getNode(w,h), grid.getNode(0,h), pilot);

        grid.addWall(grid.getNode(w/2, 6), grid.getNode(w/2,2), pilot);

        grid.drawGrid(gridColor, nodeColor);
    }

    public void draw() {

        background(35);
        image(grid.gridImage, 0, 0);

        knot.drawKnot();
        image(knot.knotImage, 0, 0);

        grid.checkCollision();
        grid.shouldIDraw();

        String pilotPos = pilot.pos.x + ", " + pilot.pos.y;
        text("Pilot position: " + pilotPos, 20, 20);
        text("Collision count: " + pilot.colCount, 20, 40);

        drawPilot();
    }

    public void keyPressed(){

        if(key == ' ')
            if(isLooping())
                noLoop();
            else
                loop();

    }

    void drawPilot(){

        stroke(255);
        strokeWeight(12);
        point(pilot.pos.x, pilot.pos.y);
        strokeWeight(8);
        line(pilot.pos.x, pilot.pos.y, pilot.overhead.x, pilot.overhead.y);
    }

    public static void main(String[] args){

        PApplet.main("objectbased.ObjectBased");
    }
}
