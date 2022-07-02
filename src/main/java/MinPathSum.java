//https://leetcode.com/problems/roman-to-integer/submissions/
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0)
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        //int[][] sums = new int[rows][cols];

        /*//populate first row
        for(int i=1; i< cols; i++) { //N
            grid[0][i] += grid[0][i-1];
        }

        //populate first column
        for(int j=1; j< rows; j++) { //M
            grid[j][0] += grid[j-1][0];
        }

        for (int i=1; i<rows; i++) { //N*M
            for (int j=1; j<cols; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }*/

        for (int i = 0; i < rows; i++) { //N*M
            for (int j = 0; j < cols; j++) {
                if (i > 0 && j > 0) {
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                } else if (i > 0) {
                    grid[i][j] += grid[i - 1][j];
                } else if (j > 0) {
                    grid[i][j] += grid[i][j - 1];
                }
            }
        }

        return grid[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        System.out.println(new MinPathSum().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(new MinPathSum().minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));

    }
}
