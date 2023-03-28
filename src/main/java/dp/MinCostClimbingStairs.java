package dp;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinCostClimbingStairs {
    static Integer min_cost_climbing_stairs(ArrayList<Integer> cost) {
        int n = cost.size();

        //floor below (0), floor above (n+1)
        int[] table = new int[n+2];

        //base case
        table[0] = 0; //floor below price is '0'
        table[1] = cost.get(0);
        cost.add(0); //floor above cost

        for(int i=2; i<=n+1; i++) {
            table[i] = Math.min(table[i-1], table[i-2]) + cost.get(i-1);
        }

        //floor above cost
        //table[n+1] = Math.min(table[n], table[n-1]);

        //floor above cost
        return table[n+1];
    }

    public static void main(String[] args) {
        int[] cost = {1,1,2};
        ArrayList<Integer> costList = IntStream.of(cost)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(min_cost_climbing_stairs(costList));

        int[] cost1 = {3,4};
        ArrayList<Integer> costList1 = IntStream.of(cost1)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(min_cost_climbing_stairs(costList1));
    }
}
