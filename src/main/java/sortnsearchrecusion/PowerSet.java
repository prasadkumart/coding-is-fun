package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/subsets/
public class PowerSet {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        dfs(nums, 0, new ArrayList<Integer>(), result);

        return result;
    }

    //dfs
    //TC: O(2^n)
    //SC: O(n)
    //Aux space = max depth X size of the stack
    //          = O(n) X O(1)
    // Stack frame
    // s: pointer - O(1)
    // i: int - O(1)
    // slate: pointer - O(1)
    // result: pointer - O(1)
    static void dfs(int[] nums, int i, List<Integer> slate, List<List<Integer>> result) {
        //base case (leaf nodes)
        if (i == nums.length) {
            result.add(new ArrayList<>(slate)); //deepcopy
            return;
        }

        //recursion
        dfs(nums, i+1, slate, result); //exclude case
        //if (i>0 && nums[i-1] != nums[i]) {
            slate.add(nums[i]);
            dfs(nums, i + 1, slate, result);
            slate.remove(slate.size() - 1);
        //}
    }
    public static List<List<Integer>> get_subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        //for(int k=0; k<nums.length; k++) {
        backtrack(nums, 0, new ArrayList<Integer>(), result);
        //}

        return result;
    }
    static void backtrack(int[] nums, int start, List<Integer> slate, List<List<Integer>> result) {
        //if (slate.size() == nums.length) {
            result.add(new ArrayList<>(slate));
            //return;
        //}

        for(int i = start; i<nums.length; i++) {
            slate.add(nums[i]);
            backtrack(nums, i+1, slate, result);
            slate.remove(slate.size() - 1);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3}; //Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

        //System.out.println(subsets(nums));
        System.out.println(get_subsets(nums));
    }

}
