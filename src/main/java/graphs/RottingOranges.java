package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

//https://leetcode.com/problems/rotting-oranges/description/
//https://www.youtube.com/watch?v=y704fEOx0s0&ab_channel=NeetCode
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;
        int freshCount = 0, timeElapsed = 0;
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        //find fresh orange count
        //add rotten to queue to perform multi BFS
        //TC: O(MN) M: rows, N: cols
        //SC: O(MN) M: rows, N: cols // for queue to store all cell values in the grid
        for(int row=0; row<ROWS; row++) {
            for(int col=0; col<COLS; col++) {
                if (grid[row][col] == 1) {
                    freshCount++;
                }
                if (grid[row][col] == 2) {
                    queue.add(new Pair<>(row,col));
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }

        //4-diff directions
        int[][] directions = {
                {-1,0},
                {0,1},
                {1,0},
                {0,-1},
        };

        while(!queue.isEmpty()) {
            int queueCount = queue.size();
            for(int i=0; i<queueCount; i++) {
                Pair<Integer, Integer> rottenOrange = queue.poll();

                for(int[] direction : directions) {
                    int row = rottenOrange.val1 + direction[0];
                    int col = rottenOrange.val2 + direction[1];

                    if (row >= 0 && row < ROWS
                    && col >=0 && col < COLS
                    && grid[row][col] == 1) {
                        grid[row][col] = 2;
                        freshCount--;
                        queue.add(new Pair<>(row, col));
                    }
                }
            }
            timeElapsed++;
        }

        //timeElapsed has additional count
        timeElapsed--;

        return (freshCount == 0) ? timeElapsed : -1;
    }

    public static void main(String[] args) {
        System.out.println(new RottingOranges().orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}})); //4
        System.out.println(new RottingOranges().orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}})); //-1
        System.out.println(new RottingOranges().orangesRotting(new int[][]{{0}})); //Since there are no fresh oranges at minute 0, the answer is just 0.
    }

    class Pair<T1, T2> {
        T1 val1;
        T2 val2;

        public Pair(T1 val1, T2 val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }
}
