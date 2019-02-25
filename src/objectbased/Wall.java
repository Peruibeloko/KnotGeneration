package objectbased;

import processing.core.*;

import static objectbased.NodeType.*;

public class Wall extends PApplet{

    private Node[] nodes = new Node[2];

    public Wall(Node start, Node end) throws NodeTypeMismatchException{

        if(start.getType() == PRIMARY && end.getType() == PRIMARY ||
                start.getType() == CROSSING && end.getType() == CROSSING) {

            nodes[0] = start;
            nodes[1] = end;

        } else {

            throw new NodeTypeMismatchException("Walls must be created between nodes of similar type, being either primary or crossing.");
        }
    }

    // TODO Add collision participation
}