package practice;

//https://leetcode.com/problems/maximum-subarray/description/
public class MaxSumSubarray {
    public int maxSubArray(int[] nums) {
        //int bestSum = Integer.MIN_VALUE;

        if (null == nums || nums.length ==0) {
            return 0;
        }

        if (nums.length ==1) {
            return nums[0];
        }

        //int len = nums.length;
        //Brute Force TC: O(n^2)
        /*for(int i=0; i<nums.length; i++) { //O(n)
            int localSum = 0;
            for (int j=i; j<nums.length; j++) { //O(n)
                localSum += nums[j];
                bestSum = Math.max(bestSum, localSum);
            }
        }*/

        //TC: O(n)
        //SC: O(n)
        //dp
        /*int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int bestSum = nums[0];

        for(int i=1; i<nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            bestSum = Math.max(bestSum, dp[i]);
        }

        return bestSum;*/

        //TC: O(n)
        //SC: O(1)
        /***********************
         * KADANE's Algorithm
         ***********************/
        int bestSum = nums[0];
        int currSum = nums[0];
        for(int i=1; i<nums.length; i++) {
            currSum = Math.max(nums[i], currSum+nums[i]);
            bestSum = Math.max(bestSum, currSum);
        }

        return bestSum;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSumSubarray().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); //6
        System.out.println(new MaxSumSubarray().maxSubArray(new int[]{1})); //1
        System.out.println(new MaxSumSubarray().maxSubArray(new int[]{5,4,-1,7,8})); //23
        System.out.println(new MaxSumSubarray().maxSubArray(new int[]{-2,-1})); //-1
        System.out.println(new MaxSumSubarray().maxSubArray(new int[]{-1,-2})); //-1
    }
}
