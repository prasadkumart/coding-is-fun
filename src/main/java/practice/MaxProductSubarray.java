package practice;

//https://leetcode.com/problems/maximum-product-subarray/
//TC: O(n)
//KADANCE's Algo

//SC: O(1)
public class MaxProductSubarray {
    public int maxProduct(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        //TC: O(n^2)
        //SC: O(1)
        /*int maxProd = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++) {
            int localProd = 1;
            for(int j=i; j<nums.length; j++) {
                localProd *= nums[j];
                maxProd = Math.max(maxProd, localProd);
            }
        }
        return maxProd;*/

        int bestProduct = nums[0];
        int currMax = nums[0];
        int currMin = nums[0];

        for(int i=1; i<nums.length; i++) {
            //if (nums[i] != 0) {
                int temp = currMax;
                currMax = Math.max(nums[i], Math.max(currMin*nums[i], currMax*nums[i]));
                currMin = Math.min(nums[i], Math.min(currMin*nums[i], temp*nums[i]));
            //}
            bestProduct = Math.max(bestProduct, currMax);
        }

        return bestProduct;
    }

    public static void main(String[] args) {
        System.out.println(new MaxProductSubarray().maxProduct(new int[]{2,3,-2,4})); //6
        System.out.println(new MaxProductSubarray().maxProduct(new int[]{-2,0,1})); //1
        System.out.println(new MaxProductSubarray().maxProduct(new int[]{-2,0,-1})); //0
        System.out.println(new MaxProductSubarray().maxProduct(new int[]{-2,5,0,1,7})); //7 [1,7]
        System.out.println(new MaxProductSubarray().maxProduct(new int[]{-2,3,-4})); //3
        System.out.println(new MaxProductSubarray().maxProduct(new int[]{0,0,0})); //24
    }
}
