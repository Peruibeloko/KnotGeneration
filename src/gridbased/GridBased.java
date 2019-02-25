package gridbased;

import garciadelcastillo.dashedlines.DashedLines;
import processing.core.*;

public class GridBased extends PApplet {

    float x = 50, y = 50;
    int cols = 5, rows = 4, size = 80;

    int curveStep = 0;

    int det = ((rows - 1) * cols) + ((cols - 1) * rows);

    PVector[] cross = new PVector[det];
    PShape curve;

    DashedLines d;

    public void setup() {

        size(600, 600);
        noFill();

        d = new DashedLines(this);


        int i = 0;

        for (int r = 1; r < rows * 2; r++) {
            for (int c = 1; c < cols * 2; c++) {

                if ((c % 2 != 0 && r % 2 == 0) || (c % 2 == 0 && r % 2 != 0)) {
                    stroke(0, 255, 127);
                    strokeWeight(6);
                    point(x + (size/2 * c), y + (size/2 * r));
                    cross[i] = new PVector(x + (size/2 * c), y + (size/2 * r));
                    i++;
                }
            }
        }

        for (PVector pv : cross) {
            print(pv.x + " ");
            println(pv.y);
        }
    }

    public void draw() {

        background(43);
        drawGrid(x, y, rows, cols, size);

        stroke(0, 127, 255);
        strokeWeight(4);

        createCurve(x, y, rows, cols, size);
    }

    void createCurve(float x, float y, int rows, int cols, int size) {

        float diag = sqrt((float) Math.pow(size/2, 2) + (float) Math.pow(size/2, 2));

        d.pattern(diag * 1.6f, diag * 0.4f);
        //d.pattern(diag * 0.6, diag * 0.4);
        d.offset(diag * -0.8f);

        int i;
        curveStep = 0;

        d.beginShape(); // linha reta
        for (i = 0; i <= cols; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep += cols;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep = curveStep - rows;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        for (i = 0; i < cols; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep -= cols;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep -= 1;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep += rows;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep += cols + rows;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        for (i = 0; i < 3; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep += cols;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep += 1;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        for (i = 0; i < 3; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep -= rows;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep -= cols + rows;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep -= cols;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep -= 1;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        for (i = 0; i < cols; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep += rows;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep += cols;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        // --------------------------------------- //

        for (i = 0; i <= cols; i++) {

            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep -= rows;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep = curveStep + cols;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        for (i = 0; i < cols; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep += rows;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep -= 1;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep -= cols;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep -= cols + rows;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        for (i = 0; i < 3; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep -= rows;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep += 1;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        for (i = 0; i < 3; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep += cols;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep += cols + rows;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep += rows;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        curveStep -= 1;
        d.vertex(cross[curveStep].x, cross[curveStep].y);

        for (i = 0; i < cols; i++) {
            d.vertex(cross[curveStep].x, cross[curveStep].y);
            curveStep -= cols;
        }
        d.vertex(cross[curveStep].x, cross[curveStep].y);
        d.endShape();

        noLoop();
    }

    void drawGrid(float x, float y, int rows, int cols, int size) {

        // Squares
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                stroke(255, 127, 0);
                strokeWeight(2);
                square(x + (size * c), y + (size * r), size);
            }
        }

        // Collateral points
        for (int r = 0; r <= rows * 2; r++) {
            for (int c = 0; c <= cols * 2; c++) {

                stroke(255);
                strokeWeight(3);
                point(x + (size/2 * c), y + (size/2 * r));
            }
        }

        // Lattice points
        for (int r = 0; r <= rows; r++) {
            for (int c = 0; c <= cols; c++) {

                stroke(255);
                strokeWeight(6);
                point(x + (size * c), y + (size * r));
            }
        }

        // Intersection points
        for (int r = 1; r < rows * 2; r++) {
            for (int c = 1; c < cols * 2; c++) {

                if ((c % 2 != 0 && r % 2 == 0) || (c % 2 == 0 && r % 2 != 0)) {
                    stroke(0, 255, 127);
                    strokeWeight(6);
                    point(x + (size/2 * c), y + (size/2 * r));
                }
            }
        }
    }
}