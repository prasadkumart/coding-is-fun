import java.util.PriorityQueue;

//https://leetcode.com/problems/find-median-from-data-stream/
//https://www.youtube.com/watch?v=JNKdfHmnMSg&ab_channel=EricProgramming
//O(longN) add
//O(1) to find
public class MedianFinder {
    PriorityQueue<Integer> maxHeap = null;
    PriorityQueue<Integer> minHeap = null;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b,a));
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        //if minHeap is zero
        //FIRST ADD TO minHeap*****
        if (minHeap.size() == 0) {
            minHeap.add(num);
            return;
        }

        if (maxHeap.size() == 0) {
            if (num < minHeap.peek()) {
                maxHeap.add(num);
            } else {
                maxHeap.add(minHeap.poll());
                minHeap.add(num);
            }

            return;
        }

        if (num < minHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        //rearrange heaps based on the size and values
        balance();
    }

    public double findMedian() {
        if (maxHeap.size() == 0 && minHeap.size() == 0) {
            return 0;
        }
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (maxHeap.size() < minHeap.size()) {
            return minHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    private void balance() {
        //left side had more element
        if (maxHeap.size() > minHeap.size()+1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        System.out.println("Median : " + obj.findMedian());
        obj.addNum(1);
        System.out.println("Median : " + obj.findMedian());
        obj.addNum(2);
        System.out.println("Median : " + obj.findMedian());
        obj.addNum(3);
        System.out.println("Median : " + obj.findMedian());

        obj = new MedianFinder();
        System.out.println("Median : " + obj.findMedian());
        obj.addNum(-1);
        System.out.println("Median : " + obj.findMedian());
        obj.addNum(-2);
        System.out.println("Median : " + obj.findMedian());
        obj.addNum(-3);
        System.out.println("Median : " + obj.findMedian());
        obj.addNum(-4);
        System.out.println("Median : " + obj.findMedian());
        obj.addNum(-5);
        System.out.println("Median : " + obj.findMedian());
    }
}
