package objectbased;

import processing.core.*;

public class Wall{

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private Node[] nodes = new Node[2];
    private int type;

    public Wall(Node start, Node end){

        if (start.getType() == end.getType()) {

            if (start.getCoord()[0] == end.getCoord()[0])
                type = HORIZONTAL;
            else
                type = VERTICAL;

            nodes[0] = start;
            nodes[1] = end;
        }
    }

    public Node getNode(int i) {

        return nodes[i];
    }

    int isColliding(PVector pos) {

        if (type == VERTICAL &&
                pos.x == nodes[0].getPos().x &&
                pos.y >= nodes[0].getPos().y &&
                pos.y <= nodes[1].getPos().y) {

            return 0;

        } else if (type == HORIZONTAL &&
                pos.y == nodes[0].getPos().y &&
                pos.x >= nodes[0].getPos().x &&
                pos.x <= nodes[1].getPos().x) {

            return 1;

        } else {

            return -1;
        }
    }
}