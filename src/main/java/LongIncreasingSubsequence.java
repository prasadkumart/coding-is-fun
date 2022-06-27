import java.util.Arrays;

//https://leetcode.com/problems/longest-increasing-subsequence/
public class LongIncreasingSubsequence {
    //O(N ^ 2)
    public static int lengthOfLISDP(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return 1;
        int[] dp = new int[len];
        //initialize with 1s
        Arrays.fill(dp, 1);
        int count = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //update the latest count
            count = Math.max(count, dp[i]);
        }

        return count;
    }

    //O(N lonN)
    public static int lengthOfLISBS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0; // len of sequence

        for (int num : nums) {
            int idx = Arrays.binarySearch(dp, 0, len, num);

            //if not found, return binarySearch return -ve insertionPosition
            if (idx < 0) {
                idx = -(idx + 1);
            }

            dp[idx] = num;

            //update len when insert position is at the end
            if (idx == len) {
                len++;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLISDP(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLISDP(new int[]{10}));


        System.out.println(lengthOfLISBS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLISBS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lengthOfLISBS(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }
}
