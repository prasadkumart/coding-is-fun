package practice;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class BestTimeBuySellStockII {
    //PEAK VALLEY approach
    //current element is compared with next immediate to find a valley or a peak
    //TC: O(N)
    //SC: O(1)
    public int maxProfitPeakValley(int[] prices) {
        int profit = 0;
        if (null == prices || prices.length == 0 || prices.length == 1) {
            return profit;
        }
        int valley = prices[0]; //localMin
        int peak = prices[0]; //localMax
        int i = 0;
        //current element is compared with next, so traverse up to penultimate element
        while(i<prices.length-1) {
            //find local min (buying point)
            while(i<prices.length-1 && prices[i] >= prices[i+1]) {
                i++;
            }
            valley = prices[i];

            //find local max (selling point)
            while(i<prices.length-1 && prices[i] <= prices[i+1]) {
                i++;
            }
            peak = prices[i];

            profit += peak - valley;
        }

        return profit;
    }

    //Simple ONE-PASS
    //add all +ve profits at each point
    public int maxProfit(int[] prices) {
        int profit = 0;
        for(int i=1; i<prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(new BestTimeBuySellStockII().maxProfitPeakValley(new int[]{7,1,5,3,6,4})); //7
        System.out.println(new BestTimeBuySellStockII().maxProfitPeakValley(new int[]{1,5,3})); //4
        System.out.println(new BestTimeBuySellStockII().maxProfitPeakValley(new int[]{1,5,9})); //8
        System.out.println(new BestTimeBuySellStockII().maxProfitPeakValley(new int[]{7,6,4,3,1})); //0
        System.out.println(new BestTimeBuySellStockII().maxProfitPeakValley(new int[]{3,2,6,5,0,3})); //7
        System.out.println(new BestTimeBuySellStockII().maxProfitPeakValley(new int[]{3,3,5,0,0,3,1,4})); //8
        System.out.println(new BestTimeBuySellStockII().maxProfitPeakValley(new int[]{2,7,1,11,4})); //15
    }
}
