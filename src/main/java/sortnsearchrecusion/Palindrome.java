package sortnsearchrecusion;

//https://leetcode.com/problems/valid-palindrome/
public class Palindrome {

    //TC: O(n)
    //SC: O(n) for a new string
    public boolean isPalindrome2Pointer(String s) {
        if (null == s || s.length() == 0) {
            return false;
        } else if (s.length() == 1) {
            return true;
        }

        int strLen = s.length();
        StringBuilder sb = new StringBuilder(); //SC: O(n)
        //remove non-alpha
        for(int i=0; i<strLen; i++) { //TC: O(n)
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }

        System.out.println("Original: " + s + " New String: " + sb.toString());
        //TWO POINTER approach
        String newString = sb.toString();
        int start = 0;
        int end = newString.length()-1;

        while (start<end) {
            if (newString.charAt(start) != newString.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    //TC: O(n)
    //SC: O(1) no additional auxiliary space
    public boolean isPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return false;
        } else if (s.length() == 1) {
            return true;
        }

        int start = 0;
        int end = s.length()-1;

        //exit when start is equal to end
        while (start<end) { //O(n)
            //NOT NESTED LOOP - removes non-alpha numeric chars from front
            while (start<end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            //NOT NESTED LOOP - removes non-alpha numeric chars from back
            while (start<end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }

            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Palindrome().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new Palindrome().isPalindrome(" "));
        System.out.println(new Palindrome().isPalindrome(" A 1 a"));
        System.out.println(new Palindrome().isPalindrome("race a car "));
    }
}
