package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ConvertEdgeListAdjList {

    /*
    Asymptotic complexity in terms of the number of vertices `n` and number of edges `e` in the graph.
    * Time: O(n * e log(e)).
    * Auxiliary space: O(1).
    * Total space: O(n + e).
    */
    static ArrayList<ArrayList<Integer>> convert_edge_list_to_adjacency_list(Integer n, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        //initialize adjlist
        for(int i=0; i< n; i++) {
            adjList.add(new ArrayList<>());
        }
        //adjacency list
        for (ArrayList<Integer> edge: edges) {
            ArrayList<Integer> exitList = adjList.get(edge.get(0));
            exitList.add(edge.get(1));
            //Collections.sort(exitList); // e*log(e); e is no of edges
            adjList.set(edge.get(0), exitList);

            //for undirected
            exitList = adjList.get(edge.get(1));
            exitList.add(edge.get(0));
            //Collections.sort(exitList);
            adjList.set(edge.get(1), exitList);
        }

        for(int i=0; i< n; i++) {
            ArrayList<Integer> exitList = adjList.get(i);
            if (exitList != null || exitList.size()>0) {
                Collections.sort(exitList); // e*log(e); e is no of edges
                adjList.set(i, exitList);
            }
        }

        return adjList;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<Integer>(Arrays.asList(0,1)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,4)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(3,4)));

        convert_edge_list_to_adjacency_list(5, edges);
    }
}
