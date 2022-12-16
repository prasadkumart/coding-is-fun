package sortnsearchrecusion;

import java.util.*;

//https://leetcode.com/problems/k-closest-points-to-origin/solution/
//Time Complexity: O(NlogN), where N is the length of points.
//Space Complexity: O(N).
//Editor's choice: Frequently asked in an Amazon online assessment.
public class KClosest {
    public int[][] kClosest(int[][] points, int k) {
        int size = points.length;
        int[] distance = new int[size];
        int[][] result = new int[k][];

        for(int i=0; i<size; i++) {
            distance[i] = points[i][0]*points[i][0] + points[i][1]*points[i][1];
        }

        //sort distances
        Arrays.sort(distance); //O(NlogN)

        int kthDistance = distance[k-1];

        for(int i=0,j=0; i<size && j<k; i++) {
            if ((points[i][0]*points[i][0] + points[i][1]*points[i][1]) <= kthDistance) {
                result[j++] = points[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] result = new KClosest().kClosest(new int[][]{{1,3},{-2,2}}, 1);
        System.out.println(Arrays.deepToString(result));
    }
}
