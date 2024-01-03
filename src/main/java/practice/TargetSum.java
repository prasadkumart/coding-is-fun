package practice;

import java.util.Arrays;

//https://leetcode.com/problems/target-sum/description/
public class TargetSum {
    int count = 0;
    int numsTotal = 0;
    public int findTargetSumWays(int[] nums, int target) {
        //Brute Force TC: O(2^n)
        //calculateBruteForce(nums, 0, 0, target);
        //return count;

        //Brute force without global count variable
        //TC: O(2^n)
        //return calculateBFWithCount(nums, 0, 0, target);

        //MEMOIZATION
        //values could +ve total of all elements in the array or -ve of the total
        //find array total
        numsTotal = Arrays.stream(nums).sum();
        int[][] memo = new int[nums.length][2*numsTotal+1];
        //int array initialized with ZERO by default,
        //nums array sums could be a ZERO as well,
        //so initialize memo array with Integer.MIN_VAL
        for(int i=0; i<memo.length; i++)
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        return helper(nums, 0, 0, target, memo);
    }

    //Brute Force
    //TC: O(2^n)
    //SC: O(n)
    void calculateBruteForce(int[] nums, int i, int sum, int target) {
        if (i == nums.length) {
            if (sum == target) {
                count++;
            }
            return;
        }

        //include/add
        calculateBruteForce(nums, i+1, sum+nums[i], target);
        //exclude/subtract
        calculateBruteForce(nums, i+1, sum-nums[i], target);
    }

    int calculateBFWithCount(int[] nums, int i, int sum, int target) {
        if (i == nums.length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        }

        //include/add
        int add = calculateBFWithCount(nums, i+1, sum+nums[i], target);
        //exclude/subtract
        int sub = calculateBFWithCount(nums, i+1, sum-nums[i], target);

        return add+sub;
    }

    int helper(int[] nums, int index, int total, int target, int[][] memo) {
        if (index == nums.length) {
            if (total == target) {
                return 1;
            } else {
                return 0;
            }
        }

        //Thus, for every call to helper(nums, i, sum, S), we store the result obtained in memo[i][sum+total],
        // where total stands for the sum of all the elements from the input array. The factor of total has been added as an offset to the sum value
        // to map all the sums possible to positive integer range. By making use of memoization,
        // we can get the result of each redundant function call in constant time.
        if (memo[index][total+numsTotal] != Integer.MIN_VALUE) {
            return memo[index][total+numsTotal];
        }

        int add = helper(nums, index+1, total+nums[index], target, memo);
        int subtract = helper(nums, index+1, total-nums[index], target, memo);

        memo[index][total+numsTotal] = add + subtract;
        return memo[index][total+numsTotal];
    }

    public static void main(String[] args) {
        System.out.println(new TargetSum().findTargetSumWays(new int[]{1,1,1,1,1},3)); //5
        System.out.println(new TargetSum().findTargetSumWays(new int[]{1},1)); //1
    }
}
