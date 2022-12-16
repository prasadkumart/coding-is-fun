package graphs;

import java.util.ArrayList;

//in Eulerian Cycle, degrees of all nodes should be even
public class EulerianCycleExists {

    /*
    Asymptotic complexity in terms of the number of vertices ( = `n`) and number of edges ( = `e`):
    * Time: O(n + e).
    * Auxiliary space: O(n).
    * Total space: O(n + e).
    */
    static Boolean check_if_eulerian_cycle_exists(Integer n, ArrayList<ArrayList<Integer>> edges) {
        int[] edgesCount = new int[n]; //O(n) S

        for (ArrayList<Integer> edge: edges) { //O(n+e) T
            edgesCount[edge.get(0)]++;
            edgesCount[edge.get(1)]++;
        }

        for(int i=0; i<n; i++) { //O(n)
            if (edgesCount[i] % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}
