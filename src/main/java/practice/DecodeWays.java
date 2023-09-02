package practice;

import java.util.Arrays;

//https://leetcode.com/problems/decode-ways/
public class DecodeWays {
    public int numDecodings(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }

        //DP
        return helperDP(s);

        //Memo
        //int[] memo = new int[s.length()];
        //Arrays.fill(memo, -1);
        //return helperMemo(s, 0, memo);

        // return helperBF(s, 0);
    }

    //RECURSION - Brute Force (forms a binary tree)
    //results need to be accumulated from both options (single digit decoding and double digit decoding)
    //TC: 2^n
    //SC: O(n) - n stack frames
    int helperBF(String s, int position) {
        //when no more chars left out in the string to validate, then its valid combination
        if (s.length() == position) {
            return 1;
        }

        //position in the string valid other than 0
        if (s.charAt(position) == '0') {
            return 0;
        }

        //always validate next position -> position + 1 (single digit decoding)
        int result = helperBF(s, position + 1);

        //position+2 needs to be validated if the combo is ib between 10 and 26 (double digit decoding)
        if (position<s.length()-1 && (s.charAt(position) == '1' || s.charAt(position) == '2' && s.charAt(position + 1) <= '6')) {
            result += helperBF(s, position + 2);
        }

        return result;
    }

    //MEMOIZATION - reducing overlapping sub-problems - helps pruning sub-problems
    //TC: O(n)
    //SC: O(n) - memo array
    int helperMemo(String s, int position, int[] memo) {
        //when no more chars left out in the string to validate, then its valid combination
        if (s.length() == position) {
            return 1;
        }

        //position in the string valid other than 0
        if (s.charAt(position) == '0') {
            return 0;
        }

        //has this position been processed
        if (memo[position] != -1) {
            return memo[position];
        }

        //always validate next position -> position + 1
        int result = helperMemo(s, position + 1, memo);

        //position+2 needs to be validated if the combo is ib between 10 and 26
        if (position<s.length()-1 && (s.charAt(position) == '1' || s.charAt(position) == '2' && s.charAt(position + 1) <= '6')) {
            result += helperMemo(s, position + 2, memo);
        }

        memo[position] = result;
        return result;
    }

    //DP array position matches to char position
    int helperDP(String s) {
        int[] dp = new int[s.length()+1];

        //when no chars we would have a null string, to get one choice
        //initialize DP[0] to 1, to pass the baton
        dp[0] = 1;

        //decode ways for the first digit it 1, if it is '1' otherwise '0'
        dp[1] = (s.charAt(0) == '0') ? 0 : 1;

        for (int i=2; i<dp.length; i++) {
            //if single-digit decode is possible
            if (s.charAt(i-1) != '0') {
                dp[i] = dp[i-1];
            }

            //if double-digit decode is possible
            if (s.charAt(i-2) == '1' || s.charAt(i-2) == '2' && s.charAt(i-1) <= '6') {
                dp[i] += dp[i-2];
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("12")); //2 --{A,B} or {L(12)}
        System.out.println(new DecodeWays().numDecodings("226")); //3 --{2,26},{22,6},{2,2,6}
    }
}
