//https://leetcode.com/problems/word-search/
// https://www.youtube.com/watch?v=vYYNp0Jrdv0&t=466s&ab_channel=KevinNaughtonJr.
//Time Complexity: O(N⋅3^L) where NN is the number of cells in the board and LL is the length of the word to be matched.
//
//        For the backtracking function, initially we could have at most 4 directions to explore, but further the choices are reduced into 3
//        (since we won't go back to where we come from). As a result, the execution trace after the first step could be visualized as a 3-ary tree,
//        each of the branches represent a potential exploration in the corresponding direction. Therefore, in the worst case, the total number of
//        invocation would be the number of nodes in a full 3-nary tree, which is about 3^L.
//
//        We iterate through the board for backtracking, i.e. there could be NN times invocation for the backtracking function in the worst case.
//
//        As a result, overall the time complexity of the algorithm would be O(N⋅3^L).
//
//        Space Complexity: O(L) where LL is the length of the word to be matched.
//
//        The main consumption of the memory lies in the recursion call of the backtracking function. The maximum length of the call stack would be the length of the word.
//        Therefore, the space complexity of the algorithm is O(L).
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


