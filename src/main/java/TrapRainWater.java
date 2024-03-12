import java.util.Stack;

//https://leetcode.com/problems/trapping-rain-water/
//https://www.youtube.com/watch?v=ZI2z5pq0TqA&ab_channel=NeetCode
public class TrapRainWater {
    //O(N) TS
    public static int trapUsingArrays(int[] height) {
        if (null == height || height.length < 2) return 0;

        int leftMax = 0;
        int rightMax = 0;
        int len = height.length;
        int[] leftHeight = new int[len];
        int[] rightHeight = new int[len];
        for (int i=0, j=len-1; i< len; i++,j--) {
            leftHeight[i] = leftMax = Math.max(height[i], leftMax);
            rightHeight[j] = rightMax = Math.max(height[j], rightMax);
        }

        int total = 0;
        for (int i=0; i<len; i++) {
            total += Math.min(leftHeight[i],rightHeight[i]) - height[i];
        }

        return total;
    }

    //O(N) T
    //O(1) S
    public static int trap(int[] height) {
        if (null == height || height.length < 2) return 0;

        int total = 0;
        int leftMax = 0;
        int rightMax = height.length-1;
        int level = 0;
        while (leftMax < rightMax) {
            int lower = height[height[leftMax] < height[rightMax] ? leftMax++ : rightMax--];
            level = Math.max(level, lower);
            total += level - lower;
        }

        return total;
    }


    public static int trapMonoStack(int[] height) {
        int total = 0;
        Stack<Integer> stack = new Stack<>();
        for(int curr=0; curr<height.length; curr++) {
            while (!stack.isEmpty() && height[curr] > height[stack.peek()]) {
                int popIdx = stack.pop();

                //base case - no previous bar
                if (stack.isEmpty()) {
                    break;
                }

                //calculate bounded height
                //min(previous bar, current height) - popped height
                //boundedHeight is zero, when popped index height and previous bar height are same
                int boundedHeight = Math.min(height[stack.peek()], height[curr]) - height[popIdx];

                //calculate distance - distance between current and previous bars
                int distance = curr - stack.peek() - 1;

                total += boundedHeight * distance;
            }
            stack.add(curr);
        }

        return total;
    }

    public static void main(String[] args) {
        System.out.println(trapUsingArrays(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); //6
        System.out.println(trapUsingArrays(new int[]{4,6,8})); //0
        System.out.println(trapMonoStack(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); //6
        System.out.println(trapMonoStack(new int[]{0,1,0,2,1,0,0,1,3,2,1,2,1})); //8
        System.out.println(trapMonoStack(new int[]{4,6,8})); //0
    }
}
