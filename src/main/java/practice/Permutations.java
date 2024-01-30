package practice;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();

        //helperDups(nums, new ArrayList<>(), result);
        helperUnique(nums, visited, new ArrayList<>(), result);
        return result;
    }

    //TC: n^n (each position will have n choices as dups allowed)
    void helperDups(int[] nums, List<Integer> slate, List<List<Integer>> result) {
        //base case
        if (slate.size() == nums.length) {
            result.add(new ArrayList<>(slate));
            return;
        }

        //recursion
        for(int num : nums) {
            slate.add(num);
            helperDups(nums, slate, result);

            //backtrack
            slate.remove(slate.size()-1);
        }
    }

    void helperUnique(int[] nums, boolean[] visited, List<Integer> slate, List<List<Integer>> result) {
        //base case
        if (slate.size() == nums.length) {
            result.add(new ArrayList<>(slate));
            return;
        }

        //recursion
        for(int i=0; i<nums.length; i++) {
            //skip if already visited
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            slate.add(nums[i]);
            helperUnique(nums, visited, slate, result);

            //backtrack
            visited[i] = false;
            slate.remove(slate.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Permutations().permute(new int[]{1,2,3}));
        System.out.println(new Permutations().permute(new int[]{1,2,3,4}));
    }

}
