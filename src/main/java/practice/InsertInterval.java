package practice;

import java.util.ArrayList;
import java.util.Arrays;

//https://leetcode.com/problems/insert-interval/description/
//https://www.youtube.com/watch?v=A8NUOmlwOlM
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> result = new ArrayList<>();

        for(int i=0; i< intervals.length; i++) {
            //new interval lies before the current interval
            //new interval second value is less than the current's first value
            if (newInterval[1] < intervals[i][0]) {
                result.add(newInterval);

                //add remaining intervals
                while(i<intervals.length) {
                    result.add(intervals[i]);
                    i++;
                }
                return result.toArray(new int[result.size()][]);
            } else if (newInterval[0] > intervals[i][1]) { //new interval lies after the current interval
                result.add(intervals[i]); // NEW INTERVAL is not added to th result yet
            } else { //inverval overlaps
                newInterval = new int[] {
                        Math.min(newInterval[0], intervals[i][0]),
                        Math.max(newInterval[1], intervals[i][1])
                };
                // NEW INTERVAL is not added to th result yet
            }
        }
        result.add(newInterval);

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new InsertInterval().insert(new int[][]{{1,3},{6,9}}, new int[]{-2,-5}))); //[[-2, -5], [1, 3], [6, 9]]
        System.out.println(Arrays.deepToString(new InsertInterval().insert(new int[][]{{1,3},{6,9}}, new int[]{2,5}))); //[[1,5],[6,9]]
        System.out.println(Arrays.deepToString(new InsertInterval().insert(new int[][]{{-1,0},{6,9}}, new int[]{2,5}))); //[[-1,0],[2,5],[6,9]]
        System.out.println(Arrays.deepToString(new InsertInterval().insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8}))); //[[1,2],[3,10],[12,16]]
    }
}
