package practice;

import java.util.Stack;

//https://leetcode.com/problems/132-pattern/description/
public class One132Pattern {
    public boolean find132pattern(int[] nums) {
        return find132PatternUsingMonoStack(nums);
    }

    //3 for loops
    //TC: O(n^3)
    //SC: O(1)
    boolean find132patternBF(int[] nums) {
        for(int i=0; i<nums.length-2; i++) {
            for(int j=i+1; j<nums.length-1; j++) {
                for(int k=j+1; k<nums.length; k++) {
                    if (nums[k]>nums[i] && nums[j]>nums[k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //2 for loops
    //TC: O(n^2)
    boolean find132patternBetterBF(int[] nums) {
        int numsi = nums[0];
        for(int j=1; j<nums.length-1; j++) {
            numsi = Math.min(numsi, nums[j]);
            for(int k=j+1; k<nums.length; k++) {
                if (nums[k]>numsi && nums[j]>nums[k]) {
                    return true;
                }
            }
        }
        return false;
    }

    //monotanic stack will have values either in an increasing order or decreasing order
    //https://www.geeksforgeeks.org/introduction-to-monotonic-stack-data-structure-and-algorithm-tutorials/
    //for this problem K value is identified from the mono decreasing stack
    boolean find132PatternUsingMonoStack(int[] nums) {
        //min array is populated to get the min value to j
        int[] minArray = new int[nums.length];
        minArray[0] = nums[0];
        //compare with previous value to get min value
        for(int j=1; j<nums.length; j++) {
            minArray[j] = Math.min(minArray[j-1], nums[j]);
        }

        Stack<Integer> stack = new Stack<>();
        for(int j=nums.length-1; j>0; j--){
            while(!stack.isEmpty() && nums[j] > stack.peek()) {
                if (minArray[j]<stack.peek()) {
                    return true;
                }
                stack.pop();
            }
            stack.push(nums[j]);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new One132Pattern().find132pattern(new int[]{1,2,3,4})); //false
        System.out.println(new One132Pattern().find132pattern(new int[]{3,1,4,2})); //true [1,4,2]
        System.out.println(new One132Pattern().find132pattern(new int[]{-1,3,2,0})); //true [-1,3,2], [-1,3,0], [-1,2,0]
    }
}
