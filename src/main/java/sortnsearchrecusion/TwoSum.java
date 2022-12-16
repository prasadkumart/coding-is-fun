package sortnsearchrecusion;

import java.util.*;

//https://leetcode.com/problems/two-sum/
public class TwoSum {
    public static int[] twoSumUsingSet(int[] array, int targetSum) {
        if (null == array || array.length == 0) return new int[]{};

        if (array.length == 1) return new int[]{};


    //O(N^2)
//        for(int i=0; i<array.length; i++) {
//            for(int j=i+1; j<array.length;j++)
//                if (targetSum - array[i] == array[j]) return new int[]{array[i], array[j]};
//        }
//        return new int[]{};

        //O(N) TS
        Set<Integer> visitedSet = new HashSet<>();
        for(int num: array) {
            if (visitedSet.contains(targetSum-num)) {
                return new int[] {targetSum-num, num};
            } else {
                visitedSet.add(num);
            }
        }
        return new int[]{0};
    }

    //O(N) TS
    public static int[] twoSumUsingMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for(int i = 0; i < nums.length; i++) {
            int j = target - nums[i];
            if (map.containsKey(j) && map.get(j) != i) {
                return new int[]{i, map.get(j)};
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] result = twoSumUsingSet(new int[]{2, 7, 11, 15}, 9);
        Arrays.stream(result).forEach(System.out::print);
        System.out.println("\n");

        result = twoSumUsingSet(new int[]{3, 2, 3}, 6);
        Arrays.stream(result).forEach(System.out::print);
        System.out.println("\n");

        result = twoSumUsingSet(new int[]{3, 5, -4, 8, 11, 1, -1, 6}, 10);
        Arrays.stream(result).forEach(System.out::print);
        System.out.println("\n");

        result = twoSumUsingSet(new int[]{15}, 15);
        Arrays.stream(result).forEach(System.out::print);
    }
}
