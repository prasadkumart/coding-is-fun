package dp;

//f(n) = f(n-1)+f(n-2)
public class Fibonacci {
    static Integer find_fibonacci(Integer n) {
        //bottomup O(1) space
        return bottomUpFibWith3ArrayElements(n);

        //bottomup
        //return bottomUpFib(n);

        //topDown
        /*if (n==0 || n==1) {
            return n;
        }
        int[] memo = new int[n+1];
        memo[1] = 1;
        return topDownFib(n, memo);*/

        //recursive
        //return fibRecursive(n);
    }

    static int fibRecursive(int n) {
        if (n==0 || n==1) {
            return n;
        }
        if (n==2) {
            return 1;
        }
        return fibRecursive(n-1) + fibRecursive(n-2);
    }

    //top-down-memoization
    static int topDownFib(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }
        if (n==0 || n==1) {
            return n;
        }

        memo[n] = topDownFib(n-1, memo) + topDownFib(n-2, memo);
        return memo[n];
    }

    //bottom-up-iterative-memoization (iterative tabulation)
    static int bottomUpFib(int n) {
        if (n==0) {
            return 0;
        }
        int[] memo = new int[n+1];
        memo[0] = 0;
        memo[1] = 1;

        for(int i=2; i<=n; i++) {
            memo[i] = memo[i%3-1] + memo[i%3-2];
        }

        return memo[n];
    }

    //bottom-up-iterative-memoization-
    static int bottomUpFibWith3ArrayElements(int n) {
        if (n==0) {
            return 0;
        }
        int[] memo = new int[3];
        memo[0] = 0;
        memo[1] = 1;

        for(int i=2; i<=n; i++) {
            memo[i%3] = memo[(i-1)%3] + memo[(i-2)%3];
        }

        return memo[n%3];
    }

    public static void main(String[] args) {
        System.out.println(find_fibonacci(6));
    }
}
