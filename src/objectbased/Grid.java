package objectbased;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Grid {

    float x, y; // X-Y coordinates for the grid
    int w, h; // size of the grid in wall units (spaces between nodes)
    int size; // length of each wall

    private Node[][] nodes;
    private ArrayList<Wall> walls;

    PGraphics gridImage;
    PApplet parent;

    public Grid(float x, float y, int w, int h, int size, PApplet parent) {

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.size = size;

        nodes = new Node[w * 2][h * 2];

        for(int j = 0; j < h * 2; j++) {
            for(int i = 0; i < w * 2; i++) {

                if(i % 2 == 0 && j % 2 == 0)
                    nodes[i][j] = new Node(i, j, Node.PRIMARY, this, parent);

                else if ((i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0))
                    nodes[i][j] = new Node(i, j, Node.CROSSING, this, parent);

                else
                    nodes[i][j] = new Node(i, j, Node.SECONDARY, this, parent);
            }
        }

        gridImage = parent.createGraphics(w * size, h * size);
        this.parent = parent;
    }

    public Node getNode(int i, int j) {

        return nodes[i][j];
    }

    /**
     * Checks the pilot collision against every wall
     * @param p
     * Pilot reference
     */

    void checkCollision(Pilot p){

        for(Wall w : walls)
            p.collidePilot(w.isColliding(p.pos));
    }

    void drawGrid(int gridColor, int nodeColor){

        gridImage.background(0, 0, 0, 0);
        gridImage.noFill();

        for (int j = 0; j < h * 2; j++) {
            for (int i = 0; i < w * 2; i++) {

                gridImage.pushStyle();
                gridImage.stroke(gridColor);
                gridImage.tint(127);
                gridImage.strokeWeight(6);
                gridImage.square(i * size + x, j * size + y, size);
                gridImage.popStyle();
            }
        }

        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {

                gridImage.pushStyle();
                gridImage.stroke(gridColor);
                gridImage.strokeWeight(12);
                gridImage.square(i * size + x, j * size + y, size);
                gridImage.popStyle();
            }
        }

        for (int j = 0; j < h * 2; j++) {
            for (int i = 0; i < w * 2; i++) {

                if (nodes[i][j].getType() == Node.PRIMARY) {

                    gridImage.pushStyle();
                    gridImage.stroke(nodeColor);
                    gridImage.strokeWeight(12);

                } else if (nodes[i][j].getType() == Node.CROSSING) {

                    gridImage.pushStyle();
                    gridImage.stroke(nodeColor);
                    gridImage.tint(63, 63, 0);
                    gridImage.strokeWeight(6);

                } else {

                    gridImage.pushStyle();
                    gridImage.stroke(nodeColor);
                    gridImage.tint(0, 63, 63);
                    gridImage.strokeWeight(6);
                }

                gridImage.point(i * size + x, j * size + y);
                gridImage.popStyle();
            }
        }

        for (Wall w : walls){

            gridImage.pushStyle();
            gridImage.strokeWeight(12);
            gridImage.stroke(255, 0, 0, 127);
            gridImage.line(w.getNode(0).getPos().x, w.getNode(0).getPos().y,
                           w.getNode(1).getPos().x, w.getNode(1).getPos().y);
            gridImage.popStyle();
        }
    }
}
