//https://leetcode.com/problems/longest-palindromic-substring/solution/
public class StringToIntAtoI {
    public int myAtoi(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }

        int sign = 1, i=0, n=s.length();
        while(i<n && s.charAt(i) == ' ') {
            i++;
        }
        if (i>=n) {
            return 0;
        }

        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i++) == '+') ? 1 : -1;
        }

        long res = 0;
        while (i<n && Character.isDigit(s.charAt(i))) {
            res = res * 10 + (s.charAt(i++) - '0');
            //res = res * 10 + Integer.parseInt(""+s.charAt(i++));
            if (res * sign > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (res * sign < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }

        return (int) res*sign;
    }
    public static void main(String[] args) {
        System.out.println(new StringToIntAtoI().myAtoi("  -+125"));
        System.out.println(new StringToIntAtoI().myAtoi("  +125 345"));
        //System.out.println("123".charAt(1) - '0' + 10);

        //System.out.println(new LongestPalindromeSubstr().longestPalindrome("cbbd"));
    }
}
