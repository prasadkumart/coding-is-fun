package sortnsearchrecusion;

import java.util.ArrayList;

//N length of digits; M letters length (max 4)
//time complexity would be O(M^N times N) - O(4^N . N)
//Space complexity: O(4^N) where N is the length of digits
// input - O(N)
// mappings - O(1)
// auxiliary space/slate - O(N)
// output/result - O(4^N), where N is the length of digits
public class WordsFromPhoneNo {
    static int length;
    static ArrayList<String> get_words_from_phone_number(String phone_number) {
        ArrayList<String> result = new ArrayList<>();
        length = phone_number.length();

        String[] mapping = {
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

        helper(mapping, phone_number, 0, "", result);

        // Write your code here.
        return result;
    }

    private static void helper(String[] mapping, String phone_number, int wordIndex, String slate, ArrayList<String> result) {
        //base
        if (slate.length() == length) {
            result.add(slate.toString());
            return;
        }

        String word = mapping[phone_number.charAt(wordIndex)-'0'];
        if (word.length() == 0) {
            length--;
            helper(mapping, phone_number, wordIndex+1, slate, result);
        }
        for(int i=0; i<word.length(); i++) {
            helper(mapping, phone_number, wordIndex+1, slate + word.charAt(i), result);
        }
    }

    public static void main(String[] args) {
        System.out.println(get_words_from_phone_number("1234567"));
    }
}
