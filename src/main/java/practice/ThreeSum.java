package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/3sum/description/
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums); //TC: O(n log n) //SC: O(n)
        //3 NESTED LOOPS - TC: O(n^3)
        /*
        for(int i=0; i<nums.length; i++) {
            //compare with prev and move the pointer
            if (i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            for(int j=i+1; j<nums.length; j++) {
                if (j>i+1 && nums[j]==nums[j-1]) {
                    continue;
                }
                for(int k=j+1; k<nums.length; k++) {
                    if (k>j+1 && nums[k]==nums[k-1]) {
                        continue;
                    }
                    if (nums[i]+nums[j]+nums[k] == 0) {
                        result.add(new ArrayList<Integer>(Arrays.asList(nums[i],nums[j],nums[k])));
                    }
                }
            }
        }
        */

        //TC: O(n^2)
        for(int i=0; i<nums.length; i++) {
            //if current element is same as prev (duplicate), shift to right
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            //two element sum
            int left = i+1;
            int right = nums.length-1;
            int target = -nums[i];
            while(left<right) {
                int twoSum = nums[left] + nums[right];
                if (target == twoSum) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                } else if (twoSum > nums[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int[]{-1,0,1,2,-1,-4}));//[[-1,-1,2],[-1,0,1]]
        System.out.println(new ThreeSum().threeSum(new int[]{0,1,1}));//[]
        System.out.println(new ThreeSum().threeSum(new int[]{0,0,0}));//[[0,0,0]]
        System.out.println(new ThreeSum().threeSum(new int[]{0,0,0,0}));//[[0,0,0]]
    }
}
