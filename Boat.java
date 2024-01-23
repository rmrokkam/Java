//Written by Rohan Rokkam, rokka003
//Boat class creates the boats for Board class and checks if a boat has sunk or not
public class Boat {
    private Cell[] Coord; //Member variable containing coordinates of a cell
    private int size; //Member variable for size of boat
    private boolean orientation; //Member variable for orientation of boat

    public Boat(int size) {
        this.size = size;
        this.Coord = new Cell[size];
    }

    public int getSize() {
        return size;
    }

    public void setCoord(Cell[] coord) {
        this.Coord = coord;
    }

    public Cell[] getCoord() {
        return Coord;
    }
    public void setOrientation(boolean orientation){
        this.orientation = orientation;
    }
    public boolean getOrientation() {
        return orientation;
    }

    public boolean checkIfSunk() {
        for (int i = 0; i < Coord.length; i++) {
            if (Coord[i].get_status() == 'B') {
                return false;
            }
        }
        return true;
    }
}
