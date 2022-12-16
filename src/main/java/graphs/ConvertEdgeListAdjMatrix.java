package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ConvertEdgeListAdjMatrix {


    static ArrayList<ArrayList<Boolean>> convert_edge_list_to_adjacency_matrix(Integer n, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Boolean>> adjMatrix = new ArrayList<>();
        //initialize adjlist
        for(int i=0; i< n; i++) {
            ArrayList<Boolean> booleanArrayList = new ArrayList<>(Arrays.asList(new Boolean[n]));
            Collections.fill(booleanArrayList, Boolean.FALSE);
            adjMatrix.add(booleanArrayList);
        }
        //adjacency list
        for (ArrayList<Integer> edge: edges) {
            ArrayList<Boolean> exitList = adjMatrix.get(edge.get(0));
            exitList.set(edge.get(1), Boolean.TRUE);
            adjMatrix.set(edge.get(0), exitList);

            //for undirected
            exitList = adjMatrix.get(edge.get(1));
            exitList.set(edge.get(0), Boolean.TRUE);
            adjMatrix.set(edge.get(1), exitList);
        }

        return adjMatrix;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<Integer>(Arrays.asList(0,1)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,4)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(3,4)));

        convert_edge_list_to_adjacency_matrix(5, edges);
    }
}
