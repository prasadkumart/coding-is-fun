package sortnsearchrecusion;

//https://leetcode.com/problems/longest-palindromic-substring/description/
//TC: O(n^2)
//SC: O(n)
public class LongestPalindrome {

    int resultStart = 0;
    int resultLen = 0;
    public String longestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return "";
        } else if (s.length() == 1) {
            return s;
        }

        int strLen = s.length();
        StringBuilder sb = new StringBuilder(); //SC: O(n)
        //remove non-alpha
        for(int i=0; i<strLen; i++) { //TC: O(n)
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }

        String newString = sb.toString();
        System.out.println("Original: " + s + " New String: " + newString);
        int maxLen = 0;

        //TC: O(n^2)
        for(int i=0; i<newString.length(); i++) { //TC: O(n)
            expandAroundCenter(newString, i, i); //TC: O(n)
            expandAroundCenter(newString, i, i+1);
        }
        return s.substring(resultStart, resultStart+resultLen);
    }

    void expandAroundCenter(String s, int begin, int end) {
        //check for boundary conditions
        // left should be greater than '0'
        // right should be less than the string length
        while(begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }

        int strLen = end-begin-1;
        if (strLen > resultLen) {
            //DO NOT USE begin++
            resultStart = begin+1;
            resultLen = strLen;
        }

        //System.out.println("resultStart: " + resultStart + " resultLen: " + resultLen);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("babad")); //bab or aba
        //System.out.println(new LongestPalindrome().longestPalindrome("cbbd")); //bb
        //System.out.println(new LongestPalindrome().longestPalindrome("aaaa")); //a
        //System.out.println(new LongestPalindrome().longestPalindrome("race a car "));
    }
}
