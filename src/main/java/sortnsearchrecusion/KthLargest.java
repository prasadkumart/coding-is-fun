package sortnsearchrecusion;

import java.util.PriorityQueue;

//https://leetcode.com/problems/kth-largest-element-in-an-array/
//https://www.youtube.com/watch?v=FrWq2rznPLQ&ab_channel=KevinNaughtonJr.
//The time complexity of adding an element in a heap of size k is O(logk),
// and we do it N times that means O(Nlogk) time complexity for the algorithm.
//Space complexity : O(k) to store the heap elements.
public class KthLargest {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) { //O(N)
            minHeap.add(num); //O(logN)

            if (minHeap.size()>k) {
                minHeap.remove();
            }
        }
        System.out.println(minHeap);
        return minHeap.remove();
    }
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }
}
