package dp;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//Brute-force: 2^n
//TC: O(n*2) - n is size of the prize array
//SC: O(n)
public class CutRopeMaxProfit {
    static long max_product_from_cut_pieces(Integer n) {
        long[] table = new long[n+1];

        //base case
        table[0] = 1L;
        table[1] = 1L;

        for(int i=2; i<=n; i++) {
            //table[i] = 1;
            for(int j=1; j<i; j++) {
                table[i] = Math.max(table[i], Math.max(j*(i-j), j*table[i-j]));
            }
        }

        return table[n];
    }

    public static void main(String[] args) {
        System.out.println(max_product_from_cut_pieces(2)); //1
        System.out.println(max_product_from_cut_pieces(3)); //2
        System.out.println(max_product_from_cut_pieces(4)); //4
        System.out.println(max_product_from_cut_pieces(5)); //6
    }
}
