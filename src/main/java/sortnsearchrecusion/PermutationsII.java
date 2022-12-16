package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/permutations-ii/
public class PermutationsII {

    //TC: O(N!)
    //Space Complexity: O(n)
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> choices = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            choices.put(nums[i], choices.getOrDefault(nums[i], 0) + 1);
        }
        helper(choices, nums.length, new ArrayList<>(), result);
        return result;
    }

    static void helper(Map<Integer, Integer> choices, int needed, List<Integer> slate, List<List<Integer>> result) {
        //base case
        if (needed == slate.size()) {
            result.add(new ArrayList<>(slate)); //deepcopy
            return;
        }

        //recursion
        for (Map.Entry<Integer, Integer> choice : choices.entrySet()) {
            Integer key = choice.getKey();
            Integer value = choice.getValue();

            //nothing to do when the key value is '0'
            if (value == 0) {
                continue;
            }

            slate.add(key);
            choices.put(key, choices.getOrDefault(key, 0) - 1);
            helper(choices, needed, slate, result);
            slate.remove(slate.size()-1);
            choices.put(key, choices.getOrDefault(key, 0) + 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2}; //Output: [[1,1,2],[1,2,1],[2,1,1]]
        System.out.println(permuteUnique(nums));

        int[] nums1 = {1,2,3}; // Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        System.out.println(permuteUnique(nums1));
    }
}
