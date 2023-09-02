package dp;

//https://leetcode.com/problems/minimum-falling-path-sum/
public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;
        int minSum = Integer.MAX_VALUE;
        Integer[][] memo = new Integer[ROWS][COLS];

        //multi BFS on the first each
        for(int col=0; col<COLS; col++) {
            minSum = Math.min(minSum, getPathSum(matrix, 0, col, memo));
        }

        return minSum;
    }

    private int getPathSum(int[][] matrix, int row, int col, Integer[][] memo) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        // base case
        if (col<0 || col >= COLS) {
            return Integer.MAX_VALUE;
        }

        //return cell value, when last row is reached
        if (row == matrix.length-1) {
            return matrix[row][col];
        }

        //been calculated before
        if (memo[row][col] != null) {
            return memo[row][col];
        }

        int left = getPathSum(matrix, row+1, col-1, memo);
        int middle = getPathSum(matrix, row+1, col, memo);
        int right = getPathSum(matrix, row+1, col+1, memo);

        memo[row][col] = Math.min(left, Math.min(middle, right)) + matrix[row][col];
        return memo[row][col];
    }

    public static void main(String[] args) {
        System.out.println(new MinimumFallingPathSum().minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}})); //13
        System.out.println(new MinimumFallingPathSum().minFallingPathSum(new int[][]{{-19,57},{-40,-5}})); //-59
        System.out.println(new MinimumFallingPathSum().minFallingPathSum(new int[][]{{9}})); //9
    }
}
