package sortnsearchrecusion;

import java.util.Arrays;

//https://leetcode.com/problems/rotate-array/
//https://www.youtube.com/watch?v=gmu0RA5_zxs&ab_channel=NickWhite
public class RotateArray {
    public void rotate(int[] nums, int k) {
        if (null == nums && nums.length == 0) {
            return;
        }
        k %= nums.length; // to get the min no of rotation, when the k > length
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);

        System.out.println(Arrays.toString(nums));
    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        new RotateArray().rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }
}
