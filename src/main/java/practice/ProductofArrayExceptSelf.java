package practice;

import java.util.Arrays;

//https://leetcode.com/problems/product-of-array-except-self/description/
//TC: O(n)
//SC: O(n)
public class ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length]; //O(n) - output array not considered for SC

        int[] left = new int[nums.length]; //SC: O(n)
        left[0] = 1;

        for (int i=1; i<nums.length; i++) { //TC: O(n)
            left[i] = nums[i-1] * left[i-1];
        }
        int rightVal = 1;
        for (int i=nums.length-1; i>=0; i--) { //TC: O(n)
            result[i] = left[i] * rightVal;
            rightVal *= nums[i];
        }

        /*
        int[] right = new int[nums.length]; //SC: O(n)
        right[nums.length-1] = 1;
        for (int i=nums.length-2; i>=0; i--) { //TC: O(n)
            right[i] = nums[i+1] * right[i+1];
        }
        for (int i=0; i<nums.length; i++) { //TC: O(n)
            result[i] = left[i] * right[i];
        }*/

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ProductofArrayExceptSelf().productExceptSelf(new int[]{1,2,3,4})));//[24,12,8,6]
        System.out.println(Arrays.toString(new ProductofArrayExceptSelf().productExceptSelf(new int[]{-1,1,0,-3,3})));//[0,0,9,0,0]
    }
}
