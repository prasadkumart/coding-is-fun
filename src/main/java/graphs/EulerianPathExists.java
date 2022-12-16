package graphs;

import java.util.ArrayList;
import java.util.List;

//in Eulerian Path, degrees of all nodes should be even or at most 2 nodes would have an odd degree
public class EulerianPathExists {

    /*
    Asymptotic complexity in terms of the number of vertices ( = `n`) and number of edges ( = `e`):
    * Time: O(n + e).
    * Auxiliary space: O(n).
    * Total space: O(n + e).
    */
    static Boolean check_if_eulerian_path_exists(Integer n, ArrayList<ArrayList<Integer>> edges) {
        List<Integer>[] adjList = new List[n];
        //adjacency list
        for (ArrayList<Integer> edge: edges) { //O(n+e) T
            if (null == adjList[edge.get(0)]) {
                adjList[edge.get(0)] = new ArrayList<>();
            }
            adjList[edge.get(0)].add(edge.get(1));
            //for undirected
            if (null == adjList[edge.get(1)]) {
                adjList[edge.get(1)] = new ArrayList<>();
            }
            adjList[edge.get(1)].add(edge.get(0));
        }
        int odd = 0;
        for(List neighbors: adjList) { //O(n)
            if (null != neighbors && neighbors.size() % 2 != 0) {
                odd++;
            }
        }

        if (odd == 0 || odd == 2) {
            return true;
        }

        return false;
    }
}
