package dp;

//c(n,r) = c(n-1, r) + c(n-1, r-1)
//TC: O(nr)
//SC: O(nr)
public class NChooseR {
    static Integer ncr(Integer n, Integer r) {
        if (r > n) return 0;

        //base case
        if (n==0 || r==1) {
            return 1;
        }

        int MOD_VAL = 1000000007;
        int[][] table = new int[n+1][r+1];
        for(int row=0; row<=n; row++) {
            table[row][0] = 1;
        }
        for(int col=1; col<=r; col++) {
            table[col][col] = 1;
        }

        //recursive case
        for(int row=2; row<=n; row++) {
            for(int col=1; col<=Math.min(row,r); col++) {
                table[row][col] = (table[row-1][col] + table[row-1][col-1]) % MOD_VAL;
            }
        }

        return table[n][r];
    }

    public static void main(String[] args) {
        System.out.println(ncr(5, 3)); //10
        System.out.println(ncr(3, 5)); //0
        System.out.println(ncr(200, 100)); //114908264 //407336795
    }
}
