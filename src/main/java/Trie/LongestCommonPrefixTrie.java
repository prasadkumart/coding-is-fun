package Trie;

//https://leetcode.com/problems/longest-common-prefix/
//https://www.geeksforgeeks.org/longest-common-prefix-using-trie/?ref=lbp
// Java Program to find the longest common
// prefix of the given words
//Time complexity : preprocessing O(S)O(S), where SS is the number of all characters in the array, LCP query O(m)O(m).

//        Trie build has O(S)O(S) time complexity. To find the common prefix of qq in the Trie takes in the worst case O(m)O(m).

//        Space complexity : O(S)O(S). We only used additional SS extra space for the Trie.
public class LongestCommonPrefixTrie {
    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;

    // Trie node
    static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isLeaf is true if the node represents
        // end of a word
        boolean isLeaf;

        // constructor
        public TrieNode() {
            isLeaf = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };

    static TrieNode root;

    //to get the index of the first child from a node
    // that is occurred in count function, will be referenced in WalkTrie function
    static int index;

    // If not present, inserts the key into the trie
    // If the key is a prefix of trie node, just marks
    // leaf node
    static void insert(String key)
    {
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (int level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isLeaf = true;
    }

    // Counts and returns the number of children of the
    // current node
    static int countChildren(TrieNode node)
    {
        int count = 0;
        for (int i=0; i<ALPHABET_SIZE; i++)
        {
            if (node.children[i] != null)
            {
                count++;
                index = i;
            }
        }
        return (count);
    }

    // Perform a walk on the trie and return the
    // longest common prefix string
    static String walkTrie()
    {
        TrieNode pCrawl = root;
        index = 0;
        String prefix = "";

        while (countChildren(pCrawl) == 1 &&
                pCrawl.isLeaf == false)
        {
            pCrawl = pCrawl.children[index];
            prefix += (char)('a' + index);
        }
        return prefix;
    }

    // A Function to construct trie
    static void constructTrie(String arr[], int n)
    {
        for (int i = 0; i < n; i++)
            insert (arr[i]);
        return;
    }

    // A Function that returns the longest common prefix
    // from the array of strings
    static String commonPrefix(String arr[], int n)
    {
        root = new TrieNode();
        constructTrie(arr, n);

        // Perform a walk on the trie
        return walkTrie();
    }

    static String longestCommonPrefix(String[] strs) {
        root = new TrieNode();
        constructTrie(strs, strs.length);

        // Perform a walk on the trie
        return walkTrie();
    }


    // Driver program to test above function
    public static void main(String args[])
    {
        //String arr[] = {"geeksforgeeks", "geeks", "geek", "geezer"};
        String arr[] = {"flow", "flower"};
        int n = arr.length;

        String ans = commonPrefix(arr, n);

        if (ans.length() != 0)
            System.out.println("The longest common prefix is "+ans);
        else
            System.out.println("There is no common prefix");
    }
}

