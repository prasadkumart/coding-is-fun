package dp;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//TC: O(ak) //amount * k - no of coins
//SC: O(a)
public class CoinChange1 {
    static Integer minimum_coins(ArrayList<Integer> coins, Integer value) {
        int[] table = new int[value+1];

        //base case
        table[0] = 0; // not needed in java
        //initialize remaining all w/ MAX_INT
        for(int i=1; i<=value; i++) {
            table[i] = Integer.MAX_VALUE;
        }

        for(int i=1; i<=value; i++) {
            for(Integer coin: coins) {
                if (i-coin>=0) {
                    table[i] = Math.min(table[i], table[i-coin]);
                }
            }
            table[i]++;
        }

        return table[value];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,5};
        ArrayList<Integer> numsList = IntStream.of(nums)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(minimum_coins(numsList, 11)); //3
    }
}
