package practice;

import java.util.*;

public class MinTimeCollectAllApples {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        //create adj list
        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];

            adjMap.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            adjMap.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new MinTimeCollectAllApples().minTime(
                7,
                new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}},
                new ArrayList<>(Arrays.asList(false,false,true,false,true,true,false))
        )); //8

        System.out.println(new MinTimeCollectAllApples().minTime(
                7,
                new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}},
                new ArrayList<>(Arrays.asList(false,false,true,false,false,true,false))
        )); //6

        System.out.println(new MinTimeCollectAllApples().minTime(
                7,
                new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}},
                new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false))
        )); //0
    }
}
