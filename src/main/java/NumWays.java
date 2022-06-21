public class NumWays {
    public static int numWaysRecursive(int n) {
        if (n==0 || n==1) {
            return 1;
        }
        return numWays(n-1) + numWays(n-2);
    }

    public static int numWays(int n) {
        if (n==0 || n==1) {
            return 1;
        }
        int[] num = new int[n+1];
        num[0] = 1; num[1] = 1;
        for (int i=2; i<=n; i++) {
            num[i] = num[i-1] + num[i-2];
        }
        return num[n];
    }

    public static int numWaysX(int n, int[] X) {
        if (n==0) {
            return 1;
        }
        int total = 0;
        for(int i: X) {
            if (n-i>=0) total += numWaysX(n-i, X);
        }
        return total;
    }

    public static int numWaysXBottomUp(int n, int[] X) {
        if (n==0) {
            return 1;
        }

        int[] num = new int[n+1];
        num[0] = 1;
        for(int i=1; i<=n; i++) {
            int total = 0;
            for(int j: X) {
                if (i-j>=0) total += num[i=j];
            }
            num[i] = total;
        }

        return num[n];
    }

    public static void main(String[] args) {
        System.out.println(numWaysRecursive(6));
        System.out.println(numWays(6));
        System.out.println(numWaysX(6, new int[]{1,3,5}));
        //System.out.println(numWaysXBottomUp(6, new int[]{1,3,5}));
    }
}
