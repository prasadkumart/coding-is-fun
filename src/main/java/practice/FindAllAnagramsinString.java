package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
//no of windows for a substring - sLen-pLen+1
public class FindAllAnagramsinString {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }

        //TC: O((S-P+1) * n logN)
        /*for(int i=0; i<=s.length()-p.length(); i++) {
            String s1 = s.substring(i, i+p.length());

            if (validAnagramArr(s1, p)) {
                result.add(i);
            }
        }*/

        //TC: O((S-P+1) * n)
        /*for(int i=0; i<=s.length()-p.length(); i++) {
            String s1 = s.substring(i, i+p.length());

            if (validAnagram(s1, p)) {
                result.add(i);
            }
        }*/

        //SLIDING WINDOW //TC: O(S)
        int sLen = s.length();
        int pLen = p.length();
        int[] sFrequencyArr = new int[26];
        int[] pFrequencyArr = new int[26];
        //frequency counter for Stirng P
        for(char c : p.toCharArray()) { //O(P)
            pFrequencyArr[c-'a']++;
        }

        //no of windows for a substring - sLen-pLen+1
        for(int i=0; i<sLen; i++) { //O(S)
            //add current char to frequency array
            sFrequencyArr[s.charAt(i)-'a']++;

            //remove first char which is out of window
            if (i>=pLen) {
                sFrequencyArr[s.charAt(i - pLen)-'a']--;
            }

            //add starting position of the sub-string to the result
            //if (i>=pLen-1 && Arrays.equals(sFrequencyArr, pFrequencyArr)) { //O(1)
            if (Arrays.equals(sFrequencyArr, pFrequencyArr)) { //O(1)
                result.add(i-pLen+1);
            }
        }

        return result;
    }

    //TC: O(n log n)
    //SC: O(1)
    static boolean validAnagramArr(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    //using frequency counter
    //TC: O(n)
    //SC: O(1)
    static boolean validAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] freqCounter = new int[26];
        for(int i=0; i<s.length(); i++) {
            freqCounter[s.charAt(i)-'a']++;
            freqCounter[t.charAt(i)-'a']--;
        }
        for(int count : freqCounter) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(FindAllAnagramsinString.findAnagrams("cbaebabacd","abc")); //0,6
        System.out.println(FindAllAnagramsinString.findAnagrams("aabc","abc"));//1
        System.out.println(FindAllAnagramsinString.findAnagrams("abab","ab"));//[0,1,2]
    }
}
