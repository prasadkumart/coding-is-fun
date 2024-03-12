package practice;


import java.util.Arrays;
import java.util.Stack;

//MONOTONIC STACK - O(n)
//https://itnext.io/monotonic-stack-identify-pattern-3da2d491a61e
public class NextGreatestNum {
    public int[] nextGreatestNum(int[] nums) {
        int LEN = nums.length;
        int[] result = new int[LEN];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<LEN; i++) {
            int counter = 0;
            while(!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                counter++;
                result[stack.peek()] = nums[i];
                stack.pop();
            }
            System.out.println("index: " + i + " counter: " + counter);
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NextGreatestNum().nextGreatestNum(new int[]{2,7,3,5,4,6,8})));//7,8,5,6,6,8,-1
        System.out.println(Arrays.toString(new NextGreatestNum().nextGreatestNum(new int[]{3,2,1})));//-1,-1,-1
        System.out.println(Arrays.toString(new NextGreatestNum().nextGreatestNum(new int[]{1,2,3})));//2,3,-1
    }
}
