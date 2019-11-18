package objectbased;

import processing.core.PVector;

class Wall {

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    private Node[] nodes = new Node[2];
    private int type;
    private Pilot pilot;

    Wall(Node start, Node end, Pilot pilot) {

        if (start.getType() == end.getType()) {

            if (start.getCoord()[0] == end.getCoord()[0]) { // If x coordinate is the same between both nodes, its a vertical wall

                type = VERTICAL;

                if (end.getCoord()[1] > start.getCoord()[1]) {

                    nodes[0] = start;
                    nodes[1] = end;

                } else {

                    nodes[0] = end;
                    nodes[1] = start;
                }

            } else {

                type = HORIZONTAL;

                if (end.getCoord()[0] > start.getCoord()[0]) {

                    nodes[0] = start;
                    nodes[1] = end;

                } else {

                    nodes[0] = end;
                    nodes[1] = start;
                }
            }
        }

        this.pilot = pilot;
    }

    Node getNode(int i) {

        return nodes[i];
    }

    void collisionEmmit() {

        PVector nextPos = pilot.nextPos();
        if (type == VERTICAL) {

            if (nextPos.y > nodes[0].getPos().y && nextPos.y < nodes[1].getPos().y)
                if (Math.abs(nextPos.x - nodes[0].getPos().x) == Math.abs(pilot.pos.dist(nextPos))) {

                    pilot.collisionEvent(0);

                } else if (Math.abs(nextPos.x - nodes[0].getPos().x) < Math.abs(pilot.pos.dist(nextPos))) {

                    pilot.collisionEvent(0, this);
                }

        } else if (type == HORIZONTAL) {

            if (nextPos.x > nodes[0].getPos().x && nextPos.x < nodes[1].getPos().x)
                if (Math.abs(nextPos.y - nodes[0].getPos().y) == Math.abs(pilot.pos.dist(nextPos))) {

                    pilot.collisionEvent(1);

                } else if (Math.abs(nextPos.y - nodes[0].getPos().y) < Math.abs(pilot.pos.dist(nextPos))) {

                    pilot.collisionEvent(1, this);
                }
        }
    }
}