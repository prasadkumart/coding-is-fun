package practice;

import java.util.Arrays;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        //values could +ve total of all elements in the array or -ve of the total
        //find array total
        int numsTotal = Arrays.stream(nums).sum();

        int[][] memo = new int[nums.length][2*numsTotal+1];
        return helper(nums, 0, 0, target, memo);
    }

    int helper(int[] nums, int index, int total, int target, int[][] memo) {
        if (index == nums.length) {
            if (total == target) {
                return 1;
            } else {
                return 0;
            }
        }

        if (memo[index][total] != Integer.MIN_VALUE) {
            return memo[index][total];
        }

        int add = helper(nums, index+1, total+nums[index], target, memo);
        int subtract = helper(nums, index+1, total-nums[index], target, memo);
        return add + subtract;
    }

    public static void main(String[] args) {
        System.out.println(new TargetSum().findTargetSumWays(new int[]{1,1,1,1,1},3)); //5
        System.out.println(new TargetSum().findTargetSumWays(new int[]{1},1)); //1
    }
}
