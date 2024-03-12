package practice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> grpAnagrams = new HashMap<>();
        if (strs == null || strs.length == 0) {
            return result;
        }

        if (strs.length == 1) {
            result.add(Arrays.asList(strs));
        }

        //O(n. LlogL)
        for(String str: strs) { //O(n)
            char[] charArray = str.toCharArray(); //:(L)
            Arrays.sort(charArray); //(O(LlogL)
            String sorttedStr = new String(charArray);

            if (!grpAnagrams.containsKey(sorttedStr)) {
                grpAnagrams.put(sorttedStr, new ArrayList<>());
            }
            grpAnagrams.get(sorttedStr).add(str);
        }

        //USING HASH-KEY (CHAR COUNT DELIMITER WITH #)
        //aabcc(#3#1#2)
        //acdd(#1#0#1#2)
        //TC: O(n * L)
        for(String str: strs) { //O(n)
            int[] freqCounter = new int[26];
            for(char c: str.toCharArray()) { //O(L)
                freqCounter[c-'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<26; i++) { //O(1)
                sb.append("#");
                sb.append(freqCounter[i]);
            }

            String hashKey = sb.toString();
            if (!grpAnagrams.containsKey(hashKey)) {
                grpAnagrams.put(hashKey, new ArrayList<>());
            }
            grpAnagrams.get(hashKey).add(str);
        }

        return new ArrayList<>(grpAnagrams.values());
    }

    public static void main(String[] args) {
        System.out.println(new GroupAnagrams().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
}
