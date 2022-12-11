
#  Bipartite Graph

## what is complete bipartite graph

The graph G = (V, E) is bipartite if it is connected and V may
be partitioned into 2 subsets V1 and V2 [so that V1 ∪ V2 = V and V1 ∩ V2 = ∅]
and each edge in E joins a vertex in V1 with a vertex in V2. The bipartite graph
G = (V1 ∪ V2, E), where V1 contains m elements and V2 contains n elements is called
a complete bipartite graph, Km,n, if each vertex in V1 is joined with every vertex
in V2 by an edge in E.

## How to identified is bipartite

    1. If the graph is not connect then is not bipartite.
    2. A Bipartite Graph is a graph whose vertices can be divided into 
        two independent sets, U and V such that every edge (u, v) either
        connects a vertex from U to V or a vertex from V to U.
    3. For short is can colored with two colour.

## See Code

[Java Code](https://github.com/MeloShen/Graph-Theory-Programs/blob/main/Code/Bipartite/Bipartite.java)

## input

![input](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/Bipartite/input.png)

## output

### output1
![output1](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/Bipartite/outpu1.png)

### output2
![output2](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/Bipartite/outpu2.png)

### output3
![output2](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/Bipartite/output3.png)