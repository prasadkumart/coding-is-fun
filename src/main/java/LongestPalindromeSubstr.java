//https://leetcode.com/problems/longest-palindromic-substring/solution/
//https://www.youtube.com/watch?v=DK5OKKbF6GI&ab_channel=JavaBrains
//Time complexity : O(n^2). Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2)

//Space complexity : O(1)O(1).
public class LongestPalindromeSubstr {
    int resultStart;
    int resultLength;
    public String longestPalindrome(String s) {
        if (null == s || s.length() == 0) return "";
        int slen = s.length();
        if (slen < 2)  {
            return s;
        }

        for (int i=0; i<slen; i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i+1); //to cover when a string has even no of chars
        }
        return s.substring(resultStart, resultStart+resultLength);
    }

    private void expandAroundCenter(String s, int start, int end) {
        while(start>=0 && end<=s.length()-1 && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        int len = end-start-1; //-1, bcz of 0 index
        if (resultLength < len) {
            resultStart = start + 1; // bcz of start-- in the while loop
            resultLength = len;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromeSubstr().longestPalindrome("babad"));
        //System.out.println(new LongestPalindromeSubstr().longestPalindrome("cbbd"));
    }
}
