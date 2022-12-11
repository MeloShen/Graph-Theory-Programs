
# Eulerian Graph

## what is Eulerian
We call a circuit C in multigraph G = (V, E, φ) an Euler circuit
if every edge e in E appears in C (exactly once). If G contains an Euler circuit we say
G is Eulerian. Let u and v be distinct vertices. We call a u-vtrail T in G = (V, E, φ)
an Euler trail if every edge e in E appears in T (exactly once).

## How to identify Eulerian
    1. A multigraph G = (V, E, φ) is Eulerian iff it is connected and contains
        no vertices of odd degree.
    2. A multigraph contains an Euler trail iff it is connected and has exactly
        two vertices of odd degree.

## See Code

[Java Code](https://github.com/MeloShen/Graph-Theory-Programs/blob/main/Code/EulerianGraph/Eulerian.java)

## Outcome

![Outcome](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/EulerianGraph/outcome.png)