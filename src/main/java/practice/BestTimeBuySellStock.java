package practice;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BestTimeBuySellStock {
    //TC: O(n^2)
    //SC: O(1)
    public int maxProfitBF(int[] prices) {
        int maxProfit = 0;
        //TC: O(n^2)
        for (int i=0; i<prices.length; i++) { //O(n)
            for(int j=i+1; j<prices.length; j++) { //O(n-1)
                if (prices[i] < prices[j]) {
                    maxProfit = Math.max(maxProfit, prices[j]-prices[i]);
                }
            }
        }

        return maxProfit;
    }

    //TC: O(N)
    //SC: O(1)
    public int maxProfit(int[] prices) {
        int profit = 0;
        if (null == prices || prices.length == 0 || prices.length == 1) {
            return profit;
        }
        int buy_price = prices[0];

        for(int i=1; i<prices.length; i++) {
            //find min for buying point
            if (buy_price > prices[i]) {
                buy_price = prices[i];
            } else {
                profit = Math.max(profit, prices[i] - buy_price);
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(new BestTimeBuySellStock().maxProfitBF(new int[]{7,1,5,3,6,4})); //5
        System.out.println(new BestTimeBuySellStock().maxProfit(new int[]{1,5,3})); //4
        System.out.println(new BestTimeBuySellStock().maxProfit(new int[]{1,5,9})); //8
        System.out.println(new BestTimeBuySellStock().maxProfit(new int[]{7,6,4,3,1})); //0
        System.out.println(new BestTimeBuySellStock().maxProfit(new int[]{3,2,6,5,0,3})); //4
        System.out.println(new BestTimeBuySellStock().maxProfit(new int[]{3,3,5,0,0,3,1,4})); //4
        System.out.println(new BestTimeBuySellStock().maxProfit(new int[]{2,7,1,11,4})); //10
    }
}
