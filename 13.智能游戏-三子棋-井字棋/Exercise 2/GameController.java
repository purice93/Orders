package tic;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mrahman on 5/24/2017.
 */
public class GameController {
	// Game states
	private final int PLAYING = 0;
	private final int DRAW = 1;
	private final int X_WIN = 2;
	private final int O_WIN = 3;
	private List<Integer> list;

	// Players
	private final char CROSS = 'x';
	private final char NOUGHT = '0';

	private int currentRow, currentCol;

	private int gameState;
	private char currentPlayer;
	private Board board;

	private static Scanner kb;

	public GameController() {
		this.gameState = PLAYING;
		board = new Board();
		kb = new Scanner(System.in);

	}

	private void playerMove(char curPlayer) {

		int row, col;
		
		//<modify
		while (true) {
			if (curPlayer == CROSS) {
				System.out.println("Player X(Computer):\n");
				GameController gcon = new GameController();
				gcon.list = new LinkedList<>();
				if (board.attack(curPlayer, gcon)) {//Computer two pieces together, and the connection has a vacancy, the Lazi in the vacancy.
				} else if (board.defend(curPlayer, gcon)) {//Players two pieces together, and the connection has a space, the Lazi in the vacancy.
				} else if(board.center(curPlayer, gcon)){//Start lodging
				} else{
					board.plan(curPlayer, gcon);//Priority to diagonally
				}
				row = gcon.list.get(0);
				col = gcon.list.get(1);
			} else {
				System.out.println("Player O, please enter a valid move [row[0-2] col(0-2)]: ");
				row = kb.nextInt();
				col = kb.nextInt();
				boolean valid = board.placeASymbol(curPlayer, row, col);

				if (!valid) {
					System.out.println("Invalid move; please try again!");
					continue;
				}
			}
			break;

		}
		currentRow = row;
		currentCol = col;

	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	private boolean attrack() {
		// TODO Auto-generated method stub
		return false;
	}
	//modify>
	private void updateState() {

		// System.out.println(currentRow + "," + currentCol);
		if (board.hasWon(CROSS, currentRow, currentCol))
			gameState = X_WIN;
		else if (board.hasWon(NOUGHT, currentRow, currentCol))
			gameState = O_WIN;
		else if (board.isDraw())
			gameState = DRAW;
	}

	private void changeTurn(char curPlayer) {
		if (curPlayer == CROSS)
			currentPlayer = NOUGHT;
		else
			currentPlayer = CROSS;
	}

	public void startGame() {

		board.clear();
		currentPlayer = CROSS;
		gameState = PLAYING;

		board.display();

		while (true) {
			playerMove(currentPlayer);
			board.display();
			updateState();
			if (gameState == X_WIN) {
				System.out.println("X won the game! bye!");
				break;
			} else if (gameState == O_WIN) {
				System.out.println("O won the game! bye!");
				break;
			} else if (gameState == DRAW) {
				System.out.println("It's a draw! Bye!");
				break;
			}

			changeTurn(currentPlayer);
		}

	}
}
