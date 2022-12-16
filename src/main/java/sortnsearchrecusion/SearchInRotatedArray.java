package sortnsearchrecusion;

//https://leetcode.com/problems/search-in-rotated-sorted-array/
//https://www.youtube.com/watch?v=QdVrY3stDD4&ab_channel=NickWhite
//O(lonN) T
public class SearchInRotatedArray {
    public int search(int[] nums, int target) {
        if (null == nums || nums.length ==0) {
            return -1;
        }

        //find pivot point
        //left and right values should be same, when they meet at pivot point
        int left = 0;
        int right = nums.length-1;
        while(left < right) {
            int mid = left + (right-left)/2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int pivotPoint = left;
        left = 0;
        right = nums.length;

        if (target >= nums[pivotPoint] && target <= nums[nums.length-1]) {
            left = pivotPoint;
        } else {
            right = pivotPoint;
        }

        //BS
        while (left <= right) {
            int mid = left+(right-left)/2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        //System.out.println(new SearchInRotatedArray().search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(new SearchInRotatedArray().search(new int[]{3,1}, 3));
    }
}
