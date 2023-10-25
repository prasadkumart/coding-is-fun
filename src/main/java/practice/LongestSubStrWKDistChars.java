package practice;

import java.util.HashMap;

//SLIDING WINDOW
// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/

//TC: O(n) - linear
//SC: O(n) - hash map
public class LongestSubStrWKDistChars {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left=0, right=0, maxLength = 0;

        if (k == 0) {
            return k;
        }
        if (k >= s.length()) {
            return s.length();
        }

        HashMap<Character, Integer> map = new HashMap<>();
        while(right < s.length()) {
            //add char frequency to map
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)+1);

            while(map.size()>k) {
                map.put(s.charAt(left), map.get(s.charAt(left))-1);
                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }
                left++;
            }

            maxLength = Math.max(maxLength, right-left+1);
            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubStrWKDistChars().lengthOfLongestSubstringKDistinct("eceba", 2)); //3 "ece"
        System.out.println(new LongestSubStrWKDistChars().lengthOfLongestSubstringKDistinct("eceba", 1)); //1 "ece"
        System.out.println(new LongestSubStrWKDistChars().lengthOfLongestSubstringKDistinct("aaaaaa", 1)); //6 "aaaaaa"
        System.out.println(new LongestSubStrWKDistChars().lengthOfLongestSubstringKDistinct("a", 0)); //0 "aaaaaa"
    }
}
