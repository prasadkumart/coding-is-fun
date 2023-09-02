package practice;

public class UniqueBinarySearchTrees {
    //TC: Catalon - 2n!/(n+1)! n!
    //1,1,2,5,14,42..
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        //base cases
        //when we have '0' nodes, we can build 1 tree (empty tree)
        dp[0] = 1;
        //when we have '1' node, we can build 1 tree
        dp[1] = 1;

        for(int i=2; i<=n; i++) {
            int total = 0;
            for(int j=1; j<=i; j++) {
                //left nodes * right nodes
                total += (dp[j-1] * dp[i-j]);
            }
            dp[i] = total;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        //System.out.println(new UniqueBinarySearchTrees().numTrees(0)); //1
        System.out.println(new UniqueBinarySearchTrees().numTrees(1)); //1
        System.out.println(new UniqueBinarySearchTrees().numTrees(2)); //2
        System.out.println(new UniqueBinarySearchTrees().numTrees(3)); //5
        System.out.println(new UniqueBinarySearchTrees().numTrees(4)); //14
    }
}
