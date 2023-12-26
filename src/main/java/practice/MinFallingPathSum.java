package practice;

import jdk.swing.interop.DropTargetContextWrapper;

public class MinFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int total = Integer.MAX_VALUE;
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int[][] dp = new int[ROWS][COLS];

        for(int i=0; i<ROWS; i++) {
            for(int j=0; j<COLS; j++) {
                if (i == 0) { // first row
                    dp[i][j] = matrix[i][j];
                } else {
                    int top = Integer.MAX_VALUE;
                    int topLeft = Integer.MAX_VALUE;
                    int topRight = Integer.MAX_VALUE;
                    if (i - 1 >= 0) {
                        top = matrix[i][j] + dp[i - 1][j];
                    }
                    if (j - 1 >= 0) {
                        topLeft = matrix[i][j] + dp[i - 1][j - 1];
                    }
                    if (j + 1 < COLS) {
                        topRight = matrix[i][j] + dp[i - 1][j + 1];
                    }

                    dp[i][j] = Math.min(top, Math.min(topLeft, topRight));
                }
                //last row
                if (i == ROWS - 1) {
                    total = Math.min(total, dp[i][j]);
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        System.out.println(new MinFallingPathSum().minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}}));
    }
}
