package sortnsearchrecusion;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-substring-without-repeating-characters
//Time complexity : O(2n)=O(n). In the worst case each character will be visited twice by i and j.
//Space complexity : O(min(m,n)). Same as the previous approach. We need O(k) space for the sliding window, where kkk is the size of the Set. The size of the Set is upper bounded by the size of the string nnn and the size of the charset/alphabet mmm.
//Given a substring with a fixed end index in the string, maintain a HashMap to record the frequency of each character in the current substring. If any character occurs more than once, drop the leftmost characters until there are no duplicate characters.
public class LongestSubStrWoutRepChars {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Map<Character, Integer> visitedChars = new HashMap<>();

        //O(L) : L - length of the string
        for(int i=0, j=0; j<s.length(); j++) {
            char ch = s.charAt(j);
            visitedChars.put(ch, visitedChars.getOrDefault(ch, 0)+1);

            //if counter is more than 1, key is repeated
            //below while loop sliding 'i' value,
            //HAS NO IMPACT ON # OF ITERATIONS/TC
            while (visitedChars.get(ch) > 1) {
                visitedChars.put(s.charAt(i), visitedChars.get(s.charAt(i))-1);
                i++;
            }

            maxLength = Math.max(maxLength, j - i + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        //System.out.println(new LongestSubStrWoutRepChars().lengthOfLongestSubstring("abcabcbb")); //3
        //System.out.println(new LongestSubStrWoutRepChars().lengthOfLongestSubstring("pwwkew")); //3
        System.out.println(new LongestSubStrWoutRepChars().lengthOfLongestSubstring("abccbcbb")); //3
    }
}
