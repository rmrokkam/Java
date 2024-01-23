//Written by Rohan Rokkam, rokka003
//Board class creates a board with cells and boats assigned to certain cells. Board class is used in Game class.
public class Board {
    private Cell[][] board;//Member variable for double Cell array containing cells within the board
    private Boat[] boats; //Member variable for boat array containing boats within the board
    private int rounds = 1; //Member variable for number of rounds passed by in game
    private int numBoats; //Member variable for number of boats in board
    private int[] boatSize; //Member variable for int array containing sizes of boats in board
    private int powerPoints;//Member variable for number of power points remaining
    private int sunk=0; //Member variable for number of boats sunk in board

    public Board(int difficulty) {
        int size=0;
        if (difficulty == 0) {
            size = 3;
            numBoats = 1;
            boatSize = new int[]{2};
            powerPoints = 1;
        }
        else if (difficulty == 1) {
            size = 6;
            numBoats = 3;
            boatSize = new int[]{2, 3, 4};
            powerPoints = 3;
        }
        else if (difficulty == 2) {
            size = 9;
            numBoats = 5;
            boatSize = new int[]{2, 3, 3, 4, 5};
            powerPoints = 5;
        }
        this.board = new Cell[size][size];
        boats = new Boat[numBoats];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(i, j, '-');
            }
        }
    }
    public int getLength(){
        return board.length;
    }
    public int getRounds() {
        return rounds;
    }

    public int getSunk() {
        return sunk;
    }

    public int getNumBoats() {
        return numBoats;
    }

    public void placeBoats() {
        for (int i = 0; i < numBoats; i++) {
            boolean looper = true;
            while (looper) {
                if (Math.random() < 0.5) {
                    int randX = (int) (Math.random() * board.length);
                    int randY = (int) (Math.random() * (board.length - boatSize[i]));
                    boolean canPlaceBoat = true;
                    for (int y = randY; y < boatSize[i] + randY; y++) {
                        if (board[randX][y].get_status() != '-') {
                            canPlaceBoat = false;
                        }
                    }
                    if (canPlaceBoat == true) {
                        Cell[] boatCells = new Cell[boatSize[i]];
                        Boat newBoat = new Boat(boatSize[i]);
                        newBoat.setOrientation(true);
                        for (int y = randY; y < boatSize[i] + randY; y++) {
                            board[randX][y].set_status('B');
                            boatCells[y - randY] = board[randX][y];
                        }
                        newBoat.setCoord(boatCells);
                        for (int m = 0; m < boats.length; m++) {
                            if (boats[m] == null) {
                                boats[m] = newBoat;
                                break;
                            }
                        }
                        looper = false;
                    }
                } else {
                    int randX = (int) (Math.random() * (board.length - boatSize[i]));
                    int randY = (int) (Math.random() * board.length);
                    boolean canPlaceBoat = true;
                    for (int x = randX; x < boatSize[i] + randX; x++) {
                        if (board[x][randY].get_status() != '-') {
                            canPlaceBoat = false;
                        }
                    }
                    if (canPlaceBoat == true) {
                        Cell[] boatCells = new Cell[boatSize[i]];
                        Boat newBoat = new Boat(boatSize[i]);
                        newBoat.setOrientation(false);
                        for (int x = randX; x < boatSize[i] + randX; x++) {
                            board[x][randY].set_status('B');
                            boatCells[x - randX] = board[x][randY];
                        }
                        newBoat.setCoord(boatCells);
                        for (int m = 0; m < boats.length; m++) {
                            if (boats[m] == null) {
                                boats[m] = newBoat;
                                break;
                            }
                        }
                        looper = false;
                    }
                }
            }
        }
    }

    public Boat findBoat(int x, int y) { // x,y of cell in board
        //for each boat
        for (int i = 0; i < boats.length; i++) {
            for (int j = 0; j < boats[i].getSize(); j++) {
                if (boats[i].getCoord()[j].getRow() == x && boats[i].getCoord()[j].getCol() == y) {
                    return boats[i];
                }
            }
        }
        return null;
    }

    public void fire(int x, int y) {
        boolean inside = true;
        if (x < 0 || y < 0 || x >= board.length || y >= board.length) {
            inside = false;
        }
        if (inside == false || board[x][y].get_status() == 'H' || board[x][y].get_status() == 'M') {//recall print penalty (out of bounds or already called)
            int lossRound = rounds + 1;
            rounds = rounds + 2;
            System.out.println("(" + (x+1) + ", " + (y+1) + ")Already guessed or out of bounds. Player is penalized by losing round " + lossRound);
        } else if (board[x][y].get_status() == 'B') {//hit print hit or sunk
            board[x][y].set_status('H');
            Boat boat = findBoat(x, y);
            if (boat.checkIfSunk() == true) {
                sunk++;
                System.out.println("(" + (x+1) + ", " + (y+1) + ") Sunk");
            } else {
                System.out.println("(" + (x+1) + ", " + (y+1) + ") Hit");
            }
            rounds++;
        } else if (board[x][y].get_status() == '-') {//miss print miss
            board[x][y].set_status('M');
            System.out.println("(" + (x+1) + ", " + (y+1) + ") has been selected and there was no boat located at the selected coordinate");
            rounds++;
        }
    }

    public void missileFire(int x, int y) {
        boolean inside = true;
        if (x < 0 || y < 0 || x >= board.length || y >= board.length) {
            inside = false;
        }
        if (inside == false || board[x][y].get_status() == 'H' || board[x][y].get_status() == 'M') {//recall print penalty (out of bounds or already called)
            System.out.println(("(" + (x+1) + ", " + (y+1) +")Already guessed coordinate or out of bounds"));
        } else if (board[x][y].get_status() == 'B') {//hit print hit or sunk
            board[x][y].set_status('H');
            Boat boat = findBoat(x, y);
            if (boat.checkIfSunk() == true) {
                sunk++;
                System.out.println("(" + (x+1) + ", " + (y+1) + ")Sunk");
            } else {
                System.out.println("(" + (x+1) + ", " + (y+1) + ")Hit");
            }
        } else if (board[x][y].get_status() == '-') {//miss print miss
            board[x][y].set_status('M');
            System.out.println("(" + (x+1) + ", " + (y+1) + ")Miss");
        }
    }

    public void missile(int x, int y) {
        if (powerPoints>0){
            missileFire(x, y);
            missileFire(x + 1, y);
            missileFire(x - 1, y);
            missileFire(x, y + 1);
            missileFire(x, y - 1);
            missileFire(x + 1, y + 1);
            missileFire(x + 1, y - 1);
            missileFire(x - 1, y + 1);
            missileFire(x - 1, y - 1);
            rounds++;
            powerPoints--;
            System.out.println(powerPoints+" power points left");
        }
        else{
            System.out.println("Out of Power Points");
        }

    }

    public void drone() {
        if(powerPoints>0){
            int totalTargets = 0;
            if (Math.random() < 0.5) {
                int randY = (int) (Math.random() * (board.length));
                for(int i=0; i<board.length; i++){
                    if (board[i][randY].get_status()=='B' || board[i][randY].get_status()=='H'){
                        totalTargets++;
                    }
                }
                System.out.println("Drone has scanned "+totalTargets+" target(s) in row "+randY);
            }
            else{
                int randX = (int) (Math.random() * (board.length));
                for(int j=0; j<board.length; j++){
                    if (board[randX][j].get_status()=='B' || board[randX][j].get_status()=='H'){
                        totalTargets++;
                    }
                }
                System.out.println("Drone has scanned "+totalTargets+" target(s) in column "+randX);
            }
            powerPoints--;
            System.out.println(powerPoints+" power points left");
        }
        else{
            System.out.println("Out of Power Points");
        }
    }

    public void submarine(int x, int y) { //ASK TA ABOUT THIS!!!
        if (powerPoints>0){
            if (board[x][y].get_status() == '-' || board[x][y].get_status() == 'M') {
                board[x][y].set_status('M');
                System.out.println("Miss");
            } else {
                Boat subBoat = findBoat(x, y);
                Cell[] temp = subBoat.getCoord();
                for (int i = 0; i < subBoat.getSize(); i++) {
                    missileFire((temp[i].getRow()), (temp[i].getCol()));
                }
            }
            powerPoints--;
            System.out.println(powerPoints+" power points left");
        }
        else{
            System.out.println("Out of Power Points");
        }
    }

    public void display() {
        String grid = "";
        for (int i=0; i<board.length; i++){
            grid += "\n";
            for(int j=0; j< board.length;j++){
                if(board[i][j].get_status()=='-'){
                    grid+=" - ";
                }
                else if(board[i][j].get_status()=='H'){
                    grid+=" H ";
                }
                else if(board[i][j].get_status()=='M'){
                    grid+=" M ";
                }
                else if(board[i][j].get_status()=='B'){
                    grid+=" - ";
                }
            }
        }
        System.out.println(grid);
    }

    public void print() {
        String grid = "";
        for (int i=0; i<board.length; i++){
            grid += "\n";
            for(int j=0; j< board.length;j++){
                if(board[i][j].get_status()=='-'){
                    grid+=" - ";
                }
                else if(board[i][j].get_status()=='H'){
                    grid+=" H (Size: "+findBoat(i,j).getSize()+") ";
                }
                else if(board[i][j].get_status()=='M'){
                    grid+=" M ";
                }
                else if(board[i][j].get_status()=='B'){
                    grid+=" B (Size: "+findBoat(i,j).getSize()+") ";
                }
            }
        }
        System.out.println(grid);
    }
}
