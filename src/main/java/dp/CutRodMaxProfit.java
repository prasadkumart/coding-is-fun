package dp;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TC: O(n*2) - n is size of the prize array
//SC: O(n)
public class CutRodMaxProfit {
    static Integer get_maximum_profit(ArrayList<Integer> price) {
        int[] table = new int[price.size()+1];

        //base case
        table[0] = 0;

        for(int i=1; i<=price.size(); i++) {
            // Price for rod of length i is stored in (i-1)th index of price, here we choose not to cut
            // the rod of length i into smaller pieces and put the value for profit against dp[i]
            table[i] = price.get(i-1);

            for(int j=1; j<i; j++) {
                // Sum up profit obtained by cutting a length of j, i.e. price.get(j-1) and the profit
                // obtained from remaining rod length, i.e. dp[i-j]
                table[i] = Math.max(table[i], price.get(j-1) + table[i-j]);
            }
        }

        /*
        for(int i=1; i<=price.size(); i++) {
            for(int size=1; size<=i; size++) {
                int cost = (i/size) * price.get(size-1);
                if (i%size > 0) {
                    cost += price.get((i%size)-1);
                }
                table[i] = Math.max(cost, table[i]);
            }
        }*/

        return table[price.size()];
    }

    public static void main(String[] args) {
        int[] cost = {1,5,8,9};
        ArrayList<Integer> costList = IntStream.of(cost)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(get_maximum_profit(costList)); //10

        int[] cost1 = {3, 7, 2, 6, 6};
        ArrayList<Integer> cost1List = IntStream.of(cost1)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(get_maximum_profit(cost1List)); //17
    }
}
