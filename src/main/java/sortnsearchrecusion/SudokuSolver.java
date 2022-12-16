package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuSolver {
    static ArrayList<ArrayList<Integer>> solve_sudoku_puzzle(ArrayList<ArrayList<Integer>> board) {
        recursive_backtracking(board);
        return board;
    }

    static boolean recursive_backtracking(ArrayList<ArrayList<Integer>> board) {
        // Look for the next unfilled cell.
        int row = 0;
        int col = 0;
        boolean foundUnfilledCell = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j) == 0) {
                    // Found unfilled cell.
                    row = i;
                    col = j;
                    foundUnfilledCell = true;
                    break;
                }
            }
            if (foundUnfilledCell) {
                break;
            }
        }

        if (!foundUnfilledCell) {
            // No unfilled cells left. That means a solution is found.
            return true;
        }

        // Look for a number (1..9) that "is safe", i.e. can feasibly be placed in this unfilled cell.
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                // Found a safe number, put it to the board.
                board.get(row).set(col, num);

                // Recurse to find and fill next unfilled cell.
                if (recursive_backtracking(board)) {
                    return true;
                } else {
                    // Placing number num to this unfilled cell does not lead to a solution.
                    // So we undo placing it to the board:
                    board.get(row).set(col, 0);
                    // ... and continue with the `for` loop.
                    // That will lead to trying other numbers: backtracking.
                }
            }
        }

        // Starting from the state of the board passed to this call, no solution is possible.
        // This cannot be the initial call (root call in the recursion hierarchy of calls) because
        // problem statement guarantees that a solution exists for every test board.
        // So returning false will lead to backtracking.
        return false;
    }

    /**
     * Checks if number num is safe to place on board[row, col].
     */
    static boolean isSafe(ArrayList<ArrayList<Integer>> board, int row, int col, int num) {
        // Check if the number is already present in the row.
        // We could use hashtable (HashSet in Java) to get rid of the following cycle,
        // but with N=9 it's going to make things slower on many real CPUs. That's worth
        // considering for larger Sudoku boards though.
        for (int i = 0; i < 9; i++) {
            if (board.get(row).get(i) == num) {
                return false;
            }
        }

        // Check if the number is already present in the column.
        for (int i = 0; i < 9; i++) {
            if (board.get(i).get(col) == num) {
                return false;
            }
        }

        // Check if the number is already present in the corresponding 3 x 3 box.
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board.get(i).get(j) == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        board.add(new ArrayList<>(Arrays.asList(8, 4, 9, 0, 0, 3, 5, 7, 0)));
        board.add(new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0, 0, 0, 0, 0)));
        board.add(new ArrayList<>(Arrays.asList(7, 0, 0, 0, 9, 0, 0, 8, 3)));
        board.add(new ArrayList<>(Arrays.asList(0, 0, 0, 9, 4, 6, 7, 0, 0)));
        board.add(new ArrayList<>(Arrays.asList(0, 8, 0, 0, 5, 0, 0, 4, 0)));
        board.add(new ArrayList<>(Arrays.asList(0, 0, 6, 8, 7, 2, 0, 0, 0)));
        board.add(new ArrayList<>(Arrays.asList(5, 7, 0, 0, 1, 0, 0, 0, 4)));
        board.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 1, 0)));
        board.add(new ArrayList<>(Arrays.asList(0, 2, 1, 7, 0, 0, 8, 6, 5)));

        System.out.println(solve_sudoku_puzzle(board));
    }
}
