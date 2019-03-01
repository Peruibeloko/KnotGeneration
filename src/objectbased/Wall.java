package objectbased;

class Wall {

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    private Node[] nodes = new Node[2];
    private int type;
    private Pilot pilot;

    Wall(Node start, Node end, Pilot pilot){

        if (start.getType() == end.getType()) {

            if (start.getCoord()[0] == end.getCoord()[0]) // If x coordinate is the same between both nodes, its a vertical wall
                type = VERTICAL;
            else
                type = HORIZONTAL;

            nodes[0] = start;
            nodes[1] = end;
        }

        this.pilot = pilot;
    }

    Node getNode(int i) {

        return nodes[i];
    }

    void collisionEmmit() {

        if (type == VERTICAL) { // if wall is vertical

            if (pilot.pos.x == nodes[0].getPos().x){ // If pilot is in wall (horizontally)

                if (nodes[1].getPos().y > nodes[0].getPos().y) { // If second node of wall is beneath first
                    
                    if (pilot.pos.y >= nodes[0].getPos().y && pilot.pos.y <= nodes[1].getPos().y) { // If pilot is in wall span
                        
                        pilot.collisionEvent(0);
                    }

                } else { // If second node of wall is on top of first

                    if (pilot.pos.y >= nodes[1].getPos().y && pilot.pos.y <= nodes[0].getPos().y) { // If pilot is in wall span

                        pilot.collisionEvent(0);
                    }
                }
            }

        } else { // If wall is horizontal

            if (pilot.pos.y == nodes[0].getPos().y){ // If pilot is in wall (vertically)

                if (nodes[1].getPos().x > nodes[0].getPos().x) { // If second node of wall is to the right of first

                    if (pilot.pos.x >= nodes[0].getPos().x && pilot.pos.x <= nodes[1].getPos().x) { // If pilot is in wall span

                        pilot.collisionEvent(1);
                    }

                } else { // If second node of wall is to the left of first

                    if (pilot.pos.x >= nodes[1].getPos().x && pilot.pos.x <= nodes[0].getPos().x) { // If pilot is in wall span

                        pilot.collisionEvent(1);
                    }
                }
            }
        }
    }
}