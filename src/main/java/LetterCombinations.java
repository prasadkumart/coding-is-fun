import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/letter-combinations-of-a-phone-number/
//https://www.youtube.com/watch?v=21OuwqIC56E&ab_channel=KevinNaughtonJr.
//N length of digits; M letters length (max 4)
//time complexity would be O(M^N times N) - O(4^N . N)
//Space complexity: O(N), where N is the length of digits
public class LetterCombinations {
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = {
                "0",
                "1",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "prgs",
                "tuv",
                "wxyz"
        };

        findCombinations(mapping, digits, 0, "", result);

        return result;
    }

    private static void findCombinations(String[] mapping, String digits, int index, String current, List<String> result) {
        if (index == digits.length()) {
            result.add(current);
            return;
        }

        String letters = mapping[digits.charAt(index)-'0'];
        for (int i=0; i<letters.length(); i++) {
            findCombinations(mapping, digits, index + 1, current + letters.charAt(i), result);
        }
    }

    public static void main(String[] args) {
        List<String> result = letterCombinations("1234567");

        System.out.println(Arrays.toString(result.toArray()));
    }
}
