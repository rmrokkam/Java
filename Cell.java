//Written by Rohan Rokkam, rokka003
//Cell class creates the cells for Boat and Board classes
public class Cell {
    private int row; //member variable for row that cell is found in
    private int col; //member variable for column that cell is found in
    private char status; //member variable for status of cell

    public Cell(int row, int col, char status){
        this.row = row;
        this.col = col;
        this.status = status;
    }
    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getCol(){
        return col;
    }
    public void setCol(int col){
        this.col = col;
    }
    public char get_status() {
        return status;
    }

    public void set_status(char c) {
        this.status = c;
    }
}