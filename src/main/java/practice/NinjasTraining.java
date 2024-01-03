package practice;

import java.util.Arrays;

public class NinjasTraining {
    public static int ninjaTraining(int n, int[][] points) {
        //return helper(n-1, 3, points);
        //Create a dp array of size [n][4].
        // There are total ‘n’ days and for every day, there can be 4 choices (0,1,2 and 3).
        //Therefore we take the dp array as dp[n][4].
        int[][] dp = new int[n][4];
        for(int i=0; i<dp.length; i++)
            Arrays.fill(dp[i], -1);
        return helperWithDP(n-1, 3, points, dp);
    }


    // A: Activities (4) 0,1,2,3
    // D: days - points.length
    // TC:
    static int helper(int day, int lastActivity, int[][] points) {
        //base case
        if (day==0) {
            int localMax = 0;
            //3 activities
            for(int i=0; i<=2; i++) {
                if (i!=lastActivity) {
                    localMax = Math.max(localMax, points[0][i]);
                }
            }

            return localMax;
        }

        int localMax = 0;
        //choose activity other than lastactivity
        //3 activities for current day
        for(int i=0; i<=2; i++) {
            if (i != lastActivity) {
                //recursive call for the previous day
                int currPoints = points[day][i] + helper(day-1, i, points);
                localMax = Math.max(localMax, currPoints);
            }
        }

        return localMax;
    }

    //TC: O(n*4)
    //SC: O(n) + O(n*4)
    static int helperWithDP(int day, int lastActivity, int[][] points, int[][] dp) {
        //base case
        if (day==0) {
            int localMax = 0;
            //3 activities
            for(int i=0; i<=2; i++) {
                if (i!=lastActivity) {
                    localMax = Math.max(localMax, points[0][i]);
                }
            }

            return dp[day][lastActivity]=localMax;
        }

        if (dp[day][lastActivity] != -1) {
            return dp[day][lastActivity];
        }

        int localMax = 0;
        //choose activity other than lastactivity
        for(int i=0; i<=2; i++) {
            if (i != lastActivity) {
                int currPoints = points[day][i] + helperWithDP(day-1, i, points, dp);
                localMax = Math.max(localMax, currPoints);
            }
        }

        return dp[day][lastActivity]=localMax;
    }

    public static void main(String[] args) {
        System.out.println(NinjasTraining.ninjaTraining(3, new int[][]{{1,2,5},{3,1,1},{3,3,3}})); //5+3+3 = 11
        System.out.println(NinjasTraining.ninjaTraining(3, new int[][]{{10,40,70},{20,50,80},{30,60,90}})); //70+50+90 = 210
    }
}
