package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/palindrome-partitioning/
public class PalindromePartitioning {
    int resultStart;
    int resultEnd;
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        backtrack(s, 0, new ArrayList<>(), result);

        return result;
    }

    static void backtrack(String s, int start, ArrayList<String> slate, List<List<String>> result) {
        //base case
        if (start >= s.length()) {
            result.add(new ArrayList<>(slate));
            return;
        }

        for(int i = start; i<s.length(); i++) {
            String subStr = s.substring(start, i+1);
            //System.out.println("substr " + subStr);
            if (isPalindrome(s, start, i)) {
                slate.add(subStr);
                backtrack(s, i+1, slate, result);
                slate.remove(slate.size()-1);
            }
        }
    }

    static ArrayList<String> generate_palindromic_decompositions(String s) {
        ArrayList<String> result = new ArrayList<>();

        helper(s, 0, "", result);
        return result;
    }

    static void helper(String s, int start, String slate, ArrayList<String> result) {
        //base case
        if (start >= s.length()) {
            slate = slate.substring(0, slate.lastIndexOf("|"));
            result.add(String.valueOf(slate.toString()));
            return;
        }

        for(int j=start; j<s.length(); j++) {
            String subStr = s.substring(start, j+1);
            if (isPalindrome(s, start, j)) {
                slate = slate + subStr + "|";
                helper(s, j+1, slate, result);
                //System.out.println(slate.lastIndexOf("|"));
                //System.out.println(slate.substring(0, slate.lastIndexOf("|")));
                //slate.deleteCharAt(slate.length()-1);
                slate = slate.substring(0, slate.lastIndexOf("|"));
                slate = slate.substring(0, slate.lastIndexOf("|")+1);
            }
        }
    }


    static boolean isPalindrome(String s, int left, int right) {
        while(left < right) {
            if (s.charAt(left) != s.charAt(right)) {
               return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        //System.out.println(isPalindrome("abba", 0, 3));
        System.out.println(new PalindromePartitioning().partition("aab"));
        //System.out.println(generate_palindromic_decompositions("abracadabra"));
    }
}
