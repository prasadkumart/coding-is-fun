package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

//MONOTONIC STACK
//https://leetcode.com/problems/next-greater-element-ii/
//TC: O(n) - elements pushed to stack once, and popped once
//SC: O(n) - stack
public class NextGreatestElementII {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<2*len; i++) {
            int ind = i%len;
            while(!stack.isEmpty() && nums[ind] > nums[stack.peek()]) {
                result[stack.pop()] = nums[ind];
                //stack.pop();
            }
            if (i<len) stack.add(i);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NextGreatestElementII().nextGreaterElements(new int[]{1,2,1})));//[2,-1,2]
        System.out.println(Arrays.toString(new NextGreatestElementII().nextGreaterElements(new int[]{1,2,3,4,3})));//[2,3,4,-1,4]
    }
}
