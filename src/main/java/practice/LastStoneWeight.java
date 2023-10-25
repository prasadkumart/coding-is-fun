package practice;

import java.util.PriorityQueue;

//https://leetcode.com/problems/last-stone-weight/description/
public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        if (null == stones || stones.length == 0) {
            return 0;
        } else if (stones.length == 1) {
            return stones[0];
        }

        //PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1,n2) -> (n1-n2));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1,n2) -> (n2-n1)); //Insert: O(logN), remove: O(logN), peek O(1)

        for(int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() >= 2) {
            maxHeap.add(maxHeap.remove() - maxHeap.remove());
        }

        return maxHeap.remove();
    }

    public static void main(String[] args) {
        System.out.println(new LastStoneWeight().lastStoneWeight(new int[]{2,7,4,1,8,1})); //1
        System.out.println(new LastStoneWeight().lastStoneWeight(new int[]{31,26,33,21,40})); //1
        System.out.println(new LastStoneWeight().lastStoneWeight(new int[]{2,2,4,4})); //0
        System.out.println(new LastStoneWeight().lastStoneWeight(new int[]{1})); //1
    }
}
