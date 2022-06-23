public class TrapRainWater {

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

    public static void main(String[] args) {
        System.out.println(trapUsingArrays(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trapUsingArrays(new int[]{4,6,8}));
    }
}
