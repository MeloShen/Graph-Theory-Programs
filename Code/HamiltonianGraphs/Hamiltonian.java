package Code.HamiltonianGraphs;

import java.util.Arrays;
import java.util.List;

public class Hamiltonian {
    public static void main(String[] args) {
        // List of graph edges as per the above diagram
        // it need to put all the vertex
        List<Edge> edges = Arrays.asList(
                new Edge(0,4),new Edge(0,7),new Edge(0,6),
                new Edge(1,2),new Edge(1,3),new Edge(1,6),
                new Edge(2,1),new Edge(2,6),new Edge(3,3),new Edge(2,7),
                new Edge(3,2),new Edge(3,1),new Edge(3,5),new Edge(3,4),
                new Edge(4,3),new Edge(4,7),new Edge(4,0),new Edge(4,5),
                new Edge(5,4),new Edge(5,3),new Edge(5,0),new Edge(5,6),
                new Edge(6,0),new Edge(6,1),new Edge(6,2),new Edge(6,5),
                new Edge(7,1),new Edge(7,0),new Edge(7,2),new Edge(7,4)
        );
        // total number of edges in the graph
        int vertex = 8;

        // construct graph
        HamiltonianGraph graph = new HamiltonianGraph(edges, vertex);
        printMatrix(graph,vertex);
        DoubleLinkedList path = new DoubleLinkedList<>();


        findMaxPath(path,edges);
        findHamiltonCycle(path,edges);
    }
    //This method is used to print the matrix in the graph
    public static void printMatrix(HamiltonianGraph graph, int vertex){
        System.out.println("---------------");
        for (int x = 0; x < vertex; x++){
            for(int y = 0; y < vertex; y++){
                System.out.print(graph.array[x][y]);
            }
            System.out.println();
        }
        System.out.println("---------------");
    }
    //This function is used to check the graph has hamilton cycle or not
    public static void  findHamiltonCycle(DoubleLinkedList path,List<Edge> edges){
        int head = (int)path.findHead();
        int tail = (int)path.findTail();
        //this is to chake if the first is connect to last
        for (Edge edge:edges) {
            if (head == edge.start && tail == edge.dest) {
                path.addHead(tail);
                System.out.println("It has hamiltion cycle");
                path.print();
                return;
            }
        }
        //check the other is followed by theorem 4.1
        //use the function "hamiltonCycle" below
        if (hamiltonCycle(head,tail,edges,path) == -1){
            System.out.println("There is no hamiltion cycle");
            return;
        }
        else{
            System.out.println("It has hamiltion cycle");
            int vertices = hamiltonCycle(head,tail,edges,path);
            //change head is use to find hamilton cycle
            path.changeHead(vertices);
        }
    }
    //this method is used find which node is connect to the head and return the vertex
    public static int hamiltonCycle(int head, int tail, List<Edge> edges, DoubleLinkedList path){
        int prev = (int) path.findPrev(tail);
        int prev_prev = (int)path.findPrev(prev);
        for(Edge edge:edges){
            //need to sure head vertex connect a vertex in the list
            //the adjcent prev vertex of the connected vertex
            //is connect to tail
            if(edge.start == prev_prev && edge.dest == tail){
                for(Edge edge1 : edges){
                    if(edge1.start == prev && edge1.dest == head){
                        return prev;
                    }
                }
            }
        }
        //esle it means no hamilton cycle
        return -1;
    }
    //this function is use to fing the maximum path
    public static void findMaxPath(DoubleLinkedList path,List<Edge> edges){
        path.addHead(edges.get(0).start);
        path.addTail(edges.get(0).dest);
        //find the maximum for head side and tail side
        insertHead(edges,path);
        insertTail(edges,path);
    }
    //insert the node from head
    public static void insertHead(List<Edge> edges, DoubleLinkedList path){
        System.out.println("Looking for max path for head.");
        path.print();
        //find current which node 'head' pointed
        int currentVertex = (int) path.findHead();
        for (Edge edge:edges){
            //make sure the vertex not already in the list
            if(currentVertex == edge.start && !path.has(edge.dest) ){
                path.addHead(edge.dest);
                insertHead(edges,path);
            }
        }
    }
    //insert the node from head
    public static void insertTail(List<Edge> edges, DoubleLinkedList path){
        System.out.println("Looking for max path for tail.");
        path.print();
        //find current which node 'tail' pointed
        int currentVertex = (int) path.findTail();
        for (Edge edge:edges){
            //make sure the vertex not already in the list
            if(currentVertex == edge.start && !path.has(edge.dest) ){
                path.addTail(edge.dest);
                insertTail(edges,path);
            }
        }
    }
}
class HamiltonianGraph{
    int array[][];
    // A list of lists to represent an array
    // Constructor
    HamiltonianGraph(List<Edge> edges, int n)
    {
        array = new int[n][n];
        for (int x = 0; x < n; x++){
            for (int y = 0; y < n; y++){
                array[x][y] = 0;
            }
        }
        for (Edge edge: edges)
        {
            array[edge.start][edge.dest]=1;
        }
    }
}
class Edge {
    int start, dest;
    public Edge(int source, int end)
    {
        this.start = source;
        this.dest = end;
    }
}