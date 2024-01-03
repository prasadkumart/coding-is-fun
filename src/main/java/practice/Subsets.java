package practice;

import java.util.ArrayList;
import java.util.List;

//TC: uses binary tree (include and exclude for each number)
//O(2^N * N)
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), result);
        return result;
    }

    void helper(int[] nums, int i, List<Integer> slate, List<List<Integer>> result) {
        //base case -leaf node
        if (i == nums.length) {
            result.add(new ArrayList<>(slate)); // O(n) - add slate to power set
            return;
        }

        //INCLUDE & EXCLUDE
        //exclude
        helper(nums, i+1, slate, result);
        //include
        slate.add(nums[i]);
        helper(nums, i+1, slate, result);
        //pop from top element from slate
        slate.remove(slate.size()-1);
    }

    public static void main(String[] args) {
        System.out.println(new Subsets().subsets(new int[]{1,2,3}));
    }
}
