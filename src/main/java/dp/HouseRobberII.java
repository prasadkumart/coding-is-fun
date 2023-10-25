package dp;

import java.util.Arrays;

//https://leetcode.com/problems/house-robber-ii/description/
public class HouseRobberII {
    //optimized DP
    //TC: O(n)
    //SC: O(1)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //SC: - no auxiliary memory - O(1)
        //int max1 = robFrom(nums, 0, nums.length-2);
        //int max2 = robFrom(nums, 1, nums.length-1);

        //SC: O(N) : two new temp arrays will be used
        //SC: O(n)
        int max1 = robFrom(Arrays.copyOfRange(nums, 0, nums.length-1));
        int max2 = robFrom(Arrays.copyOfRange(nums, 1, nums.length));

        return Math.max(max1, max2);
    }
    int robFrom(int[] nums, int start, int end) {
        int rob1=0, rob2 =0;
        for(int i=start; i<=end; i++) {
            int localRob = Math.max(rob1+nums[i], rob2);
            rob1 = rob2;
            rob2= localRob;
        }

        return rob2;
    }

    int robFrom(int[] nums) {
        int rob1=0, rob2 =0;
        for(int n : nums) {
            int localRob = Math.max(rob1+n, rob2);
            rob1 = rob2;
            rob2= localRob;
        }

        return rob2;
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobberII().rob(new int[]{}));//0
        System.out.println(new HouseRobberII().rob(new int[]{1}));//1
        System.out.println(new HouseRobberII().rob(new int[]{1,2}));//2
        System.out.println(new HouseRobberII().rob(new int[]{1,2,3,1}));//4
        System.out.println(new HouseRobberII().rob(new int[]{2,7,9,3,1}));//11
    }
}
