package practice;

//https://leetcode.com/problems/container-with-most-water/description/
public class ContainerMostWater {
    public int maxArea(int[] height) {
        return maxArea2Pointer(height);
    }

    //first element have n-1, next n-2, so on...
    //N-1 * n-2 * n-3...1
    //TC: O(n^2)
    //SC: O(1)
    int maxAreaBF(int[] height) {
        int max = Integer.MIN_VALUE;
        for(int left=0; left<height.length-1; left++) {
            for(int right=left+1; right<height.length; right++) {
                int area = Math.min(height[left], height[right]) * (right-left);
                max = Math.max(area, max);
            }
        }

        return max;
    }

    //TC: O(n)
    //sc: O(1)
    int maxArea2Pointer(int[] height) {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length-1;

        while (left<right) {
            int area = Math.min(height[left], height[right]) * (right-left);
            max = Math.max(area, max);

            if (height[left]<height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new ContainerMostWater().maxArea(new int[]{1,8,6,2,5,4,8,3,7})); //49
        System.out.println(new ContainerMostWater().maxArea(new int[]{1,1})); //1
    }
}
