package practice;

import java.util.*;

//https://leetcode.com/problems/sliding-window-maximum/
//but will not be repeated
//TC: O(N) N- length of the array
//SC: O(N)
//Total no of sliding windows: N-k+1
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>(); //nums.length-k+1

        //Deque would hold elements in decreasing order
        Deque<Integer> queue = new ArrayDeque<>();
        int left = 0;
        int right = 0;

        //loop thru all elements in array
        while(right < nums.length) { //O(N)
            //loop thru queue and remove existing that are less than current
            //will repeat k-1 times, when all numbers are in increasing order
            while(!queue.isEmpty() && nums[queue.getLast()] < nums[right]) {
                queue.removeLast(); //O(1)
            }

            //add element index to queue
            queue.add(right); //O(1)

            //remove element from front when it is out of the window
            if (left > queue.getFirst()) {
                queue.removeFirst(); //O(1)
            }

            //slide window
            if (right-left+1 == k) {
                result.add(nums[queue.peek()]); //O(1)
                left++;
            }

            //increment right pointer
            right++;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    int[] slidingWindow(int[] arr, int k) {
        ArrayList<Integer> result = new ArrayList<>();

        if (null != arr || arr.length > 0) {
            Deque<Integer> deque = new ArrayDeque<>();

            int left = 0;
            int right = 0;

            while(right < arr.length) {
                //keep removing lesser elements from queue than current
                while(!deque.isEmpty() && arr[right] > arr[deque.getLast()]) {
                    deque.removeLast();
                }

                deque.add(right);

                //out of window
                if (deque.getFirst() < left) {
                    deque.removeFirst();
                }

                //add max element to result for each window
                if (right-left+1 == k) {
                    result.add(deque.peek());
                    left++;
                }

                right++;
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }



























    public static void main(String[] args) {
        int[] result = new SlidingWindowMaximum().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(Arrays.toString(result)); //3,3,5,5,6,7

        result = new SlidingWindowMaximum().maxSlidingWindow(new int[]{1}, 1);
        System.out.println(Arrays.toString(result)); //1

        result = new SlidingWindowMaximum().maxSlidingWindow(new int[]{1,2,3,4,5,6,7}, 3);
        System.out.println(Arrays.toString(result)); //3,4,5,6,7

        int[] result1 = new SlidingWindowMaximum().maxSlidingWindow(new int[]{7,2,4}, 2);
        System.out.println(Arrays.toString(result1)); //7,4

        int[] result2 = new SlidingWindowMaximum().maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3);
        System.out.println(Arrays.toString(result2)); //3,3,2,5
    }
}
