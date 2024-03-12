package practice;

//https://leetcode.com/problems/valid-anagram/description/
//TC: O(n)
//SC: O(1)
public class ValidAnagrams {
    public boolean isAnagram(String s, String t) {
        int sLen = (s==null || s.length()==0) ? 0 : s.length();
        int tLen = (t==null || t.length()==0) ? 0 : t.length();

        if (sLen != tLen) {
            return false;
        }

        //USING COUNTER
        /*
        //constant array size, so same space used for all strings, SC: O(1)
        int[] frequencyCounter = new int[26];

        for(int i=0; i<sLen; i++) {
            frequencyCounter[s.charAt(i)-'a']++;
            frequencyCounter[t.charAt(i)-'a']--;
        }

        //verify char array for non-zero
        for(int i : frequencyCounter) {
            if (i>0) {
                return false;
            }
        }
        return true;
        */

        //USING HASH-KEY (CHAR COUNT DELIMITER WITH #)
        //aabcc(#3#1#2)
        //acdd(#1#0#1#2)
        int[] frequencyCounter = new int[26];

        for(int i=0; i<sLen; i++) {
            frequencyCounter[s.charAt(i)-'a']++;
        }

        StringBuilder sb1 = new StringBuilder();
        for(int i=0; i<26; i++) {
            sb1.append("#");
            sb1.append(frequencyCounter[i]);
        }

        frequencyCounter = new int[26];
        for(int i=0; i<tLen; i++) {
            frequencyCounter[t.charAt(i)-'a']++;
        }

        StringBuilder sb2 = new StringBuilder();
        for(int i=0; i<26; i++) {
            sb2.append("#");
            sb2.append(frequencyCounter[i]);
        }

        return sb1.toString().equals(sb2.toString());
    }

    public static void main(String[] args) {
        System.out.println(new ValidAnagrams().isAnagram("anagram", "nagaram")); //true
        System.out.println(new ValidAnagrams().isAnagram("rat", "cat")); //false
        System.out.println(new ValidAnagrams().isAnagram("rat", "rats")); //false
    }
}
