package Trie;

//https://leetcode.com/problems/longest-common-prefix/
//https://www.geeksforgeeks.org/longest-common-prefix-using-trie/?ref=lbp
// Java Program to find the longest common
// prefix of the given words
//Time complexity : preprocessing O(S)O(S), where SS is the number of all characters in the array, LCP query O(m)O(m).

//        Trie build has O(S)O(S) time complexity. To find the common prefix of qq in the Trie takes in the worst case O(m)O(m).

//        Space complexity : O(S)O(S). We only used additional SS extra space for the Trie.
public class LongestCommonPrefix {

    static String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }

        //first is the default prefix
        String prefix = strs[0];

        for(int i=1; i<strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length()-1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }


    // Driver program to test above function
    public static void main(String args[])
    {
        //String arr[] = {"geeksforgeeks", "geeks", "geek", "geezer"};
        String arr[] = {"flow", "flower"};

        String ans = longestCommonPrefix(arr);

        if (ans.length() != 0)
            System.out.println("The longest common prefix is "+ans);
        else
            System.out.println("There is no common prefix");
    }
}

