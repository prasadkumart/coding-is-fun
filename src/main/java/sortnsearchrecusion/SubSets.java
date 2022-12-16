package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/subsets/
public class SubSets {
    static ArrayList<String> generate_all_subsets(String s) {
        ArrayList<String> result = new ArrayList<>();

        helper(s, 0, new StringBuilder(""), result);

        return result;
    }

    //dfs
    //TC: O(2^n)
    //SC: O(n)
    //Aux space = max depth X size of the stack
    //          = O(n) X O(1)
    // Stack frame
    // s: pointer - O(1)
    // i: int - O(1)
    // slate: pointer - O(1)
    // result: pointer - O(1)
    static void helper(String s, int height, StringBuilder slate, ArrayList<String> result) {
        if (height == s.length()) {
            result.add(new String(slate.toString()));
            return;
        }

        //recursive
        //exclusion
        helper(s, height+1, slate, result);

        //inclusion
        slate.append(s.charAt(height));
        helper(s, height+1, slate, result);
        slate.deleteCharAt(slate.length()-1);
    }

    public static void main(String[] args) {
        System.out.println(generate_all_subsets("xy"));
    }

}
