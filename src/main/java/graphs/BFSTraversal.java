package graphs;

import java.util.*;

public class BFSTraversal {
    static ArrayList<Integer> bfs_traversal(Integer n, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<Integer> result = new ArrayList<>();

        //build graph - create adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        createAdjList(n, edges, adjList);

        int[] visited = new int[n];

        //outer loop
        for(int i=0; i<n; i++) {
            if (visited[i] != 1) {
                bfsHelper(i, adjList, result, visited);
            }
        }

        return result;
    }

    //create adjacency list
    static void createAdjList(Integer n, ArrayList<ArrayList<Integer>> edges, ArrayList<ArrayList<Integer>> adjList) {
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
    }

    static void bfsHelper(int i, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> result, int[] visited) {
        visited[i] = 1;
        result.add(i);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            //get the neighbors
            for (int neighbor: adjList.get(u)) {
                if (visited[neighbor] != 1) {
                    result.add(neighbor);
                    visited[neighbor] = 1;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<Integer>(Arrays.asList(0,1)));
        edges.add(new ArrayList<Integer>(Arrays.asList(0,2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(0,4)));
        edges.add(new ArrayList<Integer>(Arrays.asList(2,3)));

        bfs_traversal(6, edges);
    }
}
