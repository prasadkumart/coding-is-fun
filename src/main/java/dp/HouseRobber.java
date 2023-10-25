package dp;

import java.util.Arrays;

//https://leetcode.com/problems/house-robber/
public class HouseRobber {

    //TC: exponent - with overlapping sub problems
    //SC: O(n)
    public int robRecursion(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return robFrom(0, nums);
    }

    int robFrom(int position, int[] nums) {
        if (position >= nums.length) {
            return 0;
        }

        return Math.max(robFrom(position+1, nums), robFrom(position+2, nums) + nums[position]);
    }


    //Recursion with memoization
    //TC: O(N) - with no overlapping sub problems
    //SC: O(N)
    public int robMemo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);

        return robFromMemo(0, nums, memo);
    }

    int robFromMemo(int position, int[] nums, int[] memo) {
        if (position >= nums.length) {
            return 0;
        }

        if (memo[position] != -1) {
            return memo[position];
        }

        memo[position] = Math.max(
                robFromMemo(position+1, nums, memo),
                robFromMemo(position+2, nums, memo) + nums[position]);
        return memo[position];
    }

    //DP
    //TC: O(N)
    //SC: O(N)
    public int robDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length+1];
        dp[1] = nums[0];
        //1,2,3,1
        //indx [0,1,2,3,4]
        //dp   [0,1,2,4,4]
        for(int i=2; i<=nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i-1]);
        }
        return dp[nums.length];
    }

    //optimized DP
    //TC: O(n)
    //SC: O(1)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //1,2,3,1
        //0,0,1,2,4,4
        int rob1 = 0, rob2 = 0;
        for(int n: nums) {
            int temp = Math.max(rob1+n, rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobber().robDP(new int[]{}));//0
        System.out.println(new HouseRobber().robDP(new int[]{1,2}));//2
        System.out.println(new HouseRobber().robDP(new int[]{1,2,3,1}));//4
        System.out.println(new HouseRobber().robDP(new int[]{2,7,9,3,1}));//12
    }
}
