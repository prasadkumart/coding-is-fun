package graphs;

import java.util.*;

public class DFSTraversal {
    static ArrayList<Integer> dfs_traversal(Integer n, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<Integer> result = new ArrayList<>();

        //build graph - create adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        createAdjList(n, edges, adjList);

        int[] visited = new int[n];

        //outer loop
        for(int i=0; i<n; i++) {
            if (visited[i] != 1) {
                dfsHelper(i, adjList, result, visited);
            }
        }

        return result;
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

    static void dfsHelper(int i, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> result, int[] visited) {
        visited[i] = 1;
        result.add(i);

        //Stack<Integer> stack = new Stack<>();
        //stack.add(i);

        //while (!stack.isEmpty()) {
          //  int u = stack.pop();
            //get the neighbors
            for (int neighbor: adjList.get(i)) { //O(degree(node) - worst case m//no of edges
                if (visited[neighbor] != 1) {
                    //visited[neighbor] = 1;
                    dfsHelper(neighbor, adjList, result, visited);
                }
            }
        //}
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<Integer>(Arrays.asList(0,1)));
        edges.add(new ArrayList<Integer>(Arrays.asList(0,2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,4)));
        edges.add(new ArrayList<Integer>(Arrays.asList(3,5)));

        dfs_traversal(6, edges);
    }
}
