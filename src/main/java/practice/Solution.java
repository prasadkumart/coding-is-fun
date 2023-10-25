package practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        ArrayList<Integer> result = new ArrayList();
        ArrayDeque<Integer> deque = new ArrayDeque();
        int l = 0, r = 0;

        while(r<nums.length) {
            //check queue's max element position with current
            while(!deque.isEmpty() && deque.getFirst() < l) {
                deque.removeFirst();
            }

            while(deque.size() > 0 && nums[deque.getLast().intValue()] < nums[r]) {
                deque.removeLast();
            }
            deque.add(r);

            if (r-l+1 == k) {
                result.add(nums[deque.getFirst()]);
                l++;
            }
            r++;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));//3,3,5,6,6,7
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(new int[]{1,-1}, 1))); //1,-1
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3)));//3,3,2,5
    }
}
