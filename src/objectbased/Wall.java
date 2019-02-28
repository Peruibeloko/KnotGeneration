package objectbased;

import processing.core.*;

import java.util.ArrayList;

public class Wall {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private Node[] nodes = new Node[2];
    private int type;

    private ArrayList<CollisionHandler> listeners = new ArrayList<CollisionHandler>();


    public Wall(Node start, Node end, Pilot pilot){

        if (start.getType() == end.getType()) {

            if (start.getCoord()[0] == end.getCoord()[0])
                type = HORIZONTAL;
            else
                type = VERTICAL;

            nodes[0] = start;
            nodes[1] = end;
        }

        listeners.add(pilot);
    }

    public Node getNode(int i) {

        return nodes[i];
    }

    void collisionEmmit(PVector pos) {

        if (type == VERTICAL &&
                pos.x == nodes[0].getPos().x &&
                pos.y >= nodes[0].getPos().y &&
                pos.y <= nodes[1].getPos().y) {

            for (CollisionHandler ch : listeners)
                ch.collisionEvent(0);

        } else if (type == HORIZONTAL &&
                pos.y == nodes[0].getPos().y &&
                pos.x >= nodes[0].getPos().x &&
                pos.x <= nodes[1].getPos().x) {

            for (CollisionHandler ch : listeners)
                ch.collisionEvent(1);

        } else {

            for (CollisionHandler ch : listeners)
                ch.collisionEvent(-1);
        }
    }
}