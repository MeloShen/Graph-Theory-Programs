
# Sloving Knapsack Problem 
![Knapsack Problem](https://www.mathworks.com/matlabcentral/mlc-downloads/downloads/646469c8-1e39-429d-bc65-adea32a5d053/584e3fbb-9dfe-4503-b3aa-1ff63900c5bb/images/screenshot.png)

## What is knapsack problem 
The knapsack problem is a problem in combinatorial optimization.

## How to understand 
    1. Set of items, each with a weight and a value.
    2. A fixed capacity.
    3. Find a combination.
    4. The total weight is less than or equal to a given limit.
    5. The total value is as large as possible. 

## Dynamic programming
Consider how many bags there are and the remaining weight that the bag can store. 

## Easy problem
    WEIGHT: W = {3,4,5,6}
    VALUE:  V = {2,6,4,6}
    CAPACITY = 8
    ITEM = 4

## Sloving

[Java Code](https://github.com/MeloShen/Graph-Theory-Programs/blob/main/Code/KnapsackProblem/Knapsack_DP.java)

If the call to B[i][j] is by selecting the largest possible value in the bag {1, 2, ..., i} with weight limit j. The maximum value is B[n][M] when selected among n packs with a weight limit of M.

### For the B[i][j] has these selections
In the case of simply having only 1 package to choose. You calculate B[1][j] for every j: which means the maximum weight of the knapsack ≥ the weight of the 1st package:

    B[1][j] = W[1]

If package i is not selected, B[i][j] is the maximum possible value by selecting among packages {1, 2, …, i – 1} with weight limit of j. You have:

    B[i][j] = B[i – 1][j]

If package i is selected (of course only consider this case when W[i] ≤ j) then B[i][j] is equal to the value V[i] of package i plus the maximum value can be obtained by selecting among packages {1, 2, …, i – 1} with weight limit (j – W[i]). That is, in terms of the value you have:

    B[i][j] = V[i] + B[i – 1][j – W[i]]

Now we have the recursive formula as follows:

    B[i][j]= max(B[i – 1][j], V[i]+B[i – 1][j – W[i]]

### Build an option table from the above recursive formula

![Knapsack Problem image 1](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/KnapsackProblem/image1.png)

![Knapsack Problem image 2](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/KnapsackProblem/image2.png)

![Knapsack Problem image 3](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/KnapsackProblem/image3.png)

![Knapsack Problem image 3](https://raw.githubusercontent.com/MeloShen/Graph-Theory-Programs/main/image/KnapsackProblem/image4.png)

### How to get the answer

#### Steps for tracing:
    1.Starting from i = n, j = M 
    2.Look in column j, up from bottom, you find the line i such that B[i][j] > B[i – 1][j]
    3.j = B[i][j] – W[i]. If j > 0, go to step 2, otherwise go to step 4
    4.Based on the table of options to print the selected packages. 
    
### In the given case can get 
    1. I = 4 j =8, m[4][8] = 8
    2. based on step 2 can get b[2][8] > b[2-1][8], so I =2. 
    3. by step 3 can get j = b[2][8] - w[2] = 8 – 4 = 4.
    4. The item has weight 4 with value 6. 
    5. Now the I = 2 and j = 4.
    6. Next do the step 2 b[1][4] > b[0][4], I = 1, j = b[1][4] – w[1] = 2 – 2 = 0.
    7. the item is weight 3 with value 2. 

### Answer
    Item 1: weight 3, value 2
    Item 2: weight 4, value 6
    Total weight = 7
    total value = 8.

## References

[0-1 Knapsack Problem. (2021, 11 09). ](https://www.interviewbit.com/blog/0-1-knapsack-problem/)

[Knapsack problem. (2022, 05 18). ]( https://en.wikipedia.org/wiki/Knapsack_problem)

[ 0/1 Knapsack Problem Fix using Dynamic Programming Example.](https://www.guru99.com/)




