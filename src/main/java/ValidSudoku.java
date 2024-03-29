import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/valid-sudoku
//https://www.youtube.com/watch?v=Pl7mMcBm2b8&ab_channel=NickWhite
public class ValidSudoku {
    private static boolean isValidSudoku(char[][] input) {
        HashSet<String> visited = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int curVal = input[i][j];
                if (curVal != '.') {
                    //HashSet.add - true if this set did not already contain the specified element
                    if (!visited.add(curVal + " found in row " + i) ||
                            !visited.add(curVal + " found in col " + j) ||
                            !visited.add(curVal + " found in box " + i/3 + "-" + j/3) ) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    private static boolean isValidSudoku1(char[][] input) {
        //check each row
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char c = input[i][j];
                if (c != '.') {
                    if (set.contains(c)) return false;
                    else set.add(c);
                }
            }
        }

        //check each column
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char c = input[j][i];
                if (c != '.') {
                    if (set.contains(c)) return false;
                    else set.add(c);
                }
            }
        }

        for (int k = 0; k < 9; k++) {
            //System.out.println(k + " " + k/3*3 + " " + k%3*3);
            Set<Character> set = new HashSet<>();
            for (int i = k / 3 * 3; i < k / 3 * 3 + 3; i++) {
                for (int j = k % 3 * 3; j < k % 3 * 3 + 3; j++) {
                    char c = input[j][i];
                    if (c != '.') {
                        if (set.contains(c)) return false;
                        else set.add(c);
                    }
                }
            }
            //System.out.println();
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] input = {{'5', '3', '.', '.', '7', '.', '.', '.', '.', '4'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        input = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        input = new char[][]{
                {'.','.','.','.','5','.','.','1','.'},
                {'.','4','.','3','.','.','.','.','.'},
                {'.','.','.','.','.','3','.','.','1'},
                {'8','.','.','.','.','.','.','2','.'},
                {'.','.','2','.','7','.','.','.','.'},
                {'.','1','5','.','.','.','.','.','.'},
                {'.','.','.','.','.','2','.','.','.'},
                {'.','2','.','9','.','.','.','.','.'},
                {'.','.','4','.','.','.','.','.','.'}};

        System.out.println(isValidSudoku(input));
    }
}
