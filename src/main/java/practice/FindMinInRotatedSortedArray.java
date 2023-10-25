package practice;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
//TC: O(log n)
//SC: O(1)
public class FindMinInRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        //sorted list
        if (nums[0]<nums[nums.length-1]) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right-left)/2;
            //if mid > mid+1; then mid+1 is the smallest
            //3,4,5,1,2; mid is 5
            if (nums[mid] > nums[mid+1]) {
                return nums[mid+1];
            }
            //if mid-1 > mid; then mid is the smallest
            ////3,4,5,1,2; mid is 1
            if (nums[mid-1] > nums[mid]) {
                return nums[mid];
            }

            //search element is in right side of the mid
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else { //search element is in left side of the mid
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FindMinInRotatedSortedArray().findMin(new int[]{3,4,5,1,2})); //1
        System.out.println(new FindMinInRotatedSortedArray().findMin(new int[]{3,4,5,6,7,8,9,0,1,2}));//0
        System.out.println(new FindMinInRotatedSortedArray().findMin(new int[]{0,1,2}));//0
    }
}
