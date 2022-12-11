package Code.Bipartite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bipartite {

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 2), new Edge(0, 3),
                new Edge(1, 3), new Edge(1, 4),
                new Edge(2, 0), new Edge(2, 4),
                new Edge(3, 0), new Edge(3, 1),
                new Edge(4, 1), new Edge(4, 2)
        );
        int n = 5;
        List<Edge> edges2 = Arrays.asList(
                new Edge(0,1),new Edge(0,7),new Edge(0,4),
                new Edge(1,0),new Edge(1,2),new Edge(1,3),
                new Edge(2,1),
                new Edge(3,1),
                new Edge(4,0),new Edge(4,5),new Edge(4,6),
                new Edge(5,4),
                new Edge(6,4),
                new Edge(7,0)
        );
        int n2 = 8;
                List<Edge> edges3 = Arrays.asList(
                new Edge(0,1),new Edge(0,2),new Edge(0,3),new Edge(0,4),
                new Edge(1,0),new Edge(1,6),
                new Edge(2,0),
                new Edge(3,0),
                new Edge(4,0),new Edge(4,5),
                new Edge(5,4),new Edge(5,6),
                new Edge(6,1),new Edge(6,5)
        );
        int n3 = 7;
         //construct graph
        bipartiteGarph graph = new bipartiteGarph(edges3, n3);
        graph.print();
        boolean[] visited = new boolean[graph.vertex];
        if(!isConnected(graph, 0, visited)){
            System.out.println("This graph is not connect, so is not bipartite.");
        }
        else {
            try{
                coloring(graph, graph,0,0);
                System.out.println("This graph is bipartite");
            }catch (Exception e){
                System.out.println("Graph is not bipartite");
            }

        }
    }
    public static void coloring(bipartiteGarph garph, bipartiteGarph changeGraph,int current,int color) throws Exception {


        if(garph.color[current][1] == -1){
            garph.color[current][1] = color;
        }

        if(garph.color[current][1] != color){
            System.out.println("The color on the vertex is " + garph.color[current][0]);
            print(garph);
            throw new Exception();
        }

        int adjecent = checkAdjecent(garph,current,color);
        if(adjecent != -1){
            System.out.println("The adjecent vertex is " + adjecent);
            System.out.println("The color is " + garph.color[adjecent][0]);
            print(garph);
            throw new Exception();
        }


        System.out.println("Current vertex is " + current);
        System.out.println("The color is " + color);
        print(garph);

        List<Integer> nextVertex = new ArrayList<>();
        for (int y = 0; y < changeGraph.vertex; y++) {
            if (changeGraph.matrix[current][y] == 1) {
                nextVertex.add(y);
            }

        }

        for(int x : nextVertex){
            change(changeGraph,current,x);
            if(color == 0){
                color = 1;
            }
            else {
                color = 0;
            }
            coloring(garph,changeGraph,x,color);
        }
    }
    public static void change(bipartiteGarph changeGraph, int current, int next){
        changeGraph.matrix[current][next] = 0;
        changeGraph.matrix[next][current] = 0;
    }

    public static int checkAdjecent(bipartiteGarph garph, int current, int color){
        int adjecent = -1;
        for(int y = 0; y < garph.vertex; y++){
            if(garph.matrix[current][y] == 1 && garph.color[y][1] == color){
                return y;
            }
        }
        return adjecent;
    }


    //by use Depth First Search reference from assignment 1
    public static boolean isConnected(bipartiteGarph graph, int currentVertex, boolean[] visited) {
        //mark the current as true visited
        visited[currentVertex] = true;
        //look for the vertex link to current vertex
        List<Integer> nextVertex = new ArrayList<>();
        for (int y = 0; y < graph.vertex; y++) {
            nextVertex.add(y);
        }
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

    public static void print(bipartiteGarph graph){
        for (int x = 0; x < graph.vertex; x++){
            System.out.print(graph.color[x][0]);
        }
        System.out.println();
        for (int x = 0; x < graph.vertex; x++){
            System.out.print(graph.color[x][1]);
        }
        System.out.println();
        System.out.println("-----------------------------");
    }
}
class bipartiteGarph {
    int[][] color;
    int[][] matrix;
    int vertex;
    bipartiteGarph(List<Edge> edges, int vertex) {
        this.vertex = vertex;
        this.color = new int[vertex][2];
        for (int x = 0; x < vertex; x++){
            this.color[x][0] = x;
        }
        for (int x = 0; x < vertex; x++){
            this.color[x][1] = -1;
        }
        this.matrix = new int[vertex][vertex];
        for (int x = 0; x < vertex; x++){
            for (int y = 0; y < vertex; y++){
                this.matrix[x][y] = 0;
            }
        }
        for (Edge edge: edges)
        {
            this.matrix[edge.start][edge.dest]=1;
        }
    }
    public void print(){
        for(int x = 0; x < this.vertex; x++) {
            for(int y = 0; y < this.vertex; y++){
                System.out.print(this.matrix[x][y]);
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
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