package Code.WarshallAlgortihm;

import java.util.Arrays;
import java.util.List;
public class WarshallAlgortihm {
    public static void main(String[] args) {
        // List of graph edges as per the above diagram
        // it need to put all the vertex
        List<WarshallEdge> edges = Arrays.asList(
                new WarshallEdge(1,3),new WarshallEdge(1,5),
                new WarshallEdge(2,4),new WarshallEdge(2,6),
                new WarshallEdge(3,1),new WarshallEdge(3,5),
                new WarshallEdge(4,2),new WarshallEdge(4,6),
                new WarshallEdge(5,3),new WarshallEdge(5,1),
                new WarshallEdge(6,2),new WarshallEdge(6,4)
        );
        // total number of edges in the graph
        int vertex = 7;
        //use for the start point in the findMatrix function
        int n = vertex;
        // construct graph
        WarshallGraph graph = new WarshallGraph(edges, vertex);
        warshallAlgortihm(graph,vertex,n);
    }
    //This method is used to print the matrix in the graph
    public static void printMatrix(WarshallGraph graph,int vertex){
        System.out.println("---------------");
        for (int x = 0; x < vertex; x++){
            for(int y = 0; y < vertex; y++){
                System.out.print(graph.array[x][y]);
            }
            System.out.println();
        }
        System.out.println("---------------");
    }
    //doing the warshall's Algorthm
    public static void warshallAlgortihm(WarshallGraph graph,int vertex, int n){
        printMatrix(graph,vertex);
        if(n == 1){
            if(checkMatrix(graph,vertex)){
                System.out.println("Graph is connected.");
            }
            else{
                System.out.println("Graph is not connected.");
            }
            return;
        }
        int Matrix[][] = findMatrix(graph,vertex,n);
        WarshallGraph newGraph = orGate(graph,Matrix,vertex);
        n--;
        warshallAlgortihm(newGraph,vertex,n);
    }
    //find the matrix use for the function
    public static int[][] findMatrix(WarshallGraph graph,int vertex, int n){
        int array[][] = new int[vertex][vertex];
        for (int x = 0; x < vertex; x++){
            if (graph.array[x][vertex-n] == 0){
                for (int y = 0; y < vertex; y++){
                    array[x][y] = 0;
                }
            }
            else {
                for (int y = 0; y < vertex; y++){
                    array[x][y] = graph.array[vertex-n][y];
                }
            }
        }
        return array;
    }
    //Do the or gate
    public static WarshallGraph orGate(WarshallGraph graph, int[][] array , int vertex){
        for (int x = 0; x < vertex; x++){
            for (int y = 0; y < vertex; y++){
                if(array[x][y] == 1){graph.array[x][y] = 1;}
            }
        }
        return graph;
    }
    //check the matrix still has o or not
    public static boolean checkMatrix(WarshallGraph graph, int vertex){
        for (int x = 0; x < vertex; x++){
            for (int y = 0; y < vertex; y++){
                if(graph.array[x][y] == 0){return false;}
            }
        }
        return true;
    }
}

class WarshallGraph{
    int array[][];
    // A list of lists to represent an array
    // Constructor
    WarshallGraph(List<WarshallEdge> edges, int n)
    {
        array = new int[n][n];
        for (int x = 0; x < n; x++){
            for (int y = 0; y < n; y++){
                array[x][y] = 0;
            }
        }
        for (WarshallEdge edge: edges)
        {
            array[edge.start][edge.dest]=1;
        }
    }
}

class WarshallEdge {
    int start, dest;
    public WarshallEdge(int source, int end)
    {
        this.start = source;
        this.dest = end;
    }
}