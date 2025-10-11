import java.util.Scanner;

/**
 * Program Title: N-Queens Solver
 * Author: [Sreenivasulu-03]
 * Date: 2025-10-07
 *
 * Description: This program solves the N-Queens problem using backtracking.
 * It places N queens on an N×N chessboard so that no two queens attack each other.
 * The program prints all possible solutions and demonstrates recursion, backtracking,
 * and array manipulation in Java.
 *
 * Time Complexity: O(N!)
 * Space Complexity: O(N^2) for the board representation
 */
public class NQueens {

    /**
     * Solves the N-Queens problem and prints all solutions.
     * @param n The size of the chessboard and the number of queens.
     */
    public static void solveNQueens(int n) {
        char[][] board = new char[n][n];

        // Initialize board with empty cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        backtrack(board, 0);
    }

    /**
     * Recursively tries to place queens row by row using backtracking.
     * @param board The current state of the chessboard.
     * @param row The current row to place a queen.
     */
    private static void backtrack(char[][] board, int row) {
        int n = board.length;

        if (row == n) {
            printBoard(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(board, row + 1);
                board[row][col] = '.'; // Backtrack
            }
        }
    }

    /**
     * Checks if placing a queen at (row, col) is safe.
     * @param board The current board state.
     * @param row Row index.
     * @param col Column index.
     * @return true if safe, false otherwise.
     */
    private static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;

        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    /**
     * Prints the current board configuration.
     * @param board The chessboard to print.
     */
    private static void printBoard(char[][] board) {
        System.out.println("Solution:");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the number of queens (N): ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input! Enter a numeric value: ");
                sc.next();
            }
            int n = sc.nextInt();

            solveNQueens(n);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
