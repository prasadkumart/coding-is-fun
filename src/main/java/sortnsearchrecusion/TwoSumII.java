package sortnsearchrecusion;

import java.util.HashMap;
import java.util.Map;


//https://leetcode.com/problems/two-sum/solution/
public class TwoSumII {

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

    //O(n) T
    //O(1) S
    public static int[] twoSum(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start<end) {
            int total = nums[start] + nums[end];
            if (total == target) {
                return new int[]{nums[start], nums[end]};
            } else if (total > target) {
                end--;
            } else if (total < target) {
                start++;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(result[0] + " " + result[1]);
    }
}
