import java.util.*;

//https://leetcode.com/problems/combination-sum/
//backtracking algo
//https://www.youtube.com/watch?v=vftmHwRVQW4&ab_channel=XavierElon
//O(N^(Target/M)) T
//N be the number of candidates, T be the target value, and M be the minimal value among the candidates
//Space Complexity: O(T/M)
public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (null == candidates) {
            return null;
        }

        List<List<Integer>> result = new ArrayList<>();

        findCombinations(candidates, 0, target, new ArrayList<>(), result);

        return result;
    }

    private static void findCombinations(int[] candidates, int index, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            //current list is a pointer, so add a copy to a new ArrayList
            result.add(new ArrayList<>(current));
            //result.add(current);
            return;
        }

        for (int i = index; i<candidates.length; i++) {
            if (candidates[i] <= target) {
                current.add(candidates[i]);
                findCombinations(candidates, i, target-candidates[i], current, result);

                //when target is < 0 (breaks the recursive call)
                //use Integer.valueOf to avoid remove by Index
                current.remove(Integer.valueOf(candidates[i]));
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = combinationSum(new int[]{2,3,6,7}, 7);

        System.out.println(Arrays.toString(result.toArray()));
    }
}
