import java.util.ArrayList;

//https://leetcode.com/problems/combination-sum/
public class Fibonacci {

    static Integer find_fibonacci(Integer n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return find_fibonacci(n-1) + find_fibonacci(n-2);
    }

    //O(2^n)
    public static int fibRec(int n) {
        int result;
        if (n == 1 || n == 2) {
            result = 1;
        } else {
            result = fibRec(n - 1) + fibRec(n - 2);
        }

        return result;
    }

    //O(N)
    public static int fibMemoization(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }
        int result;
        if (n == 1 || n == 2) {
            result = 1;
        } else {
            result = fibMemoization(n - 1, memo) + fibMemoization(n - 2, memo);
        }

        memo[n] = result;

        return result;
    }

    //O(N)
    public static int fibBottomUp(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = fibBottomUp(i - 1) + fibBottomUp(i - 2);
        }
        return arr[n];
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++)
            System.out.print(fibRec(i) + " ");

        System.out.println("\n");
        int[] memo = new int[10];
        //System.out.print(fibMemoization(5, memo) + " ");
        for (int i = 1; i < 10; i++)
            System.out.print(fibMemoization(i, memo) + " ");

        System.out.println("\n");
        for (int i = 1; i < 10; i++)
            System.out.print(fibBottomUp(i) + " ");


        System.out.println("--->"+find_fibonacci(4));
    }
}
