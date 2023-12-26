package practice;

//https://leetcode.com/problems/search-in-rotated-sorted-array/
//TC: O(log n)
public class SearchInRotatedArray {
    public int search(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }

        //smallest element index
        int pivot = 0;
        int left=0, right=nums.length-1;

        //sorted order
        if (nums[left] <= nums[right]) {
            pivot = left;
        } else {
            while(left <= right) {
                int mid = left + (right-left)/2;

                //4,5,1,2,3
                //mid: 5, pivot: 1
                if (nums[mid] > nums[mid+1]) {
                    pivot = mid+1;
                    break;
                }

                //4,5,1,2,3
                //mid: 1, pivot: 1
                if (nums[mid-1] > nums[mid]) {
                    pivot = mid;
                    break;
                }

                //if right is smaller than mid, search window is right side
                if (nums[right] < nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        //reset left and right
        left=0;
        right=nums.length-1;
        if (target >= nums[pivot] && target <= nums[right]) { //search window on right
            return binarySearch(nums, pivot, right, target);
        } else { //search window on left
            return binarySearch(nums, left, pivot, target);
        }
    }

    //TC: O(log n)
    private int binarySearch(int[] nums, int left, int right, int target) {
        while(left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target > nums[mid]) { //right window
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchInRotatedArray().search(new int[]{5,0,1,2,3,4}, 0)); //1
        System.out.println(new SearchInRotatedArray().search(new int[]{4,5,6,7,0,1,2}, 0)); //4
        System.out.println(new SearchInRotatedArray().search(new int[]{1,3}, 3)); //1
        System.out.println(new SearchInRotatedArray().search(new int[]{1}, 0)); //1
        System.out.println(new SearchInRotatedArray().search(new int[]{4,5,6,7,0,1,2}, 0)); //4
    }
}
