//Written by Rohan Rokkam, rokka003
//Game class is meant to run the Battleboats game
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("Type \"game\" for Game Mode or \"debug\" for Debug Mode");
        Scanner myScanner = new Scanner(System.in);
        String mode = myScanner.nextLine();

        if (mode.equals("game")) { //GAME MODE
            System.out.println("Choose Difficulty: \nType \"0\" for Beginner (3x3 Board)(1 Power Point) \nType \"1\" for Intermediate (6x6 Board)(3 Power Points) \nType \"2\" for Expert (9x9 Board)(5 Power Points)");
            String difficulty = myScanner.nextLine();
            Board myBoard = new Board(Integer.parseInt(difficulty));
            myBoard.placeBoats();
            boolean looper = true;
            while (looper) {
                System.out.println("Select Attack Type:\nREGULAR ATTACK:\n   Type in \"Fire\"\nPOWER-UPS:\n   Type in \"Missile\" (launches a 3x3 ranged attack)\n   Type in \"Drone\" (identifies number of targets in a certain row/column)\n   Type in \"Submarine\" (if a boat is hit then the whole boat sinks)");
                String attack = myScanner.nextLine();
                if (attack.equals("Drone")) {
                    myBoard.drone();
                    System.out.println("\nRound: " + myBoard.getRounds());
                } else {
                    System.out.println("x(row) and y(column) coordinates can be chosen from integers 1 through " + myBoard.getLength());
                    System.out.println("Identify Coordinate (\"xCoord yCoord\"): ");
                    String coord = myScanner.nextLine();
                    String[] coordSplit = coord.split(" ");
                    int x = Integer.parseInt(coordSplit[0]) - 1;
                    int y = Integer.parseInt(coordSplit[1]) - 1;
                    if (attack.equals("Fire")) {
                        myBoard.fire(x, y);
                    } else if (attack.equals("Missile")) {
                        myBoard.missile(x, y);
                    } else if (attack.equals("Submarine")) {
                        myBoard.submarine(x, y);
                    }
                    myBoard.display();
                    System.out.println("\nRound: " + myBoard.getRounds());
                }
                if (myBoard.getNumBoats() == myBoard.getSunk()) {
                    looper = false;
                }
            }
        } else  { //DEBUG MODE
            System.out.println("Choose Difficulty: \nType \"0\" for Beginner (3x3 Board)(1 Power Point) \nType \"1\" for Intermediate (6x6 Board)(3 Power Points) \nType \"2\" for Expert (9x9 Board)(5 Power Points)");
            String difficulty = myScanner.nextLine();
            Board myBoard = new Board(Integer.parseInt(difficulty));
            myBoard.placeBoats();
            boolean looper = true;
            while (looper) {
                System.out.println("Select Attack Type:\nREGULAR ATTACK:\n   Type in \"Fire\"\nPOWER-UPS:\n   Type in \"Missile\" (launches a 3x3 ranged attack)\n   Type in \"Drone\" (identifies number of targets in a certain row/column)\n   Type in \"Submarine\" (if a boat is hit then the whole boat sinks)");
                String attack = myScanner.nextLine();
                if (attack.equals("Drone")) {
                    myBoard.drone();
                    System.out.println("\nRound: " + myBoard.getRounds());
                } else {
                    System.out.println("x(row) and y(column) coordinates can be chosen from integers 1 through " + myBoard.getLength());
                    System.out.println("Identify Coordinate (\"xCoord yCoord\"): ");
                    String coord = myScanner.nextLine();
                    String[] coordSplit = coord.split(" ");
                    int x = Integer.parseInt(coordSplit[0]) - 1;
                    int y = Integer.parseInt(coordSplit[1]) - 1;
                    if (attack.equals("Fire")) {
                        myBoard.fire(x, y);
                    } else if (attack.equals("Missile")) {
                        myBoard.missile(x, y);
                    } else if (attack.equals("Submarine")) {
                        myBoard.submarine(x, y);
                    }
                    myBoard.print();
                    System.out.println("\nRound: " + myBoard.getRounds());
                }
                if (myBoard.getNumBoats() == myBoard.getSunk()) {
                    looper = false;
                }
            }
        }
    }
}
