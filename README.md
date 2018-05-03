# Information-Retrieval-HW-2
Page Rank

# The Convergence
The project achieved convergence between two matrixes by using Square Root Error(SQRE). If the SQRE between all elements in two matrixes are all less than a certain degree (a double value), the program will determine convergence is achieved. In this project, the defree is 0.00001. 

# Requirements
Graph: Your implementation can expect your graph to be submitted in adjacency matrix format, through a text file (call it graph.txt). This document will contain graph structure in matrix format, with three columns (I,j,k). Each row will denote information for a cell. e.g. a row of the form "i j k" denotes that the matrix contains a value k at the row i column j.  The value k=1 denotes that there is a link from document (node) i to document (node) j, else the value of k =0.    

Parameters: In your implementations use beta=0.85 as the dampening factor.

What to submit:
1) PDF document that will include answers to following questions:

(a) What is the output for Matrix M? Give the matrix. 

(b) What is the original rank vector (rj)?

(c) What is the Converged rank vector (R)?

(d) How many  iterations did it take to get the convergence?

2. GitHub link 
