
# Warshall Algortihm

## what is warshall algortihm
Recurrence relating elements R(k) to elements of R(k-1) is:

    R(k)[i,j] = R(k-1)[i,j] or (R(k-1)[i,k] and R(k-1)[k,j])

It implies the following rules for generating R(k) from R(k-1):

    1. If an element in row i and column j is 1 in R(k-1),it remains 1 in R(k)
    2.  If an element in row i and column j is 0 in R(k-1),it has to be changed
         to 1 in R(k) it has to be changed to 1 in R if and only if (k) if and 
         only if the element in its row i and column k and the element in its 
         column j and row k are both 1’s in R(k-1)

## How to use warshall algortihm
![Howtouse](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/WarshallAlgortihm/howtouse.png)

## See Code

[Java Code](https://github.com/MeloShen/Graph-Theory-Programs/blob/main/Code/WarshallAlgortihm/WarshallAlgortihm.java)

## Outcome

### Outcome 1
![Outcome1](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/WarshallAlgortihm/outcome1.png)

### Outcome 2
![Outcome2](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/WarshallAlgortihm/outcome2.png)