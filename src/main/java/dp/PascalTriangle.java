package dp;

import java.util.ArrayList;
import java.util.Arrays;

public class PascalTriangle {
    static ArrayList<ArrayList<Integer>> find_pascal_triangle(Integer n) {
        final int MOD = 1000000007;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        //base case
        result.add(new ArrayList<>(Arrays.asList(1)));
        if (n>1) {
            result.add(new ArrayList<>(Arrays.asList(1, 1)));
        }

        if (n>2) {
            for (int i=2; i<n; i++) {
                ArrayList<Integer> slate = new ArrayList<>();
                for(int j=0; j<=i; j++) {
                    if (j==0||i==j) {
                        slate.add(1);
                    } else {
                        slate.add((result.get(i-1).get(j-1) + result.get(i-1).get(j))%MOD);
                    }
                }
                result.add(new ArrayList<>(slate));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> result = find_pascal_triangle(5);

        for(ArrayList<Integer> vals : result) {
            for (int val: vals) {
                System.out.print(val);
            }
            System.out.print("\n");
        }
    }
}
