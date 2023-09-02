package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
public class LetterCombination {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (null == digits || digits.length() == 0) {
            return result;
        }
        String[] dig2Letters = {
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };

        backtrack(digits, dig2Letters, 0, new StringBuilder(""), result);
        return result;
    }

    //TC: O(4^n * n) - n is the length of the digits
    //SC: O(n) - n stack frames
    private void backtrack(String digits, String[] dig2Letters, int wordIndex, StringBuilder slate, List<String> result) {
        //base case
        if (slate.length() == digits.length()) {
            result.add(new String(slate));
            return;
        }

        //pull the chars for a wordIndex
        //char at will return an ASCII value, so deduct ‘0’ to get the index value
        String word = dig2Letters[digits.charAt(wordIndex)-'0'];

        //loop thru all the chars in a word (for 2, char would be 'a','b','c')
        for(char c : word.toCharArray()) {
            //happy path (add new char to slate)
            backtrack(digits, dig2Letters, wordIndex+1, slate.append(c), result);

            //recursive call returns null, to move get the control to backtrack (remove last char) to move to next
            //back track by removing last char from the slate
            slate.deleteCharAt(slate.length()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new LetterCombination().letterCombinations("24"));
    }
}
