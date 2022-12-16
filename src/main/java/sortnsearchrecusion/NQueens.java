package sortnsearchrecusion;

import java.util.ArrayList;

//https://leetcode.com/problems/n-queens/
public class NQueens {

    //TC: O(N!)
    //first queen will have N choices,
    //second queen will have N-2 choices,
        // For the next queen, we won't attempt to place it in the same column as the first queen,
        // and there must be at least one square attacked diagonally by the first queen as well.
        // and the pattern continues
    //third queen will have N-4 choices
    //This pattern continues, resulting in an approximate time complexity of O(N!)

    //build valid solution in a base case: O(N^2) for N solutions: O(N * N^2)

    //O(N!)+O(N * N^2) = O(N!)

    //SC: O(N * N^2)
    //input - O(1)
    //Aux: initial board - O(N^2)
    //     results - O(N * N^2)

    static ArrayList<ArrayList<String>> find_all_arrangements(Integer n) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int row=0; row<n; row++) { //O(N^2)
            for (int col = 0; col < n; col++) {
                board[row][col] = '-';
            }
        }

        backtrack(n, 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), board, result);

        return result;
    }

    private static void backtrack(Integer n, int row, ArrayList<Integer> usedCols, ArrayList<Integer> posDia, ArrayList<Integer> negDia, char[][] board, ArrayList<ArrayList<String>> result) {
        if (row == n) {
            ArrayList<String> tempSlate = new ArrayList<>();
            for(int r=0; r<n; r++) { //O(N^2)
                tempSlate.add(String.valueOf(board[r]));
            }
            result.add(tempSlate);
            //System.out.println(tempSlate);
            return;
        }

        for(int col=0; col<n; col++) {
            //col in used columns
            // OR +ve Diagonal row + col
            // OR -ve Diagonal row - col
            if (usedCols.contains(col)
                || posDia.contains(row+col)
                || negDia.contains(row-col)) { //O(1)
                continue;
            }

            board[row][col] = 'q';
            usedCols.add(col); //0
            posDia.add(row+col); //3
            negDia.add(row-col); //1-1

            backtrack(n, row+1, usedCols, posDia, negDia, board, result);

            //backtrack
            board[row][col] = '-';
            usedCols.remove(usedCols.size()-1);
            posDia.remove(posDia.size()-1);
            negDia.remove(negDia.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(find_all_arrangements(4));
    }
}
