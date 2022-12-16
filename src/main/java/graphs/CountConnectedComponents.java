package graphs;

import java.util.ArrayList;
import java.util.Stack;

public class CountConnectedComponents {
    static Integer number_of_connected_components(Integer n, ArrayList<ArrayList<Integer>> edges) {
        //build graph - create adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        createAdjList(n, edges, adjList);

        int[] visited = new int[n];
        int connectedComponents = 0;
        //outer loop
        for(int i=0; i<n; i++) {
            if (visited[i] != 1) {
                connectedComponents++;
                dfsHelper(i, adjList, visited);
            }
        }

        return connectedComponents;
    }

    //create adjacency list
    static void createAdjList(Integer n, ArrayList<ArrayList<Integer>> edges, ArrayList<ArrayList<Integer>> adjList) {
        //initialize adjlist
        for(int i=0; i< n; i++) { //O(n) //n is no. vertices
            adjList.add(new ArrayList<>());
        }
        //adjacency list
        for (ArrayList<Integer> edge: edges) { // O(m) m is no of edges
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
    }

    static void dfsHelper(int i, ArrayList<ArrayList<Integer>> adjList, int[] visited) {
        visited[i] = 1;
        Stack<Integer> stack = new Stack<>();
        stack.add(i);
        while(!stack.isEmpty()) {
            int u = stack.pop();
            //get neighbors
            for (int neighbor: adjList.get(u)) {
                if (visited[neighbor] != 1) {
                    dfsHelper(neighbor, adjList, visited);
                }
            }
        }
    }
}
