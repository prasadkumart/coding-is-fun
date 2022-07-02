import java.util.Arrays;
import java.util.LinkedList;

//https://leetcode.com/problems/merge-intervals/
public class MergeOverlappingIntervals {
    public int[][] merge(int[][] intervals) {
        //sort arrays
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0])); //O(NlonN) T

        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) { //O(N) T
            //if the list is empty OR
            // if the current interval does not overlap with the last interval in linked list
            if (merged.isEmpty() || interval[0] > merged.getLast()[1]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] result = new MergeOverlappingIntervals().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        System.out.println(Arrays.deepToString(result));

        result = new MergeOverlappingIntervals().merge(new int[][]{{1, 2}, {3, 6}, {8, 9}, {9, 18}});
        System.out.println(Arrays.deepToString(result));
    }
}
//Time: O(N log N)
//Space: O(N)