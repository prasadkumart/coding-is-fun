package graphs;

import java.util.*;

public class IsItATree {
    static Boolean is_it_a_tree(Integer node_count, ArrayList<Integer> edge_start, ArrayList<Integer> edge_end) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        createGraph(node_count, edge_start, edge_end, adjList);

        int connectedComps = 0;
        int[] visited = new int[node_count];
        int[] parent = new int[node_count];
        for(int i=0; i<node_count; i++) {
            parent[i] = -1;
        }
        for(int i=0; i<node_count; i++) {
            if (visited[i] != 1) { //not visited
                connectedComps++;
                if (connectedComps>1) {
                    return false;
                }
                if (bfsHelper(i, visited, parent, adjList)) {
                    return false;
                }
            }
        }
        return true;
    }

    static void createGraph(Integer node_count, ArrayList<Integer> edge_start, ArrayList<Integer> edge_end, ArrayList<ArrayList<Integer>> adjList) {
        //initializing array list to avoid NPE
        for (int i=0; i<node_count; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i=0; i<edge_start.size(); i++) {
            ArrayList<Integer> extList = adjList.get(edge_start.get(i));
            extList.add(edge_end.get(i));
            adjList.set(edge_start.get(i), extList);

            extList = adjList.get(edge_end.get(i));
            extList.add(edge_start.get(i));
            adjList.set(edge_end.get(i), extList);
        }
    }

    static boolean bfsHelper(int i, int[] visited, int[] parent, ArrayList<ArrayList<Integer>> adjList) {
        visited[i] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(i);

        while(!q.isEmpty()) {
            Integer node = q.poll();
            for(Integer neighbor : adjList.get(node)) { //visit all neighbors
                if (visited[neighbor] != 1) { //tree edge
                    parent[neighbor] = node;
                    visited[neighbor] = 1;
                    q.add(neighbor);
                } else {
                    if (neighbor != parent[node]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean dfsHelper(int i, int[] visited, int[] parent, ArrayList<ArrayList<Integer>> adjList) {
        visited[i] = 1;

        for(Integer neighbor : adjList.get(i)) { //visit all neighbors
            if (visited[neighbor] != 1) { //tree edge
                parent[neighbor] = i;
                if (dfsHelper(neighbor, visited, parent, adjList)){
                    return false;
                }
            } else {
                if (neighbor != parent[i]) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        is_it_a_tree(4, new ArrayList<>(Arrays.asList(0,0,0,0)), new ArrayList<>(Arrays.asList(1,2,3,0)));
    }
}
