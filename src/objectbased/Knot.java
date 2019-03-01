package objectbased;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Knot {

    PGraphics knotImage; // Graphics object used to store knot drawing

    private Pilot pilot;

    public Knot(Pilot p, PApplet parent){

        knotImage = parent.createGraphics(parent.width, parent.height);

        pilot = p;
    }

    void drawKnot(){

        knotImage.beginDraw();

        if(pilot.getFlag()) {

            knotImage.pushStyle();
            knotImage.strokeWeight(12);
            knotImage.stroke(0);
            knotImage.point(pilot.pos.x, pilot.pos.y);
            knotImage.popStyle();

            knotImage.pushStyle();
            knotImage.strokeWeight(8);
            knotImage.stroke(255);
            knotImage.point(pilot.pos.x, pilot.pos.y);
            knotImage.popStyle();
        }

        knotImage.endDraw();

        pilot.movePilot();
    }
}
