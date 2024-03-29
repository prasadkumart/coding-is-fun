import java.util.Arrays;
import java.util.LinkedList;

//https://leetcode.com/problems/merge-intervals/
//https://www.youtube.com/watch?v=44H3cEC2fFM&ab_channel=NeetCode
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        //sort arrays
        Arrays.sort(intervals, (inteval1,interval2) -> Integer.compare(inteval1[0],interval2[0])); //O(NlonN) T

        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) { //O(N) T
            //if the list is empty OR
            // if the current interval does not overlap with the last interval in linked list
            if (merged.isEmpty() || interval[0] - merged.getLast()[1] != 1) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        return merged.toArray(int[][]::new);
    }

    public static void main(String[] args) {
        int[][] result = new MergeIntervals().merge(new int[][]{{1, 2}, {3, 4}, {9, 10}, {7, 8}}); //[[1, 4], [7, 10]]
        System.out.println(Arrays.deepToString(result));

        result = new MergeIntervals().merge(new int[][]{{5, 6}, {1, 2}, {3, 4}, {8, 9}, {10, 18}}); //[[1, 6], [8, 18]]
        System.out.println(Arrays.deepToString(result));
    }
}
//Time: O(N log N) because of sorting
//Space: O(N) - looping thru all the intervals