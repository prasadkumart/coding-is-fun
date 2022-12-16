package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PermuteArray {
    static ArrayList<ArrayList<Integer>> get_permutations(ArrayList<Integer> arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        HashMap<Integer, Integer> choices = new HashMap<>();
        for(Integer arrVal : arr) {
            choices.put(arrVal, choices.getOrDefault(arrVal, 0) + 1);
        }

        helper(choices, arr.size(), new ArrayList(), result);

        // Write your code here.
        return result;
    }

    static void helper(HashMap<Integer, Integer> choices, int len, ArrayList<Integer> slate, ArrayList<ArrayList<Integer>> result) {
        if (slate.size() == len) {
            result.add(new ArrayList<>(slate));
            return;
        }

        for(Map.Entry<Integer, Integer> entry : choices.entrySet()) {
            Integer key = entry.getKey();
            Integer val = entry.getValue();

            if (val == 0) {
                continue;
            }

            //add to slate
            slate.add(key);

            //reduce the value from map
            choices.put(key, choices.getOrDefault(key, 0) - 1);

            helper(choices, len, slate, result);

            //backtracking
            //remove from slate
            slate.remove(slate.size()-1);
            choices.put(key, choices.getOrDefault(key,0) + 1);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3}; // Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        ArrayList<Integer> numsList = IntStream.of(nums1)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(Arrays.toString(nums));
        System.out.println(get_permutations(numsList));
    }
}
