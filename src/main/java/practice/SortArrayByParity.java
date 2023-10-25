package practice;

import java.util.ArrayList;
import java.util.Arrays;

public class SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {
        if (null == nums || nums.length == 0) {
            return null;
        }

        int left=0, right=0;

        //all elements before left are even
        while(right<nums.length) {
            if (nums[right]%2 == 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }

        return nums;
    }

    public static void main(String[] args) {
        //Arrays.stream(new SortArrayByParity().sortArrayByParity(new int[]{3, 1, 2, 4})).forEach(System.out::println); //2,4,3,1
        System.out.println(Arrays.toString(new SortArrayByParity().sortArrayByParity(new int[]{3, 1, 2, 4}))); //2,4,3,1
    }
}
