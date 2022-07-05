import java.util.Arrays;

public class GameOfLife {
    //O(M*N) T
    //O(M*N) S
    public static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] next = new int[m][n];
        int[][] dirs = {
                {-1, 0},
                {-1, 1},
                {0, 1},
                {1, 1},
                {1, 0},
                {1, -1},
                {0, -1},
                {-1, -1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveCount = 0;
                for (int[] dir : dirs) {
                    int x = dir[0] + i;
                    int y = dir[1] + j;

                    if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 1) {
                        liveCount++;
                    }
                }

                if (board[i][j] == 0 && liveCount == 3) {
                    next[i][j] = 1;
                } else if (board[i][j] == 1 && (liveCount == 2 || liveCount == 3)) {
                    next[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = next[i][j];
            }
        }
    }

    //O(M*N) T
    //O(1) S
    public static void gameOfLifeInPlace(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = {
                {-1, 0},
                {-1, 1},
                {0, 1},
                {1, 1},
                {1, 0},
                {1, -1},
                {0, -1},
                {-1, -1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveCount = 0;
                for (int[] dir : dirs) {
                    int x = dir[0] + i;
                    int y = dir[1] + j;

                    if (x >= 0 && x < m && y >= 0 && y < n && Math.abs(board[x][y]) == 1) {
                        liveCount++;
                    }
                }

                //dead -> live
                if (board[i][j] == 0 && liveCount == 3) {
                    board[i][j] = 2;
                } else if (board[i][j] == 1 && (liveCount < 2 || liveCount > 3)) { //live ->  dead
                    board[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] > 0 ? 1 : 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {{1, 1}, {1, 0}};
        gameOfLife(board);
        System.out.println(Arrays.deepToString(board));

        board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLifeInPlace(board);
        System.out.println(Arrays.deepToString(board));
    }
}
