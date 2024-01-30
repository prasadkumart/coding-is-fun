package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/triangle/
//2
//3,4
//6,5,7
//4,1,8,3
public class MinTotalTriangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int minTotal = Integer.MAX_VALUE;
        int ROWS = triangle.size();
        int[][] dp = new int[ROWS][ROWS];
        for(int row=0; row<triangle.size(); row++) {
            for(int col=0; col<=row; col++) {
                if (row==0 && col==0) { //first element
                    dp[0][0] = triangle.get(row).get(col);
                } else {
                    if (col==0) {
                        dp[row][0] = dp[row-1][0] + triangle.get(row).get(0);
                    } else if (col==row) {
                        dp[row][col] = dp[row-1][col-1] + triangle.get(row).get(col);
                    } else {
                        dp[row][col] = Math.min(dp[row-1][col], dp[row-1][col-1]) + triangle.get(row).get(col);
                    }
                }
                if (row == ROWS-1) {
                    minTotal = Math.min(minTotal, dp[row][col]);
                }
            }
        }

        return minTotal;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));

        System.out.println(new MinTotalTriangle().minimumTotal(triangle)); //2 + 3 + 5 + 1 = 11

        triangle = new ArrayList<>();
        triangle.add(Arrays.asList(-10));
        System.out.println(new MinTotalTriangle().minimumTotal(triangle)); //-10
    }
}
