package Code.GraphMeasurements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeasurementsGraph {
    public static void main(String[] args) {
//        List<Edge> edges = Arrays.asList(
//                new Edge(0,1),new Edge(0,4),
//                new Edge(1,0),new Edge(1,4),new Edge(1,3),new Edge(1,2),
//                new Edge(2,1),new Edge(2,3),
//                new Edge(3,1),new Edge(3,2),new Edge(3,4),
//                new Edge(4,0),new Edge(4,1),new Edge(4,3)
//        );
//        int vertex = 5;

//        List<Edge> edges = Arrays.asList(
//                new Edge(0,2),
//                new Edge(1,2),
//                new Edge(2,0),new Edge(2,1),new Edge(2,3),
//                new Edge(3,2),new Edge(3,4),
//                new Edge(4,3),new Edge(4,5),new Edge(4,6),
//                new Edge(5,4),
//                new Edge(6,4),new Edge(6,7),
//                new Edge(7,6)
//        );
//        int vertex = 8;

        List<Edge> edges = Arrays.asList(
                new Edge(0,1),new Edge(0,6),
                new Edge(1,0),new Edge(1,2),new Edge(1,3),new Edge(1,6),
                new Edge(2,1),new Edge(2,3),new Edge(2,6),new Edge(2,5),
                new Edge(3,1),new Edge(3,2),new Edge(3,9),
                new Edge(4,5),new Edge(4,9),
                new Edge(5,2),new Edge(5,4),new Edge(5,9),
                new Edge(6,0),new Edge(6,1),new Edge(6,2),new Edge(6,7),
                new Edge(7,6),new Edge(7,8),
                new Edge(8,7),new Edge(8,9),
                new Edge(9,3),new Edge(9,4),new Edge(9,5),new Edge(9,8)
        );
        int vertex = 10;

        Graph graph = new Graph(edges,vertex);
        graph.printMatrix();
        findAllPath(graph,0);
        findResult(graph);
    }
    //find the result
    public static void findResult(Graph graph){
        //ues array to say the max value of every row
        List<Integer> maxs = new ArrayList<>();
        for(int x = 0; x < graph.vertex; x++) {
            int max = 0;
            for(int y = 0; y < graph.vertex; y++){
                System.out.print(graph.path[x][y]);
                if(max < graph.path[x][y]){max = graph.path[x][y];}
            }
            maxs.add(max);
            System.out.print("  "+max);
            System.out.println();
        }
        //the min value in the array is the radius
        int min = maxs.get(0);
        for(int x : maxs){
            if (x < min){
                min = x;
            }
        }
        System.out.println("The radius is " + min);
        //the max value in the array is the diameter
        int max = maxs.get(0);
        for(int x : maxs){
            if (x > max){
                max = x;
            }
        }
        System.out.println("The diameter is " + max);
    }
    //use the findpath function for every vertex
    public static void findAllPath(Graph graph,int vertex){
        //if all the vertex did the finish the function
        if(graph.vertex == vertex){return;}
        //create the list to save the vertex
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        //add the start vertex in the list
        adjList.get(0).add(vertex);
        graph.findPath(adjList);
        print(adjList);
        graph.setPath(adjList);
        graph.printPath();
        vertex++;
        findAllPath(graph,vertex);
    }
    //print the list
    public static void print(List<List<Integer>> adjList){
        for (List<Integer> integers : adjList) {
            for (Integer integer : integers) {
                System.out.print(integer);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

}

class Graph {
    int[][] matrix;
    int[][] path;
    int vertex;
    Graph(List<Edge> edges, int vertex) {
        //this to save the total vertex
        this.vertex = vertex;
        //this to save the graph
        this.matrix = new int[vertex][vertex];
        //this to save the path
        this.path = new int[vertex][vertex];
        for (int x = 0; x < vertex; x++){
            for (int y = 0; y < vertex; y++){
                this.matrix[x][y] = 0;
            }
        }
        for (int x = 0; x < vertex; x++){
            for (int y = 0; y < vertex; y++){
                this.path[x][y] = 0;
            }
        }
        for (Edge edge: edges){
            this.matrix[edge.start][edge.dest]=1;
        }
    }
    //this function is to find the path
    public void findPath(List<List<Integer>> adjList){
        //if list has all the vertex then finish
        if (checkFinish(adjList)){return;}
        //find lastest saved vertex
        List<Integer> start = adjList.get(adjList.size()-1);
        //save next vertex into new list
        int current = adjList.size();
        //create new list to save the next vertex
        adjList.add(new ArrayList<>());
        //find the next and put into list
        for (int x : start){
            for (int y = 0;y < this.vertex;y++){
                if(this.matrix[x][y] == 1){
                    //check the vertex not alread in list
                    if(checkRepeat(adjList,y)){
                        adjList.get(current).add(y);
                    }
                }
            }
        }
        findPath(adjList);
    }
    //put the vertex in the list in to the path matrix
    public void setPath(List<List<Integer>> adjList){
        //find the column number
        int current = adjList.get(0).get(0);
        //put the vertex in to the path matrix
        for(int x = 1; x < adjList.size();x++){
            for (int y = 0; y < adjList.get(x).size();y++){
                this.path[current][adjList.get(x).get(y)] = x;
            }
        }
    }

    //this function is to check the list already has all the vertex or not?
    public boolean checkFinish(List<List<Integer>> adjList){
        int count = 0;
        for (List<Integer> integers : adjList) {
            for (int y = 0; y < integers.size(); y++) {
                count++;
            }
        }
        return count == this.vertex;
    }
    //check the vertex is already in the list or not
    public boolean checkRepeat(List<List<Integer>> adjList,int vertex){
        for (List<Integer> integers : adjList) {
            for (Integer integer : integers) {
                if (integer == vertex) {
                    return false;
                }
            }
        }
        return true;

    }
    //print the matrix
    public void printMatrix(){
        for(int x = 0; x < this.vertex; x++) {
            for(int y = 0; y < this.vertex; y++){
                System.out.print(this.matrix[x][y]);
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
    //print the path matrix
    public void printPath(){
        for(int x = 0; x < this.vertex; x++) {
            for(int y = 0; y < this.vertex; y++){
                System.out.print(this.path[x][y]);
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