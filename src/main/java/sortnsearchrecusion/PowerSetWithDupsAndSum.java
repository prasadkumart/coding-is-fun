package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//TC: O(N * 2^N) N is length of the array
// no. unique combinations: 2^N
// push the combination into the results - O(N) at most

//SC: O(N * 2^N)
//Input: O(N)
//Auxiliary space: O(N) for the slate
//output: O(N * 2^N)
public class PowerSetWithDupsAndSum {
    static ArrayList<ArrayList<Integer>> generate_all_combinations(ArrayList<Integer> arr, Integer target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Integer[] nums = new Integer[arr.size()]; // O(N) TS
        arr.toArray(nums); //O(N) TS
        Arrays.sort(nums); //O(N lonN) T
        helper(nums, target, 0, new ArrayList<>(), result);

        return result;
    }

    static void helper(Integer[] nums, Integer target, int start, ArrayList<Integer> slate, ArrayList<ArrayList<Integer>> result) {
        //base
        if (target == 0) {
            result.add(new ArrayList<>(slate));
        }
        for(int i=start; i<nums.length; i++) {
            if (i != start && nums[i-1] == nums[i]) {
                continue;
            }
            slate.add(nums[i]);
            helper(nums, target-nums[i], i+1, slate, result);
            slate.remove(slate.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        ArrayList<Integer> numsList = IntStream.of(nums)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(generate_all_combinations(numsList, 3));

        int[] nums1 = {1,1,1};
        ArrayList<Integer> numsList1 = IntStream.of(nums1)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(generate_all_combinations(numsList1, 2));
    }
}
