package tic;

import java.util.LinkedList;

/**
 * Created
 */
public class Board {
	public final int ROWS = 3;
	public final int COLS = 3;
	private char b[][];

	public Board() {
		this.b = new char[ROWS][COLS];

	}

	public boolean isFull() {

		return false;
	}

	public void clear() {

		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++)
				b[i][j] = ' ';

	}

	public boolean hasWon(char theSeed, int currentRow, int currentCol) {

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

	public boolean isDraw() {
		for (int row = 0; row < ROWS; ++row) {

			for (int col = 0; col < COLS; ++col) {

				if (b[row][col] == ' ') {

					return false; // an empty seed found, not a draw, exit

				}

			}

		}

		return true; // no empty cell, it's a draw

	}

	public boolean isValidCell(int r, int c) {

        if(r >=0 && r <ROWS && c>=0 && c<COLS
                && b[r][c] == ' ')
            return true;
        return false;
    }


	public boolean placeASymbol(char sym, int r, int c) {
		if (isValidCell(r, c)) {
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

	public char getSymbolAt(int r, int c) {
		if (isValidCell(r, c))
			return b[r][c];
		return ' ';
	}

	//<modify
	public boolean attack(char curPlayer, GameController gcon) {
		gcon.setList(new LinkedList<>());
		for (int i = 0; i <= b.length - 1; i++) {
			if (b[i][0] == b[i][1] && b[i][0] == curPlayer && b[i][2] == ' ') {
				b[i][2] = curPlayer;
				gcon.getList().add(i);
				gcon.getList().add(2);
				return true;
			} else if (b[i][0] == b[i][2] && b[i][0] == curPlayer && b[i][1] == ' ') {
				b[i][1] = curPlayer;
				gcon.getList().add(i);
				gcon.getList().add(1);
				return true;
			} else if (b[i][1] == b[i][2] && b[i][1] == curPlayer && b[i][0] == ' ') {
				b[i][0] = curPlayer;
				gcon.getList().add(i);
				gcon.getList().add(0);
				return true;
			} else if (b[0][i] == b[1][i] && b[0][i] == curPlayer && b[2][i] == ' ') {
				b[2][i] = curPlayer;
				gcon.getList().add(2);
				gcon.getList().add(i);
				return true;
			} else if (b[0][i] == b[2][i] && b[0][i] == curPlayer && b[1][i] == ' ') {
				b[1][i] = curPlayer;
				gcon.getList().add(1);
				gcon.getList().add(i);
				return true;
			} else if (b[1][i] == b[2][i] && b[1][i] == curPlayer && b[0][i] == ' ') {
				b[0][i] = curPlayer;
				gcon.getList().add(0);
				gcon.getList().add(i);
				return true;
			}
		}
		if (b[0][0] == b[1][1] && b[0][0] == curPlayer && b[2][2] == ' ') {
			b[2][2] = curPlayer;
			gcon.getList().add(2);
			gcon.getList().add(2);
			return true;
		} else if (b[1][1] == b[2][2] && b[1][1] == curPlayer && b[0][0] == ' ') {
			b[0][0] = curPlayer;
			gcon.getList().add(0);
			gcon.getList().add(0);
			return true;
		} else if (b[0][2] == b[1][1] && b[0][2] == curPlayer && b[2][0] == ' ') {
			b[2][0] = curPlayer;
			gcon.getList().add(2);
			gcon.getList().add(0);
			return true;
		} else if (b[1][1] == b[2][0] && b[1][1] == curPlayer && b[0][2] == ' ') {
			b[0][2] = curPlayer;
			gcon.getList().add(0);
			gcon.getList().add(2);
			return true;
		} else {
			return false;
		}
	}

	public boolean center(char curPlayer, GameController gcon) {
		gcon.setList(new LinkedList<>());
		if (b[1][1] == ' ') {
			b[1][1] = curPlayer;
			gcon.getList().add(1);
			gcon.getList().add(1);
			return true;
		} else {
			return false;
		}
	}

	public boolean defend(char curPlayer, GameController gcon) {
		gcon.setList(new LinkedList<>());
		for (int i = 0; i <= b.length - 1; i++) {
			if (b[i][0] == b[i][1] && b[i][0] == '0' && b[i][2] == ' ') {
				b[i][2] = curPlayer;
				gcon.getList().add(i);
				gcon.getList().add(2);
				return true;
			} else if (b[i][0] == b[i][2] && b[i][0] == '0' && b[i][1] == ' ') {
				b[i][1] = curPlayer;
				gcon.getList().add(i);
				gcon.getList().add(1);
				return true;
			} else if (b[i][1] == b[i][2] && b[i][1] == '0' && b[i][0] == ' ') {
				b[i][0] = curPlayer;
				gcon.getList().add(i);
				gcon.getList().add(0);
				return true;
			} else if (b[0][i] == b[1][i] && b[0][i] == '0' && b[2][i] == ' ') {
				b[2][i] = curPlayer;
				gcon.getList().add(2);
				gcon.getList().add(i);
				return true;
			} else if (b[0][i] == b[2][i] && b[0][i] == '0' && b[1][i] == ' ') {
				b[1][i] = curPlayer;
				gcon.getList().add(1);
				gcon.getList().add(i);
				return true;
			} else if (b[1][i] == b[2][i] && b[1][i] == '0' && b[0][i] == ' ') {
				b[0][i] = curPlayer;
				gcon.getList().add(0);
				gcon.getList().add(i);
				return true;
			}
		}
		if (b[0][0] == b[1][1] && b[0][0] == '0' && b[2][2] == ' ') {
			b[2][2] = curPlayer;
			gcon.getList().add(2);
			gcon.getList().add(2);
			return true;
		} else if (b[1][1] == b[2][2] && b[1][1] == '0' && b[0][0] == ' ') {
			b[0][0] = curPlayer;
			gcon.getList().add(0);
			gcon.getList().add(0);
			return true;
		} else if (b[0][2] == b[1][1] && b[0][2] == '0' && b[2][0] == ' ') {
			b[2][0] = curPlayer;
			gcon.getList().add(2);
			gcon.getList().add(0);
			return true;
		} else if (b[1][1] == b[2][0] && b[1][1] == '0' && b[0][2] == ' ') {
			b[0][2] = curPlayer;
			gcon.getList().add(0);
			gcon.getList().add(2);
			return true;
		} else {
			return false;
		}
	}

	public void plan(char curPlayer, GameController gcon) {

		if (b[0][0] == b[2][2] && b[0][0] == 1 || b[0][2] == b[2][0] && b[0][2] == 1) {
			if (b[0][2] == 0) {
				b[0][2] = curPlayer;
				gcon.getList().add(0);
				gcon.getList().add(2);
			} else {
				b[0][0] = curPlayer;
				gcon.getList().add(0);
				gcon.getList().add(0);
			}
		} else if ((b[0][1] == 1 || b[1][2] == 1) && b[2][0] == 0) {
			b[2][0] = curPlayer;
			gcon.getList().add(2);
			gcon.getList().add(0);
		} else if ((b[1][0] == 1 || b[2][1] == 1) && b[0][2] == 0) {
			b[0][2] = curPlayer;
			gcon.getList().add(0);
			gcon.getList().add(2);

		} else if (b[0][0] == 0) {
			b[0][0] = curPlayer;
			gcon.getList().add(0);
			gcon.getList().add(0);
		} else if (b[0][2] == 0) {
			b[0][2] = curPlayer;
			gcon.getList().add(0);
			gcon.getList().add(2);
		} else if (b[2][0] == 0) {
			b[2][0] = curPlayer;
			gcon.getList().add(2);
			gcon.getList().add(0);
		} else if (b[2][2] == 0) {
			b[2][2] = curPlayer;
			gcon.getList().add(2);
			gcon.getList().add(2);
		} else if (b[0][1] == 0) {
			b[0][1] = curPlayer;
			gcon.getList().add(0);
			gcon.getList().add(1);
		} else if (b[1][0] == 0) {
			b[1][0] = curPlayer;
			gcon.getList().add(0);
		} else if (b[1][2] == 0) {
			b[1][2] = curPlayer;
			gcon.getList().add(1);
			gcon.getList().add(2);
		} else
			b[2][1] = curPlayer;
		gcon.getList().add(2);
		gcon.getList().add(1);
	}
	//modify>
}
