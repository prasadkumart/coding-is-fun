//https://leetcode.com/problems/word-search/
public class WordSearchDFS {
    //O(NM * NM * No of words)
    public static boolean exist(char[][] board, String word) {
        if (null == board || board.length ==0) return false;

        for (int i=0;i<board.length;i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board,i,j,0,word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int i, int j, int count, String word) {
        if (count == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(count)) {
            return false;
        }

        //match found
        count++;
        char temp = board[i][j];
        board[i][j] = ' ';
        boolean found = dfs(board, i - 1, j, count, word)
                || dfs(board, i + 1, j, count, word)
                || dfs(board, i,j-1,count,word)
                || dfs(board,i,j+1,count,word);
        board[i][j] = temp;
        return found;
    }

    public static void main(String[] args) {
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED"));
    }
}


