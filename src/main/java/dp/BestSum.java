package dp;

import java.util.*;

public class BestSum {
    public int[] bestSum(int target, int[] nums) {
        int[] result = null;

        //return helper(target, nums, new ArrayList<>(), null);
        return helperWithMemo(target, nums, new ArrayList<>(), new HashMap<>(), null);
    }

    //Brute force using recursion
    //TC: O(N^T) * O(N) * O(T) // N: no of array elements; T: Target Sum
    // each element in a tree will have to upto T height with N leaves - O(N^T)
    // have to check best sum each array element - O(N)
    // grabbing elements from the slate - O(T)
    //SC: Stack + temp array size - O(T*T)
    //stack frame size could go up to T
    //each stack would a slate upto T size
    private int[] helper(int target, int[] nums, List<Integer> slate, int[] result) {
        //base cases
        if (target == 0) {
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
            int[] tempResult = helper(remainder, nums, slate, result);
            if (null == result || null != tempResult && tempResult.length < result.length) {
                result = tempResult;
            }
            slate.remove(slate.size()-1);
        }

        return result;
    }



    //memo would have T different objects, which would be height of the tree
    //TC: O(N*T) * O(T)  // N: no of array elements; T: Target Sum
    // each element in a tree will have to upto T height with N leaves with memo - O(N*T)
    // grabbing elements from the list - O(N)
    //SC: Stack + temp array size - O(T*T)
    //stack frame size could go up to T
    //each stack would a slate upto T size
    private int[] helperWithMemo(int target, int[] nums, List<Integer> slate, Map<Integer, int[]> memo, int[] result) {
        //base cases
        if (memo.containsKey(target)) { //O(1)
            return memo.get(target);
        }
        if (target == 0) {
            //grabbing elements from the slate - O(T)
            return slate.stream().mapToInt(Integer::intValue).toArray(); //O(T)
        }
        if (target < 0) {
            //remove top element
            return null;
        }

        //no of recursion calls with memo is N*T - l be repeated for the height of the target (T)
        //O(N*T)
        for(int num : nums) {
            int remainder = target - num;
            slate.add(num);
            int[] tempResult = helperWithMemo(remainder, nums, slate, memo, result);
            if (result == null || null != tempResult && tempResult.length < result.length) {
                result = tempResult;
            }
            slate.remove(slate.size()-1);
        }

        memo.put(target, result);
        return result;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BestSum().bestSum(8, new int[]{2,3,5}))); //3,5
        System.out.println(Arrays.toString(new BestSum().bestSum(7, new int[]{5,3,7,4,9}))); //7
        System.out.println(Arrays.toString(new BestSum().bestSum(7, new int[]{2,4}))); //null
        System.out.println(Arrays.toString(new BestSum().bestSum(300, new int[]{7,14}))); //null
    }

}
