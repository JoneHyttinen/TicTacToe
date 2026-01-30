package tictactoe;

import java.util.Scanner;

public class GameBoard {

	private static char[][] board = new char[3][3];

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		initializeBoard();
		char currentPlayer = 'X';
		boolean gameRunning = true;

		while (gameRunning) {
			printBoard();
			playerMove(input, currentPlayer);

			if (checkWinner(currentPlayer)) {
				printBoard();
				System.out.println("\nPlayer " + currentPlayer + " wins!");
				gameRunning = false;
			} else if (isBoardFull()) {
				printBoard();
				System.out.println("\nIt's a draw!");
				gameRunning = false;
			} else {
				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
			}
		}

		input.close();
	}

	private static void initializeBoard() {
		// Fill board with spaces
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private static void printBoard() {
		// Display the board in a 3x3 grid
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < board.length; i++) {
			sb.append(" ");
			for (int j = 0; j < board[i].length; j++) {
				sb.append(board[i][j]); // add each char
				if (j != (board[i].length - 1)) {
					sb.append(" | ");
				}
			}
			if (i != (board[i].length - 1)) {
				sb.append("\n-----------\n"); // newline after each row
			}

		}

		String currentBoard = sb.toString();
		System.out.println(currentBoard);
	}

	private static void playerMove(Scanner input, char player) {
		// Ask player for row/column input
		System.out.println("\nPlayer " + player + "'s turn.");
		System.out.print("\nPick a row (1-3): ");
		int row = Integer.parseInt(input.nextLine());
		System.out.print("Pick a column (1-3): ");
		int column = Integer.parseInt(input.nextLine());
		// Validate move (cell must be empty)
		boolean cellIsEmpty = false;
		while (!cellIsEmpty) {
			if (board[row - 1][column - 1] == ' ') {
				board[row - 1][column - 1] = player;
				System.out.println("\n" + player + " was placed on the grid!\n");
				cellIsEmpty = true;
			} else {
				System.out.println("\nCell was not empty.\n");
				printBoard();
				System.out.print("\nPick a new row (1-3): ");
				row = Integer.parseInt(input.nextLine());
				System.out.print("Pick a new column (1-3): ");
				column = Integer.parseInt(input.nextLine());
			}
		}

	}

	private static boolean checkWinner(char player) {
		// Check rows, columns, and diagonals
		int sameRow = 0;
		for (char[] row : board) {
			for (char cell : row) {
				if (cell == player) {
					sameRow++;
				}
			}
			if (sameRow != 3) {
				sameRow = 0;
			}
		}

		int sameColumn = 0;
		for (int column = 0; column < board[0].length; column++) {
			for (int row = 0; row < board.length; row++) {
				if (board[row][column] == player) {
					sameColumn++;
				}
			}
			if (sameColumn != 3) {
				sameColumn = 0;
			}
		}

		if (sameRow == 3) {
			return true;
		} else if (sameColumn == 3) {
			return true;
		} else if (board[0][0] == player && board[2][2] == player && board[1][1] == player) {
			return true;
		} else if (board[2][0] == player && board[0][2] == player && board[1][1] == player) {
			return true;
		}
		return false; // placeholder
	}

	private static boolean isBoardFull() {
		// Check if all cells are filled
		int filled = 0;
		for (char[] row : board) {
			for (char cell : row) {
				if (cell != ' ') {
					filled++;
				}
			}
		}
		if (filled == 9) {
			return true;
		}
		return false; // placeholder
	}
}
