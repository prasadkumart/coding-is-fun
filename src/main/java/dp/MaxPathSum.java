package dp;

import java.util.ArrayList;

//DP - maximization
//TC: O(mn) m - no of rows; n - no of columns
//SC: O(mn)
public class MaxPathSum {
    static Integer maximum_path_sum(ArrayList<ArrayList<Integer>> grid) {
        int rowLen = grid.size();
        int colLen = grid.get(0).size();
        int[][] table = new int[rowLen][colLen];

        //Base cases
        //initialize
        table[0][0] = grid.get(0).get(0);
        //first row sums //0th row
        for(int col=1; col<colLen; col++) {
            table[0][col] = grid.get(0).get(col) + table[0][col-1];
        }
        //first col sums //0th column
        for(int row=1; row<rowLen; row++) {
            table[row][0] = grid.get(row).get(0) + table[row-1][0];
        }

        //DP
        for(int row=1; row<rowLen; row++) {
            for (int col=1; col<colLen; col++) {
                table[row][col] = grid.get(row).get(col) + Math.max(table[row-1][col], table[row][col-1]);
            }
        }

        return table[rowLen-1][colLen-1];
    }
}
