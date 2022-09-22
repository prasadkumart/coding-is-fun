import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/pacific-atlantic-water-flow/
//https://www.youtube.com/watch?v=s-VkcjHqkGI&ab_channel=NeetCode
//https://www.youtube.com/watch?v=DvQf2swmei8&ab_channel=happygirlzt
//O(m.n)TS
public class PacificAtlanticWaterFlow {
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();

        //check length
        if (null == heights) return result;
        //rows
        int m = heights.length;
        if (m == 0) return result;
        //cols
        int n = heights[0].length;

        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];

        //first & last row
        for (int col = 0; col < n; col++) {
            dfs(heights, 0, col, heights[0][col], pac);
            dfs(heights, m - 1, col, heights[m - 1][col], atl);
        }

        //left & right cols
        for (int row = 0; row < m; row++) {
            dfs(heights, row, 0, heights[row][0], pac);
            dfs(heights, row, n - 1, heights[row][n - 1], atl);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j]) {
                    result.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return result;
    }

    private static void dfs(int[][] heights, int r, int c, int prevHeight, boolean[][] ocean) {
        if (r < 0 || c < 0 || r >= heights.length || c >= heights[0].length || heights[r][c] < prevHeight || ocean[r][c]) {
            return;
        }

        ocean[r][c] = true;
        prevHeight = heights[r][c];
        //recurse in all 4 direction
        dfs(heights, r + 1, c, prevHeight, ocean);
        dfs(heights, r - 1, c, prevHeight, ocean);
        dfs(heights, r, c + 1, prevHeight, ocean);
        dfs(heights, r, c - 1, prevHeight, ocean);
    }

    public static void main(String[] args) {
        List<List<Integer>> result = pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}});
        System.out.println(Arrays.toString(result.toArray()));

        result = pacificAtlantic(new int[][]{{2, 1}, {1, 2}});
        System.out.println(Arrays.toString(result.toArray()));

        result = pacificAtlantic(new int[][]{{2, 1}});
        System.out.println(Arrays.toString(result.toArray()));


    }
}
