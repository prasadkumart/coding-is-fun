package sortnsearchrecusion;

import java.util.*;

public class PowerSetWithDups {
    static ArrayList<String> get_distinct_subsets(String s) {
        ArrayList<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        //for(int k=0; k<chars.length; k++) {
            backtrack(chars, 0, new StringBuilder(), result);
        //}

        //Collections.sort(result);
        return result;
    }

    static void backtrack(char[] chars, int start, StringBuilder slate, ArrayList<String> result) {
        //if (slate.size() == k) {
            result.add(String.valueOf(slate));
       //     return;
        //}

        for(int i = start; i<chars.length; i++) {
            //ignore if dups
            if (i != start && chars[i-1] == chars[i]) {
                continue;
            }
            slate.append(chars[i]);
            backtrack(chars, i+1, slate, result);
            slate.deleteCharAt(slate.length() - 1);
        }
    }

    public static void main(String[] args) {
        String s = "aab";

        System.out.println(get_distinct_subsets(s));
    }
}
