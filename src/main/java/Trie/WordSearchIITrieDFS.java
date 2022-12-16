package Trie;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/word-search-ii/
//O(M*N*4 * 3^(L - 1) T, because we only need to search 3 directions after first search.
//O(N) S
//Time complexity: O(M(4⋅3 ^ L−1)), where MM is the number of cells in the board and LL is the maximum length of words.

// It is tricky is calculate the exact number of steps that a backtracking algorithm would perform. We provide a upper bound of steps for the worst scenario for this problem.
// The algorithm loops over all the cells in the board, therefore we have MM as a factor in the complexity formula.
// It then boils down to the maximum number of steps we would need for each starting cell (i.e.4⋅3 ^ L−1).

// Assume the maximum length of word is L, starting from a cell, initially we would have at most 4 directions to explore. Assume each direction is valid (i.e. worst case),
// during the following exploration, we have at most 3 neighbor cells (excluding the cell where we come from) to explore. As a result, we would traverse at most 4⋅3^L−1 cells during the backtracking exploration.


// Space Complexity: O(N), where NN is the total number of letters in the dictionary.
// The main space consumed by the algorithm is the Trie data structure we build. In the worst case where there is no overlapping of prefixes among the words,
// the Trie would have as many nodes as the letters of all words. And optionally, one might keep a copy of words in the Trie as well. As a result, we might need 2N2N space for the Trie.
public class WordSearchIITrieDFS {

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0) {
            return null;
        }
        //build a Trie
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();
        for (int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length;j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    public void dfs(char[][] board, int i, int j, TrieNode trieNode, List<String> result) {
        if (i<0 || i>=board.length || j<0 || j>=board[i].length) {
            return;
        }

        char c = board[i][j];
        int index = c -'a';
        // no matching child
        if (c == '$' || trieNode.childen[index] == null) {
            return;
        }
        //new child
        trieNode = trieNode.childen[index];
        if (trieNode.word != null) { //word found
            result.add(trieNode.word);
            trieNode.word = null; //de-duplicate
        }

        board[i][j] = '$';
        dfs(board,i+1,j,trieNode,result);
        dfs(board,i-1,j,trieNode,result);
        dfs(board,i,j+1,trieNode,result);
        dfs(board,i,j-1,trieNode,result);
        board[i][j] = c;
    }

    TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for(String word: words) {
            TrieNode tempNode = root;
            for (char c: word.toCharArray()) {
                int index = c - 'a';
                if (tempNode.childen[index] == null) {
                    tempNode.childen[index] = new TrieNode();
                }
                tempNode = tempNode.childen[index];
            }
            tempNode.word = word;
        }

        return root;
    }
    class TrieNode {
        TrieNode[] childen = new TrieNode[26];
        String word;
    }

    public static void main(String[] args) {
        System.out.println(new WordSearchIITrieDFS().findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},new String[]{"oath","pea","eat","rain"}));
        System.out.println(new WordSearchIITrieDFS().findWords(new char[][]{{'o','a','b','n'},{'o','t','a','e'},{'a','h','k','r'},{'a','f','l','v'}},new String[]{"oa","oaa"}));
    }
}


