package dp;

import java.util.*;

public class HowSum {
    public int[] howSum(int target, int[] nums) {
        int[] result = null;

        //return helper(target, nums, new ArrayList<>());
        return helperWithMemo(target, nums, new ArrayList<>(), new HashMap<>());
    }

    //Brute force using recursion
    //TC: O(T * N^T) - no of recursive calls N^T; worst case (when there is '1' in array) could have max of T elements in the result
    //SC: O(T)
    private int[] helper(int target, int[] nums, List<Integer> slate) {
        //base cases
        if (target == 0) {
            //worst case (when there is '1' in array) could have max of T elements in the result
            return slate.stream().mapToInt(Integer::intValue).toArray(); //O(T)
        }
        if (target < 0) {
            //remove top element
            return null;
        }

        //recursion will be repeated for the height of the target (T)
        //O(N^T)
        for(int num : nums) {
            int remainder = target - num;
            slate.add(num);
            int[] result = helper(remainder, nums, slate);
            if (null != result) {
                return result;
            }
            slate.remove(slate.size()-1);
        }

        return null;
    }


    //Using memoization
    //no of recursive calls are reduced to N*T from N^T
    //TC: O(N*T * T) -> O(N*T^2)
    //SC: O(T*T)
    private int[] helperWithMemo(int target, int[] nums, List<Integer> slate, Map<Integer, int[]> memo) {
        //base cases
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target == 0) {
            //worst case (when there is '1' in array) could have max of T elements in the result
            return slate.stream().mapToInt(Integer::intValue).toArray(); //O(T)
        }
        if (target < 0) {
            return null;
        }

        //recursion will be repeated for the height of the target (T)
        //O(N^T)
        for(int num : nums) {
            int remainder = target - num;
            slate.add(num);
            int[] result = helperWithMemo(remainder, nums, slate, memo);
            if (null != result) {
                memo.put(remainder, result);
                return result;
            }
            slate.remove(slate.size()-1);
        }

        memo.put(target, null);
        return null;
    }
    public static void main(String[] args) {
        //System.out.println(Arrays.toString(new HowSum().howSum(7, new int[]{2,3}))); //2,2,3
        //System.out.println(Arrays.toString(new HowSum().howSum(7, new int[]{5,3,4,7}))); //4,3
        //System.out.println(Arrays.toString(new HowSum().howSum(7, new int[]{2,4}))); //null
        System.out.println(Arrays.toString(new HowSum().howSum(300, new int[]{7,14}))); //null
    }

}
