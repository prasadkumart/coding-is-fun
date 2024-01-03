package practice;

//https://leetcode.com/problems/minimum-path-sum/description/
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        return minPathSumDP(grid);
        //return minPathSumBF(grid, grid.length-1, grid[0].length-1);
    }

    //TC: O(2^m+n),  For every move, we have at most 2 options.
    //SC: O(m+n); Recursion of depth m+n.
    //bottom-up
    int minPathSumBF(int[][] grid, int i, int j) {
        //base case
        if (i==0 && j==0) return grid[0][0];
        //out of bouds
        if (i<0 || j<0) return Integer.MAX_VALUE;

        int up = minPathSumBF(grid, i-1,j);
        int left = minPathSumBF(grid, i, j-1);

        return grid[i][j] + Math.min(up, left);
    }

    //TC: O(mn) //m: rows; n: cols
    //SC: O(mn) //DP array is same size as the original grid
    int minPathSumDP(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];

        for(int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (i==0 && j==0) {
                    dp[i][j] = grid[i][j];
                } else {
                    int up = (i>0) ? dp[i-1][j] : Integer.MAX_VALUE;
                    int left = (j>0) ? dp[i][j-1] : Integer.MAX_VALUE;

                    dp[i][j] = grid[i][j] + Math.min(up, left);
                }
            }
        }
        return dp[rows-1][cols-1];
    }

    public static void main(String[] args) {
        System.out.println(new MinPathSum().minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}})); //7
        System.out.println(new MinPathSum().minPathSum(new int[][]{{1,2,3},{4,5,6}})); //12
    }
}
