import java.util.Arrays;

//https://leetcode.com/problems/minesweeper/
//O(N ^ 2)
public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] dirs = {{0, 1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {1, -1}, {1, 0}, {1, 1}};

        int row = click[0], col = click[1];
        int m = board.length, n = board[0].length;
        //if there is unrevealed/revealed mine
        if (board[row][col] == 'M' || board[row][col] == 'X') {
            board[row][col] = 'X';
            return board;
        }
        int mines = 0;
        //check for mines in all 8 directions
        for (int[] dir : dirs) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            //if the new cell valid and has a mine
            if (newRow >= 0 && newRow < m
                    && newCol >= 0 && newCol < n
                    && board[newRow][newCol] == 'M') {
                mines++;
            }
        }

        //mines found at the clicked cell
        if (mines > 0) {
            board[row][col] = (char) (mines + '0');
            return board;
        }

        //no mines at the clicked cell
        board[row][col] = 'B';
        //unveil adjacent cell to an empty cell recursively in all 8 directions
        for (int[] dir : dirs) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
            //if the new cell valid and empty
            if (newRow >= 0 && newRow < m
                    && newCol >= 0 && newCol < n
                    && board[newRow][newCol] == 'E') {
                updateBoard(board, new int[]{newRow, newCol});
            }
        }

        return board;
    }


    public static void main(String[] args) {
        char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        int[] click = {3, 0};
        System.out.println(Arrays.deepToString(new Minesweeper().updateBoard(board, click)));
    }
}
