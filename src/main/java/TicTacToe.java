//https://leetcode.com/problems/number-of-islands/
//O(m.n)TS
public class TicTacToe {
    int n;
    //
    int[] rows;
    int[] cols;
    int diagonal;
    int xDiagonal;

    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        xDiagonal = 0;
    }

    public int move(int row, int col, int player) {
        //identify player
        int playerVal = (player == 1) ? 1 : -1;

        //increment row
        rows[row] += playerVal;

        //increment col
        cols[col] += playerVal;

        //diagonal
        if (row == col) {
            diagonal += playerVal;
        }

        //x-diagonal
        if (row + col == n - 1) {
            xDiagonal += playerVal;
        }

        //any of them equal to n
        if (Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(xDiagonal) == n) {
            return player;
        }

        return 0;
    }

    public static void main(String[] args) {
        //Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
        TicTacToe toe = new TicTacToe(3);

        System.out.println(toe.move(0, 0, 1)); //-> Returns 0 (no one wins)
        System.out.println(toe.move(0, 2, 2)); //-> Returns 0 (no one wins)
        System.out.println(toe.move(2, 2, 1)); //-> Returns 0 (no one wins)
        System.out.println(toe.move(1, 1, 2)); //-> Returns 0 (no one wins)
        System.out.println(toe.move(2, 0, 1)); //-> Returns 0 (no one wins)
        System.out.println(toe.move(1, 0, 2)); //-> Returns 0 (no one wins)
        System.out.println(toe.move(2, 1, 1)); //-> Returns 1 (player 1 wins)
    }
}