import java.util.Arrays;

//https://leetcode.com/problems/longest-increasing-subsequence/
//https://www.youtube.com/watch?v=S9oUiVYEq7E&ab_channel=TusharRoy-CodingMadeSimple
public class LongIncreasingSubsequence {
    //TC: O(2^N)
    //SC: stack frame size O(N)
    public int LISBF(int[] nums) {
        //single number can be a subsequnce with single element
        int maxAns = 1;

        //find max subsequence at each position of the array
        for(int i=0; i<nums.length; i++) { //O(N)
            maxAns = Math.max(maxAns, findLIS(nums, i));
        }

        return maxAns;
    }

    //TS: O(N)
    int findLIS(int nums[], int curr) {
        //base case
        if (curr == 0) {
            return 1;
        }

        int maxElements = 1;
        for(int i=curr-1; i>=0; i--) {
            //when current element is greater than previous, which mean there is one more element can be added the LIS
            //so, add 1 ot the result of the sublist => 1 + findLIS(nums, i)
            if (nums[curr] > nums[i]) {
                maxElements = Math.max(maxElements, 1 + findLIS(nums, i));
            }
        }

        return maxElements;
    }

    //TC: O(N ^ 2)
    //SC: O(N) - DP array with size N
    public static int lengthOfLISDP(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return 1;
        int[] dp = new int[len];
        //initialize with 1s
        Arrays.fill(dp, 1);
        //int count = 0;
        //O(1+2+3+..+N)
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //update the latest count
            //count = Math.max(count, dp[i]);
        }

        return dp[len-1];
    }

    //O(N lonN)
    public static int lengthOfLISBS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0; // len of sequence

        for (int num : nums) {
            //if num not found in dp array, binarySearch returns -ve possible insertionPosition (not index)
            int idx = Arrays.binarySearch(dp, 0, len, num);

            if (idx < 0) {
                idx = -(idx + 1);
            }

            dp[idx] = num;

            //update len when insert position is at the end (last element)
            if (idx == len) {
                len++;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        System.out.println(new LongIncreasingSubsequence().LISBF(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));//4
        System.out.println(lengthOfLISDP(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); //4
        System.out.println(lengthOfLISDP(new int[]{10})); //1 - {10}
        System.out.println(lengthOfLISDP(new int[]{10,9})); //1 - {10} or {9}
        System.out.println(lengthOfLISDP(new int[]{10,9,11})); //2 - {9,11}


        System.out.println(lengthOfLISBS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); //4
        System.out.println(lengthOfLISBS(new int[]{0, 1, 0, 3, 2, 3})); //4
        System.out.println(lengthOfLISBS(new int[]{7, 7, 7, 7, 7, 7, 7})); //1
    }
}
