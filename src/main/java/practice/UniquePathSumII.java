package practice;

public class UniquePathSumII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }

        if (obstacleGrid[0][0] == 0) {
            dp[0][0]=1;
        }

        for(int i=1;i<n;i++) {
            if (obstacleGrid[0][i] != 1 && dp[0][i-1] != 0) {
                dp[0][i] = 1;
            }
        }

        for(int j=1;j<m;j++) {
            if (obstacleGrid[j][0] != 1 && dp[j-1][0] != 0) {
                dp[j][0] = 1;
            }
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePathSumII().uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));//2
        System.out.println(new UniquePathSumII().uniquePathsWithObstacles(new int[][]{{0,0},{1,1},{0,0}}));//0
    }
}
