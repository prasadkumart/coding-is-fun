package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/letter-case-permutation/
public class LetterCasePermutations {
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        //helper(s, 0, "", result);
        //return result;

        helperMutableSlate(s, 0, s.toCharArray(), result);
        return result;
    }

    //dfs
    //TC: O(2^n)
    //SC: O(n^2)
    //Aux space = max depth X size of the stack - immutable Strings
    //          = O(n) X O(n)
    // Stack frame
    // s: pointer - O(1)
    // i: int - O(1)
    // slate: String - O(n)
    // result: pointer - O(1)
    static void helper(String s, int i, String slate, List<String> result) {
        // base case
        if (s.length() == i) {
            result.add(slate);
            return;
        }

        // recursion
        char c = s.charAt(i);
        if (Character.isAlphabetic(c)) {
            //slate + Character.toLowerCase(c) -> O(n)
            helper(s, i+1, slate + Character.toLowerCase(c), result); //O(n)
            helper(s, i+1, slate + Character.toUpperCase(c), result);
        } else {
            helper(s, i+1, slate + c, result);
        }
    }


    //dfs
    //TC: O(2^n)
    //SC: O(n)
    //Aux space = max depth X size of the stack
    //          = O(n) X O(1)
    // Stack frame
    // s: pointer - O(1)
    // i: int - O(1)
    // slate: pointer - O(1)
    // result: pointer - O(1)
    static void helperMutableSlate(String s, int i, char[] slate, List<String> result) {
        // base case
        if (i == s.length()) {
            result.add(new String(slate));
            return;
        }

        // recursion
        char c = s.charAt(i);
        if (Character.isAlphabetic(c)) {
            slate[i] = Character.toLowerCase(c);
            helperMutableSlate(s, i+1, slate, result); //O(n)
            slate[i] = Character.toUpperCase(c);
            helperMutableSlate(s, i+1, slate, result);
            slate[i] = c;
        } else {
            helperMutableSlate(s, i+1, slate, result);
        }
    }

    public static void main(String[] args) {
        String s = "a1b2"; //Output: ["a1b2","a1B2","A1b2","A1B2"]
        System.out.println(letterCasePermutation(s));

        s = "3z4"; // Output: ["3z4","3Z4"]
        System.out.println(letterCasePermutation(s));
    }
}
