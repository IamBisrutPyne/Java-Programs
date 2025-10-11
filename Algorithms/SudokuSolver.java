// Metadata Header (MANDATORY)
// -----------------------------
// Program Title: Sudoku Solver (Backtracking)
// Author: [Madipadige-ManishKumar]
// Date: 2025-10-09
//
// Description: Solves a standard 9x9 Sudoku puzzle using the Backtracking algorithm.
//
// Language: Java
//
// Time Complexity (Practical): O(1)
// Time Complexity (Worst-Case): O(9^N), where N is the number of empty cells.
// Space Complexity: O(1).
// -----------------------------

public class SudokuSolver {

    private static final int SIZE = 9; // Standard Sudoku size

    // Function to print the Sudoku board
    public static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Check if placing num at board[row][col] is valid
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    // Recursive function to solve Sudoku
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Find an empty cell
                if (board[row][col] == 0) {
                    // Try digits 1-9
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            // Recursively solve the rest
                            if (solveSudoku(board))
                                return true;

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Solved
    }

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved successfully:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku.");
        }
    }
}
