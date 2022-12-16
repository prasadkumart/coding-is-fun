package sortnsearchrecusion;//https://leetcode.com/problems/median-of-two-sorted-arrays/
//https://www.youtube.com/watch?v=LPFhl65R7ww&ab_channel=TusharRoy-CodingMadeSimple
//https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java

//Time complexity is O(log(min(m,n))
// m is length of the array1
// n is length of the array2
// Space complexity is O(1) - No extra space is required
public class MedianofTwoSortedArrays {

    public static double findMedianSortedArraysBS(int[] nums1, int[] nums2) {
//        if (null == nums1 && null == nums2 || null == nums1 && lenCheck(nums2) || lenCheck(nums1) && null == nums2 || lenCheck(nums1) && lenCheck(nums2)) {
//            return 0;
//        }

        if (nullCheck(nums1) && nullCheck(nums2)) {
            return 0;
        }
        //int arrayXLen = (nums1 != null) ? nums1.length : 0;
        //int arrayYLen = (nums2 != null) ? nums2.length : 0;

        int arrayXLen = 0;
        if (nums1 == null) {
            nums1 = new int[]{};
        } else {
            arrayXLen = nums1.length;
        }
        int arrayYLen = 0;
        if (nums2 == null) {
            nums2 = new int[]{};
        } else {
            arrayYLen = nums2.length;
        }

        //shorted array should be the first one, as binary search is implement on it to find the mid value
        if (arrayXLen > arrayYLen) {
            return findMedianSortedArraysBS(nums2, nums1);
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

    private static boolean nullCheck(int[] arr) {
        if (null == arr || arr.length == 0) {
            return true;
        }
        return false;
    }


    private static double calcMedian(int[] arr, int len) {
        if (len % 2 == 1) {
            return arr[len / 2];
        } else {
            return (double) (arr[(len - 1) / 2] + arr[len / 2]) / 2;
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nullCheck1(nums1) && nullCheck1(nums2)) {
            return 0;
        }

        int arrXLen = nums1.length;
        int arrYLen = nums2.length;

        if (arrXLen == 0) {
            return findMedian(nums2);
        } else if (arrYLen ==0) {
            return findMedian(nums1);
        }

        if (arrXLen > arrYLen) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int mid = (arrXLen + arrYLen +1)/2; //+1 is added to play well with even and odd no of elements

        int left = 0;
        int right = arrXLen;

        while(left <= right) {
            int partX = (left+right)/2;
            int partY = mid - partX;

            int x1 = (partX == 0) ? Integer.MIN_VALUE : nums1[partX-1];
            int x2 = (partX == arrXLen) ? Integer.MAX_VALUE : nums1[partX];

            int y1 = (partY == 0) ? Integer.MIN_VALUE : nums2[partY-1];
            int y2 = (partY == arrYLen) ? Integer.MAX_VALUE : nums2[partY];

            if (x1<=y2 && y1<=x2) {
                if ((arrXLen+arrYLen)%2 != 0) {
                    return (double) Math.max(x1, y1);
                } else {
                    return (double) (Math.max(x1,y1) + Math.min(x2,y2))/2;
                }
            } else if (x1 > y2) {
                right = partX - 1;
            } else {
                left = partX + 1;
            }
        }

        throw new IllegalArgumentException();

    }

    public static boolean nullCheck1(int[] arr) {
        if (null == arr || arr.length == 0) {
            return true;
        }
        return false;
    }

    public static double findMedian(int[] arr) {
        int size = arr.length;
        if (size%2 != 0) {
            return arr[size/2];
        } else {
            return (arr[size/2-1] + arr[size/2])/2.0;
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArraysBS(null, null));
        System.out.println(findMedianSortedArraysBS(new int[]{}, null));
        System.out.println(findMedianSortedArraysBS(new int[]{}, new int[]{}));

        //System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{5,9,13,69}));
        //System.out.println(findMedianSortedArraysBS(new int[]{1, 2, 3}, new int[]{}));

        //System.out.println(findMedianSortedArrays(new int[]{}, new int[]{3}));

        System.out.println(findMedianSortedArrays(new int[]{1,2,3,5,6}, new int[]{4})); //16.0
        //System.out.println(findMedianSortedArrays(new int[]{23, 26, 31, 35}, new int[]{3, 5, 7, 9, 11, 16, 17})); //16.0
        //System.out.println(findMedianSortedArraysBS(new int[]{23, 26, 31, 35}, new int[]{3, 5, 7, 9, 11, 16, 17})); //16.0

        //System.out.println(findMedianSortedArrays(new int[]{1, 3, 8, 15, 16, 17}, new int[]{7,11,18,19,21,25})); //15.5
        //System.out.println(findMedianSortedArraysBS(new int[]{1, 3, 8, 15, 16, 17}, new int[]{7,11,18,19,21,25})); //15.5

        //unsorted array
        //System.out.println(findMedianSortedArrays(new int[]{8,3,1,11,3}, new int[]{7,5,4,9})); //15.5
    }
}
