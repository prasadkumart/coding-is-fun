import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/word-search/
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


