package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/subsets-ii/description/
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        helper(nums, 0, new HashSet<>(), new ArrayList<>(), result);

        return result;
    }

    void helper(int[] nums, int i, Set<Integer> visitedSet,List<Integer> slate, List<List<Integer>> result) {
        //base case
        if (i == nums.length) {
            result.add(new ArrayList<>(slate));
            return;
        }

        visitedSet.add(nums[i]);
        while(i<nums.length && visitedSet.contains(nums[i])) {
            i++;
        }

        //exclude
        helper(nums, i+1, visitedSet, slate, result);

        //include
        slate.add(nums[i]);
        helper(nums, i+1, visitedSet, slate, result);

        //pop the slate
        slate.remove(slate.size()-1);
    }

    public static void main(String[] args) {
        System.out.println(new SubsetsII().subsetsWithDup(new int[]{1,1,2}));
    }
}
