package practice;

import java.util.Arrays;

//https://leetcode.com/problems/sort-an-array/description/
//divide-conquer
public class MergeSort {
    public int[] sort(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
    //TC: O(N logN)
    //SC: O(n)
    public int[] mergeSort(int[] nums, int start, int end) {
        //leaf worker (base case)
        if (start == end) {
            return new int[]{nums[start]};
        }

        //internal node worker - sort sub-array //repeated for log N times
        int mid = start + (end-start)/2;
        int[] left = mergeSort(nums, start, mid);
        int[] right = mergeSort(nums, mid+1, end);

        //merge sub-arrays O(n)
        int i = 0;
        int j = 0;
        int[] temp = new int[end-start+1];
        int k = 0; //temp array index
        while(i<left.length && j<right.length) {
            if (left[i] < right[j]) {
                temp[k] = left[i];
                k++; i++;
            } else {
                temp[k] = right[j];
                k++; j++;
            }
        }

        //gather sub-arrays
        while(i<left.length) {
            temp[k] = left[i];
            k++; i++;
        }
        while(j<right.length) {
            temp[k] = right[j];
            k++; j++;
        }

        return temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MergeSort().sort(new int[]{5,2,6,4})));
    }
}
