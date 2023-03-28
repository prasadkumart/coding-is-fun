package dp;

import java.util.ArrayList;
import java.util.Arrays;

public class WordBreak {
    static Boolean word_break(String s, ArrayList<String> words_dictionary) {
        boolean table[] = new boolean[s.length()+1];

        //base case
        table[0] = true;
        for(int i=1; i< table.length; i++) {
            table[i] = false;
        }

        for(int i=1; i<=s.length(); i++) {
            for(int j=0; j<i; j++) {
                if (table[j] && words_dictionary.contains(s.substring(j, i))) {
                    table[i] = true;
                    break;
                }
            }
        }

        return table[s.length()];
    }

    public static void main(String[] args) {
        ArrayList<String> dict = new ArrayList<String>(Arrays.asList("leet", "code"));
        System.out.println(word_break("leetcode", dict));

        ArrayList<String> dict2 = new ArrayList<String>(Arrays.asList("world", "hello", "faang"));
        System.out.println(word_break("helloworldhello", dict2));

        ArrayList<String> dict1 = new ArrayList<String>(Arrays.asList("interview", "preparation"));
        System.out.println(word_break("interviewkickstart", dict1));
    }
}
