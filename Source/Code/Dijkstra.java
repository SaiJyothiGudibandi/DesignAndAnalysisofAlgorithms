package com;

import java.util.Set;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;

class Data implements Comparable<Data> {
	public final int index;
	public final double priority;

	public Data(int index,double distance) {
		this.index = index;
		this.priority = distance;
	}

	@Override
	public int compareTo(Data other) {
		return Double.valueOf(priority).compareTo(other.priority);
	}
	
	public boolean equals(Data other) {
		return priority == other.priority;
	}
}

public class Dijkstra{
	static int hopLength=0;
	static double finalMean = 0.0;
	static double finalStandardDeviation = 0.0;
	static double finalCoefficientSquare = 0.0;
	
	public static double dijkstra(double[][] Graph,ObjectData[][] newGraph, int i, int j,String method){
		hopLength =0;
		finalMean = 0.0;
		finalStandardDeviation = 0.0;
		finalCoefficientSquare = 0.0;
		//Get the number of vertices in G
		int n = Graph.length;
		int parent[]=new int[Graph.length];
		
		double[] distance = new double[Graph.length];
		PriorityQueue<Data> PQ = new PriorityQueue<Data>();
		boolean[] inTree = new boolean[Graph.length];
		for (int index = 0; index < Graph.length; index++) {
			if (index == i) {
				distance[index] = 0;
			}
			else {
				distance[index] = Double.MAX_VALUE;
				PQ.add(new Data(index,distance[index]));
				inTree[index] = true; 
			}
		}
		
		for (int index = 0; index < Graph.length; index++) { // for each edge (v,z) do
			if (Graph[i][index] != -1.0) { // There is an edge
				if (distance[i] + Graph[i][index] < distance[index]) { // if D[v] + w((v,z)) < D[z] then 
					double oldIndex = distance[index];
					parent[index]=i;
					distance[index] = distance[i] + Graph[i][index]; // D[z] â†� D[v] + w((v,z))  
					PQ.remove(new Data(index, oldIndex));
					PQ.add(new Data(index, distance[index])); // update PQ wrt D[z] 
				}
			}
		}
		
		while (PQ.peek() != null) { // If PQ isn't empty
			Data vertex = PQ.poll(); // RemoveMin
			for (int index = 0; index < Graph.length; index++) { // for each edge (u,z) with z âˆˆ PQ do
				if (Graph[vertex.index][index] != -1.0 && inTree[index] == true) { // z âˆˆ PQ
					if (distance[vertex.index] + Graph[vertex.index][index] < distance[index]) { // if D[v] + w((v,z)) < D[z] then
						parent[index]=vertex.index;
						double oldIndex = distance[index];
						distance[index] = distance[vertex.index] + Graph[vertex.index][index]; // D[z] â†� D[v] + w((v,z))
						PQ.remove(new Data(index, oldIndex));
						PQ.add(new Data(index, distance[index])); // update PQ wrt D[z] 
					}
				}		
			} 
		}
		System.out.println("\n"+method+" : ");
		System.out.println("Length of Shortest path from 1 to 12 using "+method + "Method : "+distance[j] );
		System.out.print("Path : ");
		printPath(parent, j, i);
		calculateFinalMean(parent,Graph,newGraph,j,i);
		calculateStandardDeviation(parent,Graph,newGraph,j,i);
		calculatefinalCoefficientSquare(parent,Graph,newGraph,j,i);
		System.out.println("\nμ - σ : "+ (finalMean - finalStandardDeviation));
		System.out.println("μ : "+finalMean);
		System.out.println("μ +  σ : "+ (finalMean + finalStandardDeviation));
		System.out.println("μ + 2σ : "+ (finalMean + (2 * finalStandardDeviation)));
		System.out.println("C^2 : " + finalCoefficientSquare);
		System.out.println("Hop Length : "+ hopLength);
		if (distance[j] == Integer.MAX_VALUE || distance[j] < 0) {
			return -1;
		}
		else {
			return distance[j];
		}
	}
	public static void calculatefinalCoefficientSquare(int parent[], double Graph[][],ObjectData[][] newGraph,int vertex, int startNode){
		 if (parent[vertex] == 0) {	// current vertex has no parent]
			 return;
		} else {	// go for the current vertex's parent
			finalCoefficientSquare = finalCoefficientSquare + newGraph[parent[vertex]][vertex].coefficientSquare;
			calculatefinalCoefficientSquare(parent,Graph,newGraph,parent[vertex], startNode);
	    }
	}
	public static void calculateStandardDeviation(int parent[], double Graph[][],ObjectData[][] newGraph,int vertex, int startNode){
		 if (parent[vertex] == 0) {	// current vertex has no parent]
			 return;
		} else {	// go for the current vertex's parent
			finalStandardDeviation = finalStandardDeviation + newGraph[parent[vertex]][vertex].b;
			calculateStandardDeviation(parent,Graph,newGraph,parent[vertex], startNode);
	    }
	}
	public static void calculateFinalMean(int parent[],double Graph[][],ObjectData newGraph[][],int vertex, int startNode){
		 if (parent[vertex] == 0) {	// current vertex has no parent
	    	return;
		} else {	// go for the current vertex's parent
			finalMean = finalMean + newGraph[parent[vertex]][vertex].a;
			calculateFinalMean(parent,Graph,newGraph,parent[vertex], startNode);
	    }
	}
	public static void printPath(int parent[],int vertex,int startNode){
		if (vertex == startNode) {	// reached the source vertex
	        System.out.print(startNode+" -> ");
	        return;
	    } else if (parent[vertex] == 0) {	// current vertex has no parent
	    	hopLength ++;
	    	if(vertex == parent.length-1)
	    		System.out.print(vertex);
	    	else
	    		System.out.print(vertex+" -> ");
	    	return;
		} else {	// go for the current vertex's parent
			hopLength ++;
	        printPath(parent, parent[vertex], startNode);
	        if(vertex == parent.length-1)
	    		System.out.print(vertex);
	    	else
	    		System.out.print(vertex+" -> ");
	    }
	}
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException{
		final String inputFileName = "D:\\PBProject\\DAAProject\\src\\com\\input2.txt";
		double[][] Graph = AdjacencyMatrix.calculateMean(new BufferedReader(new FileReader(inputFileName)));
		ObjectData[][] newGraph= AdjacencyMatrix.getMeanVarianceMatrix();
		dijkstra(Graph,newGraph,1,Graph.length-1,"Mean");
		Graph = AdjacencyMatrix.calculateOptimist(new BufferedReader(new FileReader(inputFileName)));
		newGraph= AdjacencyMatrix.getMeanVarianceMatrix();
		dijkstra(Graph,newGraph,1,Graph.length-1,"Optimist");
		Graph = AdjacencyMatrix.calculatePessimist(new BufferedReader(new FileReader(inputFileName)));
		newGraph= AdjacencyMatrix.getMeanVarianceMatrix();
		dijkstra(Graph,newGraph,1,Graph.length-1,"Pessimist");
		Graph = AdjacencyMatrix.calculateDoublePessimist(new BufferedReader(new FileReader(inputFileName)));
		newGraph= AdjacencyMatrix.getMeanVarianceMatrix();
		dijkstra(Graph,newGraph,1,Graph.length-1,"Double Pessimist");
		Graph = AdjacencyMatrix.calculateStable(new BufferedReader(new FileReader(inputFileName)));
		newGraph= AdjacencyMatrix.getMeanVarianceMatrix();
		dijkstra(Graph,newGraph,1,Graph.length-1,"Stable");
		Graph = AdjacencyMatrix.calculateStable(new BufferedReader(new FileReader(inputFileName)));
		newGraph= AdjacencyMatrix.getMeanVarianceMatrix();
		dijkstra(Graph,newGraph,1,Graph.length-1,"Triple Pessimist");
	}
}
