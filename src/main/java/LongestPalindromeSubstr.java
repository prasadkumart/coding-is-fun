//https://leetcode.com/problems/longest-palindromic-substring/solution/
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
            expandAroundCenter(s, i, i+1); //to cover when a string has even no no chars
        }
        return s.substring(resultStart, resultStart+resultLength);
    }

    private void expandAroundCenter(String s, int start, int end) {
        while(start>=0 && end<=s.length()-1 && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        if (resultLength < end-start-1) {
            resultStart = start + 1;
            resultLength = end-start-1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromeSubstr().longestPalindrome("babad"));
        //System.out.println(new LongestPalindromeSubstr().longestPalindrome("cbbd"));
    }
}
