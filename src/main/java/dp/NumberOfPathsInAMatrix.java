package dp;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberOfPathsInAMatrix {
    static Integer number_of_paths(ArrayList<ArrayList<Integer>> matrix) {
        final int MOD = 1000000007;
        
        int rowLen = matrix.size();
        int colLen = matrix.get(0).size();
        int[][] table = new int[rowLen][colLen];

        //Base cases
        //initialize
        table[0][0] = matrix.get(0).get(0);
        //first row sums //0th row
        for(int col=1; col<colLen; col++) {
            table[0][col] = (matrix.get(0).get(col) == 1 && table[0][col-1] == 1) ? 1 : 0;
        }
        //first col sums //0th column
        for(int row=1; row<rowLen; row++) {
            table[row][0] = (matrix.get(row).get(0) == 1 && table[row-1][0] == 1) ? 1 : 0;
        }

        //DP
        for(int row=1; row<rowLen; row++) {
            for (int col=1; col<colLen; col++) {
                table[row][col] = matrix.get(row).get(col) == 0 ? 0 : ((table[row-1][col] != 0 ? table[row-1][col] : 0)%MOD +  ((table[row][col-1] != 0 ? table[row][col-1] : 0)%MOD)%MOD);
            }
        }

        return table[rowLen-1][colLen-1];
        
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        int[] row1 = {1,1,0,1};
        ArrayList<Integer> row1List = IntStream.of(row1)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        int[] row2 = {1,1,1,0};
        ArrayList<Integer> row2List = IntStream.of(row2)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        matrix.add(row1List);
        matrix.add(row2List);

        System.out.println(number_of_paths(matrix));

    }
}
