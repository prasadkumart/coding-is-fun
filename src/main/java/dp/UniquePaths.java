package dp;
//DP: decision problem
public class UniquePaths {
    static Integer unique_paths(Integer n, Integer m) {
        int MOD_VAL = 1000000007;

        int[][] table = new int[n][m];
        //initialize first column in each row cell with '1'
        for(int row=0; row<n; row++) {
            table[row][0] = 1;
        }
        //initialize first row in each column cell with '1'
        for(int col=0; col<m; col++) {
            table[0][col] = 1;
        }

        for(int row=1; row<n; row++) {
            for(int col=1; col<m; col++) {
                table[row][col] = (table[row-1][col] + table[row][col-1])%MOD_VAL;
            }
        }

        return table[n-1][m-1];
    }

    public static void main(String[] args) {
        System.out.println(unique_paths(2, 2));
    }
}
