package practice;

//BACKTRACKING
//TC: board O(m * n * 4^L) #L length of the word
//SC: O(L)
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && helper(board, i, j, 0, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    //TC: since search happens in all 4 directions - 4^L #L length of the word
    boolean helper(char[][] board, int row, int col, int count, String word) {
        if (count == word.length()) {
            return true;
        }
        //out of bounds
        if (row<0 || row>=board.length || col<0 || col>=board[0].length || word.charAt(count) != board[row][col]) {
            return false;
        }

        //matched, increment the count
        count++;

        //mark as visited
        char temp = board[row][col];
        board[row][col] = '0';

        boolean flag = helper(board, row-1, col, count, word)
                || helper(board, row, col-1, count, word)
                || helper(board, row, col+1, count, word)
                || helper(board, row+1, col, count, word);

        //backtrack & unvisited
        board[row][col] = temp;

        return flag;
    }

    public static void main(String[] args) {
        System.out.println(new WordSearch().exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"));//true
        System.out.println(new WordSearch().exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABD"));//false
        System.out.println(new WordSearch().exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "SEE"));//true
    }
}
