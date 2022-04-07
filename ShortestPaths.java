package AlgorithmsProject;

import java.io.File;
import java.util.Scanner;

public class ShortestPaths {

	public static String filename;
	public double distTo[][];
	public int edgeTo[][];
	public double noEdges;
	public double noStops;

	/**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    using Dijkstra method */
    ShortestPaths (String filename){
     this.filename = filename;
     try {
     File file = new File(filename);
     Scanner scanner = new Scanner(file);
     noEdges = 1777456;
     noStops = 1772370;
     int i =0;
     while(scanner.hasNextLine()) {
    	 String s = scanner.nextLine().trim();
    	 String[] split = s.split("\\s+");
    	 if(i ==0) {
    		 distTo = new double[(int) noStops][(int) noStops];   // making graph
    		 edgeTo = new int[(int) noStops][(int) noStops];
    		 
    		 for(int j=0;j<distTo.length;j++) {
    			 for(int k=0; k<distTo[j].length;k++) {
    				 distTo[j][k] = Integer.MAX_VALUE;
     				if(j == k)
     				{
     					distTo[j][k] = 0;
     				} 
    			 }
    		 }
    	 }
     
 		else
 		{
 			distTo[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = Double.parseDouble(split[2]);
 			edgeTo[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = Integer.parseInt(split[0]);
 			
 			
 		}
 		
 		i++;	
     }
     for(int ind=0;ind<distTo.length;ind++) {
    	 findShortestPath(ind);
     }
     
    }
    
     catch (Exception x) {
    	 distTo = new double[0][0];
 		edgeTo = new int[0][0];
 		return;
     }
     
    	 
     }

	public void findShortestPath(int curr) {
		boolean[] visited = new boolean[distTo.length];
		visited[curr] = true;
		int min = Integer.MAX_VALUE;
		while (true) {
			int minIndex = -1;
			for (int i = 0; i < distTo.length; i++) {
				if ((visited[i] == false) && (distTo[curr][i] != min)) {
					minIndex = i;
					break;
				}
			}
			if(minIndex == -1) {
				return;
			}
			visited[minIndex] = true;
			for(int i=0; i<distTo.length;i++) {
				if(distTo[curr][minIndex] + distTo[minIndex][i] < distTo[curr][i]) {
				distTo[curr][i]	= distTo[curr][minIndex] + distTo[minIndex][i];
				visited[i] = false;
				edgeTo[curr][i] = minIndex;
				}
			}
		}

	}
	public static void main(String[] args) {
		ShortestPaths test = new ShortestPaths("C:\\Users\\leaho\\Documents\\Alg & Data 2\\project input\\transfers.txt");
		System.out.print("Please enter two different bus stops(separated by a space): ");
		Scanner scanner = new Scanner(System.in);
		if(scanner.hasNextInt()) {
			int stop1 = scanner.nextInt();
			int stop2 = scanner.nextInt();
			
		}

	}

}
