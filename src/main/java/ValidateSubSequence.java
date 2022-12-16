//https://leetcode.com/problems/is-subsequence/
public class ValidateSubSequence {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null || s.length() > t.length()) {
            return false;
        }
        if (s.length() == 0 || t.length() == 0) return true;
        boolean result = false;

        int j = 0;
        int sLenth = s.length();
        for(int i = 0; i < t.length(); i++) {
            if (j<sLenth && s.charAt(j) == t.charAt(i)) {
                j++;
            }
        }
        if (j == s.length()) {
            return true;
        }


        return result;
    }
    public static void main(String[] args) {
        System.out.println(new ValidateSubSequence().isSubsequence("abc","ahbgdc"));
        System.out.println(new ValidateSubSequence().isSubsequence("axc","ahbgdc"));
        System.out.println(new ValidateSubSequence().isSubsequence(null,"ahbgdc"));
        System.out.println(new ValidateSubSequence().isSubsequence("b","abc"));
    }
}
