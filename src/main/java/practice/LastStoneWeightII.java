package practice;

//https://leetcode.com/problems/last-stone-weight-ii/description/
//https://www.youtube.com/watch?v=TaZxJt4-FHk&t=323s
//https://www.youtube.com/watch?v=Ad8xOhU82l0
//split the numbers into two groups, such that the diff is minimal
//optimal solution would be getting the sum of each group closer to (sum of the numbers)/2
//0/1 knapsack to collect the numbers

//finding two sub-arrays and minimize the difference is the solution
//total (S) = S1 + S2
//solution: to minimize s1-s2; need to make sure that the s2 reaches (S/2), diff would be 0 - BEST CASE
//if not, then at least maximize the value of S2 to reach to S/2

// target = sum/2
//res = S1 - S2 => sum => 2 * target => 2 * DP[target]

//its a bounded knapsack, which means each stone can be picked only once
public class LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        return 0;
    }

    //https://www.youtube.com/watch?v=TaZxJt4-FHk&t=323s
    public int lastStoneWeightII1(int[] stones) {
        int N = stones.length;

        //sum of stones
        int total = 0;
        for(int stone : stones) {
            total += stone;
        }

        //dp array

        boolean[] dp = new boolean[total/2+1];
        dp[0] = true;
        int maxInSetS2 = 0;
        for(int stone : stones) {
            boolean[] temp = dp.clone();
            for(int i=stone; i<=total/2; i++) {
                if (dp[i-stone]) {
                    temp[i] = true;
                    maxInSetS2 = Math.max(maxInSetS2, i);

                    //when maxInSetS2 reaches to S/2
                    if (maxInSetS2 == total/2) { //BEST SOLUTION
                        return total - maxInSetS2*2;
                    }
                }
            }
            dp = temp;
        }
        System.out.println("\nmaxInSetS2 :" + maxInSetS2);
        return total - maxInSetS2*2;
    }

    public static void main(String[] args) {
        System.out.println(new LastStoneWeightII().lastStoneWeightII(new int[]{2,7,4,1,8,1}));//1
        //System.out.println(new LastStoneWeightII().lastStoneWeightII(new int[]{31,26,33,21,40}));//5
    }
}
