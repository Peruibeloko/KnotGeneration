package techdemo;

import processing.core.*;

class CollisionDemo extends PApplet {

    float x, y;
    float xv, yv;

    public void settings(){

        size(600, 600);
    }

    public void setup() {

        x = 150;
        y = height / 2 + 60;

        xv = 1;
        yv = -1;
    }

    public void draw() {

        background(35);

        stroke(255);
        line(width / 2, 20, width / 2, 400);

        strokeWeight(12);
        stroke(255, 127, 0);

        point(x, y);

        if (checkCollision() == 0)
            xv = -xv;
        else if (checkCollision() == 1)
            yv = -yv;

        x += xv;
        y += yv;

        text("X: " + x, 20, 20);
        text("Y: " + y, 20, 40);
        text("colliding: " + checkCollision(), 20, 60);
    }

    // is something in a colliding space?
    int checkCollision() {

        if (x >= width || x <= 0)
            return 0;
        else if (y <= 0 || y >= height)
            return 1;
        else if (x == width / 2 && (y >= 20 || y <= 400))
            return 0;

        else
            return -1;
    }

    public static void main(String[] args) {

        PApplet.main("techdemo.CollisionDemo");
    }
}