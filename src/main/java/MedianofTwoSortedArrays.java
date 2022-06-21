//https://leetcode.com/problems/median-of-two-sorted-arrays/
public class MedianofTwoSortedArrays {

    private static boolean lenCheck(int[] arr) {
        return null != arr && arr.length ==0;
    }
    //O(log(min(m,n)) T
    // O(1) S
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (null == nums1 && null == nums2 || null == nums1 && lenCheck(nums2) || lenCheck(nums1) && null == nums2 || lenCheck(nums1) && lenCheck(nums2)) {
            return 0;
        }
        int m = (nums1 != null) ? nums1.length : 0;
        int n = (nums2 != null) ? nums2.length : 0;

        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int i = 0, j = 0, iMin = 0, iMax = m, half = (m + n + 1) / 2;
        int maxLeft = 0, minRight = 0;
        while (iMin <= iMax) {
            i = (iMin + iMax) / 2;
            j = half - i;

            if (i < m && nums1[i] < nums2[j - 1]) {
                iMin = i + 1;
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                break;
            }
        }
        if ((m + n) % 2 == 1) {
            return maxLeft;
        }

        if (i == m) {
            minRight = nums2[j];
        } else if (j == n) {
            minRight = nums1[i];
        } else {
            minRight = Math.min(nums1[i], nums2[j]);
        }

        return (double) (maxLeft + minRight) / 2;
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (null == nums1 && null == nums2 || (null != nums1 && nums1.length == 0 && null != nums2 && nums2.length ==0)) {
            return 0;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0) {
            return calcMedian(nums2, len2);
        } else if (len2 == 0) {
            return calcMedian(nums1, len1);
        } else {
            //mergedArr = new int[nums1.length + nums2.length];
            //first array should be the shortest one
            if (len1 < len2) {
                findMedian(nums1, nums2, len1, len2);
            } else {
                findMedian(nums2, nums1, len2, len1);
            }
        }

        return 0;
    }

    private static double findMedian(int[] firstArr, int[] secArr, int m, int n) {
        int minVal = 0, maxVal = m, half = (m + n + 1) / 2;

        while (minVal <= maxVal) {
            int firstArrMid = (minVal + maxVal) / 2;
            int secondArrMid = (m + n - firstArrMid);

            if (firstArrMid < maxVal && firstArr[firstArrMid] < secArr[secondArrMid - 1]) {
                minVal = firstArrMid + 1;
            } else if (firstArrMid > minVal && firstArr[firstArrMid] < secArr[secondArrMid - 1]) {
                maxVal = firstArrMid - 1;
            }
        }
        return 0;
    }

    private static double calcMedian(int[] arr, int len) {
        if (len % 2 == 1) {
            return arr[len / 2];
        } else {
            return (double) (arr[(len - 1) / 2] + arr[len / 2]) / 2;
        }
    }



    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(null, null));
        System.out.println(findMedianSortedArrays(new int[]{}, null));
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{}));

        System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{5,9,13,69}));
        System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, null));

        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{3}));

        System.out.println(findMedianSortedArrays(new int[]{23, 26, 31, 35}, new int[]{3, 5, 7, 9, 11, 16, 17}));

        System.out.println(findMedianSortedArrays(new int[]{1, 3, 8, 15, 16, 17}, new int[]{7,11,18,19,21,25}));
    }
}
