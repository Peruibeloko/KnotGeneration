package objectbased;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Knot {

    PApplet parent;
    PGraphics knotImage; // Graphics object used to store knot drawing

    private Pilot pilot;
    private Grid grid;

    public Knot(Pilot p, Grid g, PApplet parent){

        this.parent = parent;

        knotImage = parent.createGraphics(g.w * g.size, g.h * g.size);

        pilot = p;
        grid = g;
    }

    public void drawKnot(){

        if(pilot.setFlag()) {

            knotImage.pushStyle();
            knotImage.strokeWeight(12);
            knotImage.stroke(0);
            knotImage.point(pilot.pos.x, pilot.pos.y);
            knotImage.popStyle();

            knotImage.pushStyle();
            knotImage.strokeWeight(10);
            knotImage.stroke(255);
            knotImage.point(pilot.pos.x, pilot.pos.y);
            knotImage.popStyle();
        }

        pilot.movePilot();
    }
}
