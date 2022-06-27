//https://leetcode.com/problems/longest-continuous-increasing-subsequence/
public class LongContIncSubsequence {
    public static int findLengthOfLCIS(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return 1;
        }

        int result = 0;
        int anchor = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i - 1] >= nums[i]) {
                anchor = i;
            }

            result = Math.max(result, i - anchor + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(findLengthOfLCIS(new int[]{1, 3, 5, 4, 7, 8, 10}));
        System.out.println(findLengthOfLCIS(new int[]{1, 1, 1}));
        System.out.println(findLengthOfLCIS(new int[]{1, 1, 2}));

    }
}
