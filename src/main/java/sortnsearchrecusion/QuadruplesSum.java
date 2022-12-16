package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TC: O(n^3)
//SC: O(???) - ????
// https://leetcode.com/problems/4sum/
public class QuadruplesSum {

    public static ArrayList<ArrayList<Integer>> four_sum(ArrayList<Integer> arr, Integer target) {
        HashSet<ArrayList<Integer>> result = new HashSet<>();

        Integer[] nums = new Integer[arr.size()];
        arr.toArray(nums);

        //sort array to avoid dups
        Arrays.sort(nums); //O(n log n)

        kSum(nums, 4, 0, target, new ArrayList<>(), result);

        return new ArrayList<>(result);
    }

    //T: O(n^3)
    static void kSum(Integer[] nums, int k, int start, int target, ArrayList<Integer> quadList, HashSet<ArrayList<Integer>> result) {
        //when level is more than 2
        if (k != 2) {
            for (int i=start; i < nums.length; i++) { // stop till before k pairs
                if (i>start && nums[i] == nums[i-1]) {
                    continue;
                }

                if (i == start || nums[i] != nums[i-1]) {
                    quadList.add(nums[i]);
                    //System.out.println(k + "loop " + quadList);
                    kSum(nums, k-1, i+1, target-nums[i], quadList, result);
                    quadList.remove(quadList.size()-1);
                }
            }
        } else {
            //two sum - O(n^2)
            int end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] > target) {
                    end--;
                } else if (nums[start] + nums[end] < target) {
                    start++;
                } else {
                    quadList.add(nums[start]);
                    quadList.add(nums[end]);
                    //System.out.println("Sum2 " + quadList);
                    result.add(new ArrayList<>(quadList));
                    quadList.remove(quadList.size() - 1);
                    quadList.remove(quadList.size() - 1);
                    start++;
                    while (start < end && nums[start] == nums[start - 1]) {
                        start++;
                    }
//                    end--;
//                    while (start < end && nums[end] == nums[end - 1]) {
//                        end--;
//                    }
                }
            }
            return;
        }

    }

    public static void main(String[] args) {
        //int[] nums = {0, 0, 1, 3, 2, -1}; //[-1, 0, 1, 3], [0, 0, 1, 2]
        //int target = 3;
        //int[] nums = {2, 1, 6, 3, 1, 3, 5, 4, 4, 5, 6, 2};
        //int target = 11; //[1, 1, 3, 6],[1, 1, 4, 5],[1, 2, 2, 6],[1, 2, 3, 5],[1, 2, 4, 4],[1, 3, 3, 4],[2, 2, 3, 4]
        //int[] nums = {0, 0, 1, 3, 2, -1};
        //int target = 3; //[[-1, 0, 1, 3], [0, 0, 1, 2]]
        //int[] nums = {251, 251, 251, 251, 251, 251, 251}; //[[251, 251, 251, 251]]
        //int target = 1004;
        int[] nums = {1, -1, 5, 4, 6, 5, 2, -2, 7, 3}; //[[251, 251, 251, 251]]
        int target = 10;
        ArrayList<Integer> numsList = IntStream.of(nums)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(four_sum(numsList, target));
    }
}
