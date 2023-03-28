package graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class CountIslands {
    static int[][] directions = {
            {-1,0},
            {-1,-1},
            {-1,1},
            {0,-1},
            {0,1},
            {1,0},
            {1,-1},
            {1,1}
    };
    static Integer count_islands(ArrayList<ArrayList<Integer>> matrix) {
        int numIslands = 0;

        for(int i=0; i<matrix.size(); i++) {
            for (int j=0; j<matrix.get(0).size(); j++) {
                if (matrix.get(i).get(j) == 1) {
                    numIslands ++;
                    dfsHelper(i, j, matrix);
                }
            }
        }

        return numIslands;
    }

    private static void dfsHelper(int row, int col, ArrayList<ArrayList<Integer>> matrix) {
        //mark cell as visited
        matrix.get(row).set(col, 0);

        //visit all around including diagonal
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            //base case
            if ( (newRow<0||newRow>=matrix.size()) || (newCol<0||newCol>=matrix.get(0).size())) {
                continue;
            }

            if (matrix.get(newRow).get(newCol) == 1) {
                dfsHelper(newRow, newCol, matrix);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<Integer>(Arrays.asList(1,1)));
//        matrix.add(new ArrayList<Integer>(Arrays.asList(1,1,0,0,0)));
//        matrix.add(new ArrayList<Integer>(Arrays.asList(0,1,0,0,1)));
//        matrix.add(new ArrayList<Integer>(Arrays.asList(1,0,0,1,1)));
//        matrix.add(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0)));
//        matrix.add(new ArrayList<Integer>(Arrays.asList(1,0,1,0,1)));

        count_islands(matrix);
    }
}
