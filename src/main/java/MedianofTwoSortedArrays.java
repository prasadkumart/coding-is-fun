//https://leetcode.com/problems/median-of-two-sorted-arrays/
//https://www.youtube.com/watch?v=LPFhl65R7ww&ab_channel=TusharRoy-CodingMadeSimple
//https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java

//Time complexity is O(log(min(m,n))
// m is length of the array1
// n is length of the array2
// Space complexity is O(1) - No extra space is required
public class MedianofTwoSortedArrays {

    public static double findMedianSortedArraysBS(int[] nums1, int[] nums2) {
        if (null == nums1 && null == nums2 || null == nums1 && lenCheck(nums2) || lenCheck(nums1) && null == nums2 || lenCheck(nums1) && lenCheck(nums2)) {
            return 0;
        }
        int arrayXLen = (nums1 != null) ? nums1.length : 0;
        int arrayYLen = (nums2 != null) ? nums2.length : 0;

        //shorted array should be the first one, as binary search is implement on it to find the mid value
        if (arrayXLen > arrayYLen) {
            findMedianSortedArraysBS(nums2, nums1);
        }

        int mid = (arrayXLen + arrayYLen + 1)/2; //+1 is added to play well with even and odd no of elements
        int low = 0;
        int high = arrayXLen; // no of ways first array can be partitioned = no of elements in the array
        while (low <= high) {
            int partitionX = (low + high)/2;
            int partitionY = mid - partitionX;

            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX-1];
            int minRightX = (partitionX == arrayXLen) ? Integer.MAX_VALUE : nums1[partitionX]; //no -1 as partitionX will have a position (not index val)

            int maxLeftY = (partitionY ==0) ? Integer.MIN_VALUE : nums2[partitionY-1];
            int minRightY = (partitionY == arrayYLen) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if ((arrayXLen+arrayYLen) % 2 ==0) {
                    return (double) (Math.max(maxLeftX,maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                high = partitionX - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException();
    }

    private static boolean lenCheck(int[] arr) {
        return null != arr && arr.length ==0;
    }
    //O(log(min(m,n)) T
    // m is the shortest length array
    // n is the the lenght of the 2nd array
    // O(1) S
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (null == nums1 && null == nums2 || null == nums1 && lenCheck(nums2) || lenCheck(nums1) && null == nums2 || lenCheck(nums1) && lenCheck(nums2)) {
            return 0;
        }
        int m = (nums1 != null) ? nums1.length : 0;
        int n = (nums2 != null) ? nums2.length : 0;

        //if num2 has lesser elements than num1
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        //iMax (no of ways first array can be partitioned = no of elements in the array)
        int i = 0, j = 0, iMin = 0, iMax = m, half = (m + n + 1) / 2; //+1 is needed to play well with even and odd no of elements
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
        System.out.println(findMedianSortedArraysBS(null, null));
        System.out.println(findMedianSortedArraysBS(new int[]{}, null));
        System.out.println(findMedianSortedArraysBS(new int[]{}, new int[]{}));

        //System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{5,9,13,69}));
        //System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, null));

        //System.out.println(findMedianSortedArrays(new int[]{}, new int[]{3}));

        System.out.println(findMedianSortedArrays(new int[]{23, 26, 31, 35}, new int[]{3, 5, 7, 9, 11, 16, 17})); //16.0
        System.out.println(findMedianSortedArraysBS(new int[]{23, 26, 31, 35}, new int[]{3, 5, 7, 9, 11, 16, 17})); //16.0

        System.out.println(findMedianSortedArrays(new int[]{1, 3, 8, 15, 16, 17}, new int[]{7,11,18,19,21,25})); //15.5
        System.out.println(findMedianSortedArraysBS(new int[]{1, 3, 8, 15, 16, 17}, new int[]{7,11,18,19,21,25})); //15.5

        //unsorted array
        System.out.println(findMedianSortedArrays(new int[]{8,3,1,11,3}, new int[]{7,5,4,9})); //15.5
    }
}
