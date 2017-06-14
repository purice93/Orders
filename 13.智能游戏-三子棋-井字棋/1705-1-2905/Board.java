/**
 * Created by mrahman on 5/24/2017.
 */
public class Board {
    public final int ROWS = 3;
    public final int COLS = 3;
    private char b[][];



    public Board() {
        this.b = new char[ROWS][COLS];

    }

    public boolean isFull(){


        return false;
    }

    public void clear(){

        for(int i=0; i<ROWS; i++)
            for(int j=0; j<COLS; j++)
                b[i][j] = ' ';

    }

    public boolean hasWon(char theSeed, int currentRow, int currentCol){

        return ( b[currentRow][0] == theSeed         // 3-in-the-row
                    && b[currentRow][1] == theSeed
                    && b[currentRow][2] == theSeed
                || b[0][currentCol] == theSeed      // 3-in-the-column
                    && b[1][currentCol] == theSeed
                    && b[2][currentCol] == theSeed
                || currentRow == currentCol            // 3-in-the-diagonal
                    && b[0][0] == theSeed
                    && b[1][1] == theSeed
                    && b[2][2] == theSeed
                || currentRow + currentCol == 2
                    && b[0][2] == theSeed
                    && b[1][1] == theSeed
                    && b[2][0] == theSeed
        );


    }

    public boolean isDraw(){
        for (int row = 0; row < ROWS; ++row) {

            for (int col = 0; col < COLS; ++col) {

                if (b[row][col] == ' ') {

                    return false; // an empty seed found, not a draw, exit

                }

            }

        }

        return true; // no empty cell, it's a draw


    }

    public boolean isValidCell(int r, int c){

        if(r >=0 && r <ROWS && c>=0 && c<COLS
                && b[r][c] == ' ')
            return true;
        return false;
    }


    public boolean placeASymbol(char sym, int r, int c){
        if(isValidCell(r, c)){
            b[r][c] = sym;
            return true;
        }
        return false;
    }

    public void display(){
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                System.out.print(" " + b[row][col] + " ");   // each cell paints itself
                if (col < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("-----------");
            }
        }
    }

    public char getSymbolAt(int r, int c){
        if(isValidCell(r, c))
            return b[r][c];
        return ' ';
    }


}
