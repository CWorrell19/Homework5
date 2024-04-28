//Conner Worrell
import java.util.Scanner;

public class TicTacToe {
    // Function to print the game board
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(row[0] + " | " + row[1] + " | " + row[2]);
            System.out.println("- - -");
        }
    }

    // Function to check if a player has won
    public static boolean checkWinner(char[][] board, char player) {
        // Check rows
        for (char[] row : board) {
            if (row[0] == player && row[1] == player && row[2] == player) {
                return true;
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    // Function to check if the board is full
    public static boolean checkTie(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false; // If any cell is empty, the game is not a tie
                }
            }
        }
        return true; // All cells are filled, the game is a tie
    }

    // Function to update and display the score
    public static void displayScore(int[] score) {
        System.out.println("Score:");
        System.out.println("Player 1: " + score[0] + " wins");
        System.out.println("Player 2: " + score[1] + " wins");
    }

    // Main function to play the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        int[] score = new int[2]; // Index 0 for Player 1, Index 1 for Player 2
        char currentPlayer = 'X';

        // Main game loop
        while (true) {
            printBoard(board);
            System.out.println("Player " + currentPlayer + " turn:");
            System.out.print("Enter row (1-3): ");
            int row = scanner.nextInt() - 1;
            System.out.print("Enter column (1-3): ");
            int col = scanner.nextInt() - 1;

            // Check if the selected position is valid and empty
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                // Check if the current player has won
                if (checkWinner(board, currentPlayer)) {
                    printBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                    score[currentPlayer == 'X' ? 0 : 1]++;
                    displayScore(score);
                    break; // End the game
                }
                // Check if the game is a tie
                else if (checkTie(board)) {
                    printBoard(board);
                    System.out.println("It's a tie!");
                    displayScore(score);
                    break; // End the game
                }
                // Switch to the other player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }
}
