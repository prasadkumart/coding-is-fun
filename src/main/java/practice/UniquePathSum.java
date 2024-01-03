package practice;

//https://leetcode.com/problems/unique-paths/
//TC: recursive 2^(m*n)
//TC: O(m*n)
//SC: O(m*n)
public class UniquePathSum {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0; i<n; i++) {
            dp[0][i] = 1;
        }
        for(int j=1; j<m; j++) {
            dp[j][0] = 1;
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePathSum().uniquePaths(3,2));//3
        System.out.println(new UniquePathSum().uniquePaths(3,3));//6
        System.out.println(new UniquePathSum().uniquePaths(3,7));//28
    }
}
