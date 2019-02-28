package objectbased;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Grid {

    float x, y; // X-Y coordinates for the grid
    int w, h; // size of the grid in wall units (spaces between nodes)
    int size; // length of each wall

    private Node[][] nodes;
    private ArrayList<Wall> walls = new ArrayList<>();

    PGraphics gridImage;
    PApplet parent;

    public Grid(float x, float y, int w, int h, int size, PApplet parent) {

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.size = size;

        nodes = new Node[w+1][h+1];

        for(int j = 0; j <= h; j++) {
            for(int i = 0; i <= w; i++) {

                if (i % 2 == 0 && j % 2 == 0) {
                    nodes[i][j] = new Node(i, j, Node.PRIMARY, this, parent);
                    parent.print("+ ");

                } else if (((i % 2 != 0 && j % 2 == 0) || (i % 2 == 0 && j % 2 != 0)) &&
                        ((i > 0 && i < w) && (j > 0 && j < h))) {

                    nodes[i][j] = new Node(i, j, Node.CROSSING, this, parent);
                    parent.print("X ");

                } else {
                    nodes[i][j] = new Node(i, j, Node.SECONDARY, this, parent);
                    parent.print("  ");
                }
            }
            parent.println("");
        }

        gridImage = parent.createGraphics(parent.width, parent.height);
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
            w.collisionEmmit(p.pos);
    }

    void addWall(Node start, Node end, Pilot pilot){

        walls.add(new Wall(start, end, pilot));
    }

    void drawGrid(int gridColor, int nodeColor){

        gridImage.beginDraw();

        gridImage.background(0, 0, 0, 0);
        gridImage.noFill();

        for (int j = 0; j < h; j++) { // fine grid
            for (int i = 0; i < w; i++) {

                gridImage.pushStyle();
                gridImage.stroke(gridColor, 50);
                gridImage.strokeWeight(3);
                gridImage.square(i * size + x, j * size + y, size);
                gridImage.popStyle();
            }
        }

        for (int j = 0; j < h/2; j++) { // big grid
            for (int i = 0; i < w/2; i++) {

                gridImage.pushStyle();
                gridImage.stroke(gridColor);
                gridImage.strokeWeight(6);
                gridImage.square(i * size*2 + x, j * size*2 + y, size*2);
                gridImage.popStyle();
            }
        }

        for (int j = 0; j <= h; j++) { // nodes
            for (int i = 0; i <= w; i++) {

                if (nodes[i][j].getType() == Node.PRIMARY) {

                    gridImage.pushStyle();
                    gridImage.stroke(nodeColor); // regular blue
                    gridImage.strokeWeight(8);

                } else if (nodes[i][j].getType() == Node.CROSSING) {

                    gridImage.pushStyle();
                    gridImage.stroke(nodeColor + gridImage.color(63, 63, 0)); // lighter blue
                    gridImage.strokeWeight(5);

                } else {

                    gridImage.pushStyle();
                    gridImage.stroke(nodeColor + gridImage.color(0, 63, 63)); // green
                    gridImage.strokeWeight(5);
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

        gridImage.endDraw();
    }
}
