package AlgorithmsProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ShortestPaths {

	public double distTo[][];
	public int edgeTo[][];
	public double noEdges;
	public double noStops;
	public EdgeWeightedDigraph graph;
	public HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	public int mapIndex = 0;

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA,       sB, sC: speeds for 3 contestants using Dijkstra method
	 */
	ShortestPaths(String filename) {
		graph = initialiseVertexSizedGraph(filename);
		graph = addEdgesTransfers();
		graph = addEdgesStop_Times();
	}

	private EdgeWeightedDigraph addEdgesStop_Times() {
		File file = new File("C:\\Users\\leaho\\Documents\\Alg & Data 2\\project input\\stop_times.txt");
		try {	
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			String curr = scanner.nextLine();
			while(scanner.hasNextLine()) {
				String s= scanner.nextLine();
				String[] split = curr.split(",");
				String[] nextLineContents = s.split(",");
				if(split[0].equalsIgnoreCase(nextLineContents[0])) {
					String currentStopId = split[3];
					int v = Integer.parseInt(currentStopId);	// convert from String to int
					if(map.get(v)==null) {	// if bus stop not already encountered
						map.put(v, mapIndex);
						mapIndex++;
					}
					String nextStopId = nextLineContents[3];
					int w = Integer.parseInt(nextStopId);		// convert from String to int
					if(map.get(w)==null) {	// if bus stop not already encountered
						map.put(w, mapIndex);
						mapIndex++;
					}
					double weight = 1;
					DirectedEdge edge = new DirectedEdge(map.get(v), map.get(w), weight);
					graph.addEdge(edge);
				}
				curr = s;
			}	
		
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		return graph;
	}

	private EdgeWeightedDigraph addEdgesTransfers() {
		try {
			File file = new File("C:\\Users\\leaho\\Documents\\Alg & Data 2\\project input\\transfers.txt");
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine().trim();
				String[] split = s.split(",");
				int v = Integer.parseInt(split[0]);
				if(map.get(v)==null) {	// if bus stop not already encountered
					map.put(v, mapIndex);
					mapIndex++;
				}
				int w = Integer.parseInt(split[1]);
				if(map.get(w)==null) {	// if bus stop not already encountered
					map.put(w, mapIndex);
					mapIndex++;
				}
				int transferType = Integer.parseInt(split[2]);
				double weight = 0;
				if(transferType==0) {
					weight = 2;	
				}
				else if(transferType==2) {
					double minimumTransferTime = Double.parseDouble(split[3]);
					weight = minimumTransferTime/100;
				}
				DirectedEdge edge = new DirectedEdge(map.get(v), map.get(w), weight);
				graph.addEdge(edge);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		return graph;
	}

	private EdgeWeightedDigraph initialiseVertexSizedGraph(String filename2) {
		int noVerts = 0; // no. vertices

		try {
			File file = new File(filename2);
			Scanner scanner = new Scanner(file);
			int i = 0;
			while (scanner.hasNextLine()) {
				noVerts++;
				scanner.nextLine();
			}
			noVerts -= 1;
			graph = new EdgeWeightedDigraph(noVerts);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		return graph;

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
			if (minIndex == -1) {
				return;
			}
			visited[minIndex] = true;
			for (int i = 0; i < distTo.length; i++) {
				if (distTo[curr][minIndex] + distTo[minIndex][i] < distTo[curr][i]) {
					distTo[curr][i] = distTo[curr][minIndex] + distTo[minIndex][i];
					visited[i] = false;
					edgeTo[curr][i] = minIndex;
				}
			}
		}

	}

}
