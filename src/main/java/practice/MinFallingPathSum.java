package practice;

import concurrency.SimpleRateLimiter;

public class MinFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        return minFallingPathSumDP(matrix);
        /*
        int minSum = Integer.MAX_VALUE;
        //find min value for each cell in the first row
        //TC: O(n. 3^n)
        //SC: O(n)
        for(int i=0; i<matrix.length; i++) { //O(n)
            minSum = Math.min(minSum, minFallingPathSumBF(matrix, 0, i)); //O(3^n)
        }
        return minSum;
        */
    }

    //becomes 3-ary recursion tree; and tree haa depth of the rows in the matrix (n): 3^n
    //TC: O(3^n)
    private int minFallingPathSumBF(int[][] matrix, int r, int c) {
        //base case
        //out of bounds
        if (c<0 || c>=matrix.length) return Integer.MAX_VALUE;
        //when reached last row
        if (r == matrix.length-1) return matrix[r][c];

        int left = minFallingPathSumBF(matrix, r+1, c-1);
        int mid = minFallingPathSumBF(matrix, r+1, c);
        int right = minFallingPathSumBF(matrix,r+1, c+1);

        return matrix[r][c] + Math.min(left, Math.min(mid, right));
    }

    //TC: O(n^2)
    //SC: O(n)
    private int minFallingPathSumDP(int[][] matrix) {
        int minSum = Integer.MAX_VALUE;
        int SIZE = matrix.length;
        int dp[][] = new int[SIZE][SIZE];

        //TC: //O(n*n)
        for(int r=0; r<SIZE; r++) {//O(n)
            for(int c=0; c<SIZE; c++) {//O(n)
                //first row
                if (r==0) {
                    dp[r][c] = matrix[r][c];
                } else {
                    int top = dp[r-1][c];
                    int topLeft = (c-1>=0) ? dp[r-1][c-1] : Integer.MAX_VALUE;
                    int topRight = (c+1<=SIZE-1) ? dp[r-1][c+1] : Integer.MAX_VALUE;

                    dp[r][c] = matrix[r][c] + Math.min(topLeft, Math.min(top,topRight));
                }

                if(r== SIZE-1) {
                    minSum = Math.min(minSum, dp[r][c]);
                }
            }
        }

        return minSum;
    }

    public static void main(String[] args) {
        System.out.println(new MinFallingPathSum().minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}})); //13
        System.out.println(new MinFallingPathSum().minFallingPathSum(new int[][]{{-19,57},{-40,-5}})); //-59
    }
}
