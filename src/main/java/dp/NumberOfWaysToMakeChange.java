package dp;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//A = amount
//C = # of coins
//TC: O(AC)
//SC: O(AC)
public class NumberOfWaysToMakeChange {
    static Integer number_of_ways(ArrayList<Integer> coins, Integer amount) {
        int[][] table = new int[coins.size()][amount+1];

        //base case
        for(int row=0; row<coins.size(); row++) {
            for(int idx=0; idx<= amount; idx++) {
                //base case
                // As we can make 0 in one way - by using no coin at all
                if (idx==0) {
                    table[row][0] = 1;
                } else {
                    // We have two options for the coin sitting in index "idx"

                    // 1. We can decide to not use this coin to make the sum "target"
                    // choices that we have till using the last coin
                    // just pull the values from the previous row
                    if (row>0) {
                        table[row][idx] = table[row-1][idx];
                    }

                    // 2. inclusive of current coin value
                    // amount - current coin value + choices that can be made using last coin
                    // So that we do not access any negative index
                    if (idx - coins.get(row) >= 0) {
                        table[row][idx] = (table[row][idx] + table[row][idx - coins.get(row)])%1000000007;
                    }
                }
            }
        }

        return table[coins.size()-1][amount];
    }

    static int helper(int idx, int target, ArrayList<Integer> coins) {
        if (target<0) {
            return 0;
        }
        //no more coins left
        if (idx<0) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        //include the same coin
        int result = helper(idx, target-coins.get(idx), coins);

        //exclude the same coin
        result = (result + helper(idx-1, target, coins)) % 1000000007;

        return result;
    }



    public static void main(String[] args) {
        int[] coins = {1,2,3};
        ArrayList<Integer> coinList = IntStream.of(coins)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(number_of_ways(coinList, 3)); //3

        int[] coins1 = {1,2,3,4,5};
        ArrayList<Integer> coin1List = IntStream.of(coins1)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(number_of_ways(coin1List, 15)); //84
    }
}
