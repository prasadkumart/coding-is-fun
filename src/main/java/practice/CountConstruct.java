package practice;

import java.util.Arrays;
import java.util.List;

public class CountConstruct {
    public int countConstruct(String target, List<String> wordBank) {
        int count = 0;

        return count;
    }

    public static void main(String[] args) {
        String s = "purple";
        System.out.println(s.startsWith("pur") ? s.replaceFirst("^"+"pur", "") : s);
        System.out.println(s.startsWith("pur") ? s.substring("pur".length()) : s);
        //System.out.println();
        //System.out.println(new CountConstruct().countConstruct("purple", Arrays.asList(new String[]{"purp","p","ur","le","purpl"})));
    }
}
