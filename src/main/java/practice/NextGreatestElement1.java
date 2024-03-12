package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

//https://leetcode.com/problems/next-greater-element-i/description/
//https://www.youtube.com/watch?v=68a1Dc_qVq4&t=55s&ab_channel=NeetCode
//nums1 is a subset of nums2
//TC: O(length of nums2)
//SC: O(length of nums1) //hashMap and stack will have only elements related to num1 only
public class NextGreatestElement1 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);

        //O(nums1 length)
        //HashMap to look up nums1 values
        HashMap<Integer, Integer> nums1Map = new HashMap<>();
        for(int i=0; i<nums1.length; i++) {
            nums1Map.put(nums1[i], i);
        }

        //O(length of nums2)
        //Monotonic stack for nums2 array
        Stack<Integer> stack = new Stack<>();
        for(int j=0; j<nums2.length; j++) {
            //when stack top element is smaller than the current element
            while(!stack.isEmpty() && nums2[j]>nums2[stack.peek()]) {
                if (nums1Map.containsKey(nums2[stack.peek()])) {
                    result[nums1Map.get(nums2[stack.peek()])] = nums2[j];
                }
                stack.pop();
            }
            stack.add(j);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NextGreatestElement1().nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2}))); //[-1,3,-1]
        System.out.println(Arrays.toString(new NextGreatestElement1().nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4}))); //[3,-1]
    }
}
