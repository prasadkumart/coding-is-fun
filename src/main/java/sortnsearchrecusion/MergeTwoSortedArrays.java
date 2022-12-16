package sortnsearchrecusion;

import java.util.Arrays;

public class MergeTwoSortedArrays {

    //Time: O(n)
    //space: O(n)
    static int[] mergeArray(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int i=0, j=0, k=0;
        while(i<nums1.length && j<nums2.length) {
            if (nums1[i]<nums2[j]) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }

        //gather remaining
        while(i<nums1.length) {
            result[k++] = nums1[i++];
        }

        while(j<nums2.length) {
            result[k++] = nums2[j++];
        }

        return result;
    }

    //merge with no auxiliary array
    //Time: O(n)
    //space: O(1) - no extra space
    static int[] mergeArray1(int[] nums1, int[] nums2, int n) {
        int i=n-1, j=n-1, k=2*n-1;
        while(i>=0 && j>=0) {
            if (nums1[i]>nums2[j]) {
                nums2[k--] = nums1[i--];
            } else {
                nums2[k--] = nums2[j--];
            }
        }

        //gather remaining
        while(i>=0) {
            nums2[k--] = nums1[i--];
        }
        //not needed
//        while(j>=0) {
//            nums2[k--] = nums2[j--];
//        }

        return nums2;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3,5};
        int[] nums2 = {2,4,6};

        //System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(mergeArray(nums1, nums2)));

        //when one of the arrays has the whole space needed for the resultant array
        int[] nums3 = {7,8,9};
        int[] nums4 = {1,2,3,0,0,0};
        int n = 3;

        //System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(mergeArray1(nums3, nums4, n)));
    }
}
