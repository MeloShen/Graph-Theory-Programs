package Code.EulerianGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Eulerian {
    public static void main(String[] args)
    {
        // List of graph edges as per the above diagram
        // it need to put all the vertex
        List<Edge> edges = Arrays.asList(
                new Edge(0, 2), new Edge(0, 3),new Edge(0,4),
                new Edge(1, 3), new Edge(1, 4),
                new Edge(2, 0), new Edge(2, 4),
                new Edge(3, 0), new Edge(3, 1),
                new Edge(4, 1), new Edge(4, 2),new Edge(4,0)
        );
        // total number of edges in the graph
        int n = 5;
        // construct graph
        EulerianGraph graph = new EulerianGraph(edges, n);
        // check if the graph is connected or not and
        // if graph is not connected then it does not have Euler Circuits
        boolean[] visited = new boolean[n];
        for( int x = 0; x < n; x++){visited[x] = false;}
        if (isConnected(graph,0, visited)) {
            System.out.println("The graph is connected");
            if(isEulerian(graph, n)==0||isEulerian(graph,n) == 2){
                //start the main function
                List<Integer> path = new ArrayList<>();
                if(findOddDegree(graph,n).size()==0){
                    System.out.println("The graph have Euler Circuits");
                    Find(graph,edges.get(0).start,path);
                }
                else{
                    System.out.println("The graph have Euler path");
                    Find(graph,findOddDegree(graph,n).get(0),path,findOddDegree(graph,n).get(1));                }
            }
            else {System.out.println("The graph does not have Euler Circuits");}
        }
        else {
            System.out.println("The graph is not connected");
            System.out.println("The graph does not have Euler Circuits");
        }
    }
    //by use Depth First Search reference from
    //https://www.hackerearth.com/practice/algorithms/graphs/depth-first-search/tutorial/
    public static boolean isConnected(EulerianGraph graph, int currentVertex,boolean[] visited)
    {
        //mark the current as true visited
        visited[currentVertex] = true;
        //look for the vertex link to current vertex
        List<Integer> nextVertex = new ArrayList<>(graph.adjList.get(currentVertex));
        //travle all adjacent vertex
        for (Integer vertex : nextVertex) {
            if (!visited[vertex]) {
                isConnected(graph, vertex, visited);
            }
        }
        //check the list if still have false then the graph is not connect
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
    // check the graph has euler circuits or not
    public static int isEulerian(EulerianGraph graph, int n){
        // to count how many odd degree
        int  OddDegree = 0;
        for (int i = 0; i < n; i++){
            // the degree mod 2 to check
            if(graph.adjList.get(i).size()%2!=0){OddDegree++;}
        }
        //if odd degree is 2 or zero has euler circuits
        return OddDegree;
        // otherwise it does not has euler circuits
    }
    // this function is use to find the euler circuits
    public static void Find(EulerianGraph graph,int currentVertex,List<Integer> path){
        path.add(currentVertex);
        //check the next vertex is exist or not
        if (graph.adjList.get(currentVertex).size() != 0 ){
            //find the next vertex
            int nextVertex = graph.adjList.get(currentVertex).get(0);
            Find(copyGraph(graph,currentVertex,nextVertex),nextVertex,path);
        }
        else{System.out.println(path);}
    }
    // this function is use to find the euler path and the odd degree is 2
    public static void Find(EulerianGraph graph,int currentVertex,List<Integer> path,int lastVertex){
        path.add(currentVertex);
        if (graph.adjList.get(lastVertex).size() == 1 && graph.adjList.get(currentVertex).size()>1){
            //check the next vertex is exist or not
            if (graph.adjList.get(currentVertex).size() != 0 ){
                //find the next vertex
                int nextVertex = graph.adjList.get(currentVertex).get(1);
                Find(copyGraph(graph,currentVertex,nextVertex),nextVertex,path);
            }
            else{System.out.println(path);}
        }
        else{
            //check the next vertex is exist or not
            if (graph.adjList.get(currentVertex).size() != 0 ){
                //find the next vertex
                int nextVertex = graph.adjList.get(currentVertex).get(0);
                Find(copyGraph(graph,currentVertex,nextVertex),nextVertex,path);
            }
            else{System.out.println(path);}
        }
    }
    //if graph has odd degree then need to find the
    //if all the degree is even then start from 0
    public static List<Integer> findOddDegree(EulerianGraph graph,int n){
        List<Integer> oddDegree = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if(graph.adjList.get(i).size()%2!=0){oddDegree.add(i);}
        }
        return oddDegree;
    }
    //cope the graph without walked edge
    public static EulerianGraph copyGraph(EulerianGraph graph,int currentVertex,int nextVertex){
        int flag = 0;
        for (int x = 0;x < graph.adjList.get(currentVertex).size();x++){
            if (nextVertex == graph.adjList.get(currentVertex).get(x)){
                graph.adjList.get(currentVertex).remove(flag);
            }
            else{flag++;}
        }
        flag = 0;
        for (int x = 0;x < graph.adjList.get(nextVertex).size();x++){
            if (currentVertex == graph.adjList.get(nextVertex).get(x)){
                graph.adjList.get(nextVertex).remove(flag);
            }
            else{flag++;}
        }
        return graph;
    }
}
class EulerianGraph{
    // A list of lists to represent an adjacency list
    List<List<Integer>> adjList;
    // Constructor
    EulerianGraph(List<Edge> edges, int n)
    {
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        // add edges to the directed graph
        for (Edge edge: edges)
        {
            int start = edge.start;
            int dest = edge.dest;

            adjList.get(start).add(dest);
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