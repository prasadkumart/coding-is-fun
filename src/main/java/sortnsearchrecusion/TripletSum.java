package sortnsearchrecusion;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TC: O(n^3)
//SC: O(n^2)
//https://leetcode.com/problems/3sum/
public class TripletSum {

    public static ArrayList<String> threeSum(int[] nums) {
        ArrayList<String> result = new ArrayList<>();

        //sort array to avoid dups
        Arrays.sort(nums); //O(n log n)

        //since, 3 loops nested TC: O(n^2)
        for(int i=0; i<nums.length;i++) { //O(n)
            if (i>0 && nums[i-1] == nums[i]) {
                continue;
            } else {
                int start = i+1;
                int end = nums.length - 1;
                while (start < end) { //O(n)
                    int threeSum = nums[i] + nums[start] + nums[end];
                    if (threeSum < 0) {
                        start++;
                    } else if (threeSum > 0) {
                        end--;
                    } else {
                        result.add(new String(nums[i]+","+nums[start]+","+nums[end]));
                        start++;
                        while(start < end && nums[start] == nums[start-1]) { //this loop is shifting start and end values that are on one loop above
                            start++;
                        }
                    }
                }
            }
        }

        return result;
    }
//
//    static void twoSum(int[] nums, int current, int start, int target, List<List<Integer>> result) {
//        int end = nums.length - 1;
//        while (start < end) {
//            if (start-1 > current && nums[start-1] == nums[start]) {
//                start++;
//            } else if (nums[start] + nums[end] > target) {
//                end--;
//            } else if (nums[start] + nums[end] < target) {
//                start++;
//            } else {
//                result.add(Arrays.asList(nums[current], nums[start], nums[end]));
//                start++;
//                end--;
//            }
//        }
//    }

    static ArrayList<String> find_zero_sum_using_set(ArrayList<Integer> arr) {
        Integer[] nums = new Integer[arr.size()];
        arr.toArray(nums);

        Arrays.sort(nums);

        HashSet<String> result = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
//            if (i>0 && nums[i] == nums[i-1]) {
//                continue;
//            } else {
//                twoSum(nums, i, i+1, 0-nums[i], result);
//            }
            twoSumUsingSet(nums, i, i+1, 0-nums[i], result);
        }

        return new ArrayList<>(result);
    }

    static void twoSumUsingSet(Integer[] nums, int current, int start, int target, HashSet<String> result) {
        int end = nums.length - 1;
        while(start < end) {
            //if (start-1>current && nums[start] == nums[start-1]) {
            //    start++;
            //} else
            if (nums[start] + nums[end] > target) {
                end--;
            } else if (nums[start] + nums[end] < target) {
                start++;
            } else {
                System.out.println(new String(nums[current]+","+nums[start]+","+nums[end]));
                result.add(new String(nums[current]+","+nums[start]+","+nums[end]));
                start++;
                end--;
            }
        }
    }

    public static void main(String[] args) {
        //List<List<Integer>> result = threeSum(new int[]{-1,0,1,2,-1,-4}); //[[-1,-1,2],[-1,0,1]]
        //ArrayList<String> result = threeSum(new int[]{-2,0,0,2,2}); //[[-2,0,2]]
        //System.out.println(result);

        System.out.println(threeSum(new int[]{-381, -403, 342, 90, 13, -105, 303, 14, 73, -551, -156, 713, -603, -72, 342, -198, -196, 162, -69, -163, 874, 310, 213, -188, 59, 66, -829, 369, 6, 684, 351, 222, 660, 636, -200, 878, 634, 317, 176, -565, -203, 792, 685, 502, -599, 570, 127, 620, 11, -303, 281, 61, -386, -547, -697, -77, 159, 554, 447, -53, 393, -274, 548, 124, 305, 483, 296, 2, 277, 476, -187, 311, -106, -105, 649, -171, 195, 572, -211, 23, 687, -583, 250, 269, -706, 155, -153, 484, -90, 745, -635, 278, -56, 89, -300, -53, -77, -357, 525, -32, 219, 10, 63, -20, 316, -241, 194, 60, 73, -210, -400, 394, -864, -632, -863, -577, 147, -223, -252, -227, -344, 9, -631, 217, 308, 45, -215, 130, -86, 218, 205, -3, -658, -687, 365, -830, 509, 655, 472, -740, -829, -805, -136, 165, -236, 32, 843, 48, 445, -543, -900, 631, -231, 274, 153, -379, 405, 48, 867, -158, -178, 83, -597, 419, -306, -87, -8, 63, 740, 208, 843, -236, -462, 509, 124, 441, 430, -151, -213, -85, -389, -671, -208, -726, 78, 114, -218, 505, -382, -309, -390, 130, -371, -12, 68, 16, 594, -15, -713, -762, -58, -583, 218, -540, -738, -350, 668, 438, -260, -381, -553, -19, -627, -615, 275, 125, 22, 476, -738, -18, 100, 417, 363, 299, 577, 270, -230, 472, -500, 570, 258, 77, 102, 91, 404, 365, -571, 247, 568, -430, 158, 325, 383, 592, 44, 163, -31, -73, 92, 53, 308, -61, 314, -186, 795, 709, -190, -576, 477, -217, -544, -102, 114, 132, -22, -243, 749, -97, -630, 428, -350, -271, 125, 183, 492, 668, -496, 188, 691, -124, -34, -95, 22, -80, -12, 635, -146, 279, 79, 459, -68, -305, 730, -148, 151, 172, 794, -553, -471, 185, -38, -294, 176, -177, 232, -774, -90, -216, -353, 317, -398, 128, 12, 291, 253, 47, 363, 98, 407, 457, 505, -938, -448, 543, -448, 47, -303, 429, -810, 19, -639, -117, 120, 104, 2, 302, -135, 237, 324, -579, 260, -56, 493, -60, -33, 239, -424, 653, 440, 183, -388, -517, 222, -287, -199, -207, -421, -175, -353, 533, -440, 287, -161, -786, 802, -65, -750, -188, -684, -255, -770, -142, -299, -77, -370, -631, -105, -269, -241, 157, -73, 63, 197, 431, 497, 445, 621, 882, -134, 460, -647, 231, -114, -413, -127, -487, -392, 38, 821, 660, -619, -394, -459, -41, -645, -217, -430, -25, -485, 503, -12, 45, -260, -18, 82, -13, -223, 10, 575, -292, -786, 145, -432, 313, 304, -737, 391, -482, -429, -34, -220, 355, -421, 291, -104, -635, -203, 2, -357, 5, -597, -291, -676, 93, -243, -17, 61, -504, -419, 287, -436, 525, 790, 373, -21}));

        int[] nums = {-1,0,1,2,-1,-4};
        ArrayList<Integer> numsList = IntStream.of(nums)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        //System.out.println(find_zero_sum_using_set(numsList));
    }
}
