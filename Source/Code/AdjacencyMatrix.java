package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLClientInfoException;

class ObjectData{
	public final double a;
	public final double b;
	public final double coefficientSquare;
	public ObjectData(double a,double b,double coefficientSquare) {
		this.a = a;
		this.b = b;
		this.coefficientSquare = coefficientSquare;
	}
}
public class AdjacencyMatrix {
	
	private static final String inputFileName = "D:\\PBProject\\DAAProject\\src\\com\\input.txt";
	private static double[][] adjacencyMatrix = new double[13][13];
	private static ObjectData[][] newadjacencyMatrix =new ObjectData[13][13];
	
	public static double[][] calculateMean(BufferedReader data) throws NumberFormatException, IOException{
		String sCurrentLine;
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	adjacencyMatrix[i][j]= -1.0;
            }
        }
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	newadjacencyMatrix[i][j]=new ObjectData(0.0,0.0,0.0);
            }
        }
		while ((sCurrentLine = data.readLine()) != null) {
			String[] current=sCurrentLine.split(",");
			if(current[3].equals("1")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = 0.0;
				double standardDeviation = Math.sqrt(variance);
				double squaredCoefficient = 0.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),mean);
			}
			else if(current[3].equals("2")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = (a + b)/2;
				double variance = ((b - a)*(b - a))/12;
				double standardDeviation = (double) Math.sqrt(variance);
				double coefficient = (b - a)/(a + b);
				double squaredCoefficient = coefficient * coefficient/3;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),mean);
			}
			else if(current[3].equals("3")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = 1/a;
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double squaredCoefficient = 1.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),mean);
			}
			else if(current[3].equals("4")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = b + (1/a);
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double coefficient = 1/(1 + (b * a));
				double squaredCoefficient = coefficient * coefficient;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),mean);
			}
			else if(current[3].equals("5")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = b;
				double standardDeviation = (double) Math.sqrt(variance);
				double squaredCoefficient = b/(a * a) ;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),mean);
			}
			else if(current[3].equals("6")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = (a * a) * b;
				double standardDeviation = (double) Math.sqrt(variance);
				double squaredCoefficient = b;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),mean);
			}
		}
		return adjacencyMatrix;
	}
	public static ObjectData[][] getMeanVarianceMatrix(){
		return newadjacencyMatrix;
	}
	public static double[][] calculateOptimist(BufferedReader data) throws NumberFormatException, IOException{
		String sCurrentLine;
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	adjacencyMatrix[i][j]=-1.0;
            }
        }
		for (int i = 1; i <= 12; i++){
            for (int j = 1; j <= 12; j++){
            	newadjacencyMatrix[i][j]=new ObjectData(0.0,0.0,0.0);
            }
        }
		while ((sCurrentLine = data.readLine()) != null) {
			String[] current=sCurrentLine.split(",");
			if(current[3].equals("1")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a; 
				double variance = 0.0;
				double standardDeviation = (double) Math.sqrt(variance);
				double optimistValue = mean - standardDeviation;
				double squaredCoefficient = 0.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),optimistValue);
			}
			else if(current[3].equals("2")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = (a + b)/2;
				double variance = ((b - a)*(b - a))/12;
				double standardDeviation = (double) Math.sqrt(variance);
				double optimistValue = mean - standardDeviation;
				double coefficient = (b - a)/(a + b);
				double squaredCoefficient = coefficient * coefficient/3;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),optimistValue);
			}
			else if(current[3].equals("3")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = 1/a;
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double optimistValue = mean - standardDeviation;
				double squaredCoefficient = 1.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),optimistValue);
			}
			else if(current[3].equals("4")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = b + (1/a);
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double optimistValue = mean - standardDeviation;
				double coefficient = 1/(1 + (b * a));
				double squaredCoefficient = coefficient * coefficient;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),optimistValue);
			}
			else if(current[3].equals("5")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = b;
				double standardDeviation = (double) Math.sqrt(variance);
				double optimistValue = mean - standardDeviation;
				double squaredCoefficient = b/(a * a) ;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),optimistValue);			
			}
			else if(current[3].equals("6")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = (a * a) * b;
				double standardDeviation = (double) Math.sqrt(variance);
				double optimistValue = mean - standardDeviation;
				double squaredCoefficient = b;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),optimistValue);
			}
		}
		return adjacencyMatrix;
	}
	public static double[][] calculatePessimist(BufferedReader data) throws NumberFormatException, IOException{
		String sCurrentLine;
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	adjacencyMatrix[i][j]=-1.0;
            }
        }
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	newadjacencyMatrix[i][j]=new ObjectData(0.0,0.0,0.0);
            }
        }
		while ((sCurrentLine = data.readLine()) != null) {
			String[] current=sCurrentLine.split(",");
			if(current[3].equals("1")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a; 
				double variance = 0.0;
				double standardDeviation = (double) Math.sqrt(variance);
				double pessimistValue = mean + standardDeviation;
				double squaredCoefficient = 0.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),pessimistValue);
			}
			else if(current[3].equals("2")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = (a + b)/2;
				double variance = ((b - a)*(b - a))/12;
				double standardDeviation = (double) Math.sqrt(variance);
				double pessimistValue = mean + standardDeviation;
				double coefficient = (b - a)/(a + b);
				double squaredCoefficient = coefficient * coefficient/3;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),pessimistValue);
			}
			else if(current[3].equals("3")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = 1/a;
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double pessimistValue = mean + standardDeviation;
				double squaredCoefficient = 1.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),pessimistValue);
			}
			else if(current[3].equals("4")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = b + (1/a);
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double pessimistValue = mean + standardDeviation;
				double coefficient = 1/(1 + (b * a));
				double squaredCoefficient = coefficient * coefficient;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),pessimistValue);
			}
			else if(current[3].equals("5")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = b;
				double standardDeviation = (double) Math.sqrt(variance);
				double pessimistValue = mean + standardDeviation;
				double squaredCoefficient = b/(a * a) ;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),pessimistValue);			
			}
			else if(current[3].equals("6")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = (a * a) * b;
				double standardDeviation = (double) Math.sqrt(variance);
				double pessimistValue = mean + standardDeviation;
				double squaredCoefficient = b;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),pessimistValue);
			}
		}
		return adjacencyMatrix;
	}
	public static double[][] calculateDoublePessimist(BufferedReader data) throws NumberFormatException, IOException{
		String sCurrentLine;
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	adjacencyMatrix[i][j]=-1.0;
            }
        }
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	newadjacencyMatrix[i][j]=new ObjectData(0.0,0.0,0.0);
            }
        }
		while ((sCurrentLine = data.readLine()) != null) {
			String[] current=sCurrentLine.split(",");
			if(current[3].equals("1")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a; 
				double variance = 0.0;
				double squaredCoefficient = 0.0;
				double standardDeviation = (double) Math.sqrt(variance);
				double doublePessimistValue = mean + (2 * standardDeviation);
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doublePessimistValue);
			}
			else if(current[3].equals("2")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = (a + b)/2;
				double variance = ((b - a)*(b - a))/12;
				double standardDeviation = (double) Math.sqrt(variance);
				double doublePessimistValue = mean + (2 * standardDeviation);
				double coefficient = (b - a)/(a + b);
				double squaredCoefficient = coefficient * coefficient/3;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doublePessimistValue);
			}
			else if(current[3].equals("3")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = 1/a;
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double doublePessimistValue = mean + (2 * standardDeviation);
				double squaredCoefficient = 1.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doublePessimistValue);
			}
			else if(current[3].equals("4")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = b + (1/a);
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double doublePessimistValue = mean + (2 * standardDeviation);
				double coefficient = 1/(1 + (b * a));
				double squaredCoefficient = coefficient * coefficient;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doublePessimistValue);
			}
			else if(current[3].equals("5")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = b;
				double standardDeviation = (double) Math.sqrt(variance);
				double doublePessimistValue = mean + (2 * standardDeviation);
				double squaredCoefficient = b/(a * a) ;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doublePessimistValue);			
			}
			else if(current[3].equals("6")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = (a * a) * b;
				double standardDeviation = (double) Math.sqrt(variance);
				double doublePessimistValue = mean + (2 * standardDeviation);
				double squaredCoefficient = b;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doublePessimistValue);
			}
		}
		return adjacencyMatrix;
	}
	public static double[][] calculateStable(BufferedReader data) throws NumberFormatException, IOException{
		String sCurrentLine;
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	adjacencyMatrix[i][j]=-1.0;
            }
        }
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	newadjacencyMatrix[i][j]=new ObjectData(0.0,0.0,0.0);
            }
        }
		while ((sCurrentLine = data.readLine()) != null) {
			String[] current=sCurrentLine.split(",");
			if(current[3].equals("1")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a; 
				double variance = 0.0;
				double standardDeviation = (double) Math.sqrt(variance);
				double squaredCoefficient = 0.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),squaredCoefficient);
			}
			else if(current[3].equals("2")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = (a + b)/2;
				double variance = ((b - a)*(b - a))/12;
				double standardDeviation = (double) Math.sqrt(variance);
				double coefficient = (b - a)/(a + b);
				double squaredCoefficient = coefficient * coefficient/3;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),squaredCoefficient);
			}
			else if(current[3].equals("3")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = 1/a;
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double squaredCoefficient = 1.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),squaredCoefficient);
			}
			else if(current[3].equals("4")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = b + (1/a);
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double coefficient = 1/(1 + (b * a));
				double squaredCoefficient = coefficient * coefficient;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),squaredCoefficient);
			}
			else if(current[3].equals("5")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = b;
				double standardDeviation = (double) Math.sqrt(variance);
				double squaredCoefficient = b/(a * a) ;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),squaredCoefficient);			
			}
			else if(current[3].equals("6")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = (a * a) * b;
				double standardDeviation = (double) Math.sqrt(variance);
				double squaredCoefficient = b;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),squaredCoefficient);
			}
		}
		return adjacencyMatrix;
	}
	public static double[][] calculateDoubleOptimist(BufferedReader data) throws NumberFormatException, IOException{
		String sCurrentLine;
		for (int i = 0; i <= 12; i++){
            for (int j = 0; j <= 12; j++){
            	adjacencyMatrix[i][j]=-1.0;
            }
        }
		for (int i = 1; i <= 12; i++){
            for (int j = 1; j <= 12; j++){
            	newadjacencyMatrix[i][j]=new ObjectData(0.0,0.0,0.0);
            }
        }
		while ((sCurrentLine = data.readLine()) != null) {
			String[] current=sCurrentLine.split(",");
			if(current[3].equals("1")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a; 
				double variance = 0;
				double standardDeviation = (double) Math.sqrt(variance);
				double doubleOptimist = mean + (3 * standardDeviation);
				double squaredCoefficient = 0.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doubleOptimist);
			}
			else if(current[3].equals("2")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = (a + b)/2;
				double variance = ((b - a)*(b - a))/12;
				double standardDeviation = (double) Math.sqrt(variance);
				double doubleOptimist = mean + (3 * standardDeviation);
				double coefficient = (b - a)/(a + b);
				double squaredCoefficient = coefficient * coefficient/3;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doubleOptimist);
			}
			else if(current[3].equals("3")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = 1/a;
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double doubleOptimist = mean + (3 * standardDeviation);
				double squaredCoefficient = 1.0;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doubleOptimist);
			}
			else if(current[3].equals("4")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = b + (1/a);
				double variance = 1/(a * a);
				double standardDeviation = (double) Math.sqrt(variance);
				double doubleOptimist = mean + (3 * standardDeviation);
				double coefficient = 1/(1 + (b * a));
				double squaredCoefficient = coefficient * coefficient;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doubleOptimist);
			}
			else if(current[3].equals("5")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = b;
				double standardDeviation = (double) Math.sqrt(variance);
				double doubleOptimist = mean + (3 * standardDeviation);
				double squaredCoefficient = b/(a * a) ;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doubleOptimist);			
			}
			else if(current[3].equals("6")){
				double a=  Double.parseDouble(current[4]);
				double b = Double.parseDouble(current[5]);
				double mean = a;
				double variance = (a * a) * b;
				double standardDeviation = (double) Math.sqrt(variance);
				double doubleOptimist = mean + (3 * standardDeviation);
				double squaredCoefficient = b;
				makeNewEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),new ObjectData(mean,standardDeviation,squaredCoefficient));
				makeEdge(Integer.parseInt(current[1]),Integer.parseInt(current[2]),doubleOptimist);
			}
		}
		return adjacencyMatrix;
	}
	public static void makeEdge(int to, int from, double edge) 
    {
		adjacencyMatrix[to][from] = edge;
    }
	public static void makeNewEdge(int to, int from, ObjectData edge) 
    {
		newadjacencyMatrix[to][from] = edge;
    }
	public static void printAdjacencyMatrix(){
		System.out.print("  ");
        for (int i = 1; i <= 12; i++)
            System.out.print(i + "   ");
        System.out.println();

        for (int i = 1; i <= 12; i++) 
        {
            System.out.print(i + " ");
            for (int j = 1; j <= 12; j++) 
                System.out.print(getEdge(i, j) + " ");
            System.out.println();
        }
	}
	public static double getEdge(int to, int from) 
    {
        try 
        {
            return adjacencyMatrix[to][from];
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("The vertices does not exists");
        }
        return 0;
    }
}
