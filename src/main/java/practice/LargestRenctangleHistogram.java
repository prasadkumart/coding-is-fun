package practice;

import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/
//https://www.youtube.com/watch?v=zx5Sw9130L0&ab_channel=NeetCode
//DETERMINE THE LEFT AND RIGHT BOUNDARIES FOR EACH HEIGHT VALUE
//when height is decreasing then the height can not be extended to right [2,1]; height 2 cant be extended
//when height is increasing then the height can be extended to right [1,2]; height 1 can be extended to next column
//when height is decreasing right after a higher column,
//      taller height can not be extended to left, calculate are for that single column, and pop from the consideration
//      then the lower height can be extended to left [1,2,1]; area = 3 X 1
//TC: O(n); n numbers are pushed and popped
//SC: O(n); stack size
public class LargestRenctangleHistogram {

    public int largestRectangleArea(int[] heights) {
        int largestArea = 0;
        //will use Pair class, to track possible left boundary and height
        Stack<Pair> stack = new Stack<>();
        for(int curr=0; curr<heights.length; curr++) {
            int leftBoundary = curr;
            //when current is shorter than stack top, then pop it
            while (!stack.isEmpty() && heights[curr] < stack.peek().getHeight()) {
                Pair poppedPair = stack.pop();
                int width = curr - poppedPair.index;
                int area = poppedPair.height * width;
                largestArea = Math.max(largestArea, area);
                leftBoundary = poppedPair.index;
            }

            // Add LEFT BOUNDARY and current height to the stack
            stack.add(new Pair(leftBoundary, heights[curr]));
        }

        //loop thru remaining values in the stack (for values in increasing order, stack will never get popped)
        //these elements can be extended TILL END OF THE STACK
        while(!stack.isEmpty()) {
            Pair poppedPair = stack.pop();
            int area = poppedPair.height * (heights.length - poppedPair.getIndex());
            largestArea = Math.max(largestArea, area);
        }

        return largestArea;
    }
    public int largestRectangleAreaBF(int[] heights) {
        int largestArea = 0;

        for(int i=0; i<heights.length; i++) {
            //first bar
            largestArea = Math.max(largestArea, heights[i]);
            int minHeight = heights[i];
            for(int j=i+1; j< heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                int area = minHeight * (j-i+1);
                largestArea = Math.max(largestArea, area);
            }
        }
        return largestArea;
    }

    public static void main(String[] args) {
        System.out.println(new LargestRenctangleHistogram().largestRectangleAreaBF(new int[]{2,1,5,6,2,3})); //10
        System.out.println(new LargestRenctangleHistogram().largestRectangleAreaBF(new int[]{2,4})); //4
        System.out.println(new LargestRenctangleHistogram().largestRectangleAreaBF(new int[]{5,1,1})); //5
        System.out.println(new LargestRenctangleHistogram().largestRectangleAreaBF(new int[]{1,1})); //2
        System.out.println(new LargestRenctangleHistogram().largestRectangleAreaBF(new int[]{0,9})); //9
        System.out.println(new LargestRenctangleHistogram().largestRectangleAreaBF(new int[]{0,0})); //0

        System.out.println(new LargestRenctangleHistogram().largestRectangleArea(new int[]{2,1,5,6,2,3})); //10
        System.out.println(new LargestRenctangleHistogram().largestRectangleArea(new int[]{2,4})); //4
        System.out.println(new LargestRenctangleHistogram().largestRectangleArea(new int[]{5,1,1})); //5
        System.out.println(new LargestRenctangleHistogram().largestRectangleArea(new int[]{1,1})); //2
        System.out.println(new LargestRenctangleHistogram().largestRectangleArea(new int[]{0,9})); //9
        System.out.println(new LargestRenctangleHistogram().largestRectangleArea(new int[]{0,0})); //0
    }

    class Pair {
        int index;
        int height;

        public Pair(int index, int height) {
            this.index = index;
            this.height = height;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
