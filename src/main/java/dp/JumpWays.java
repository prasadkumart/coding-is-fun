package dp;

//f(n) = f(n-1)+f(n-2) //two choice
//f(n) = f(n-1)+f(n-2)+f(n-3) //three choices
//TC: o(n)
//SC: O(1)
public class JumpWays {
    static Long jump_ways(Integer n) {
        // Write your code here.
        return jumpWays(n);
    }

    static Long jumpWays(int n) {
        if (n==0) {
            return 1L;
        }
        long[] memo = new long[3];
        memo[0] = memo[1] = 1;

        for (int i=2; i<=n; i++) {
            memo[i%3] = memo[(i-1)%3] + memo[(i-2)%3];
        }

        return memo[n%3];
    }

    public static void main(String[] args) {
        System.out.println(jump_ways(0));
    }
}
