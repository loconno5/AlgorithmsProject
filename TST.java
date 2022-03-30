package AlgorithmsProject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;



public class TST<Key extends Comparable<Key>, Value> {

	private Node root;
	
	private class Node {
        private Key key;                   // sorted by key
        private Value val;                 // associated data
        private Node left, middle, right;  // left, middle and right subtrees
        private int N;                     // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

	public static void main(String[] args) {
		ArrayList<String> busStops = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader("C:\\Users\\leaho\\Documents\\Alg & Data 2\\project input\\stops.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			boolean endOfFile = false;
			while(!endOfFile) {
				String line = bufferedReader.readLine();
				if(line != null) {
					String[] stopData = line.split(",");
					String stopName = stopData[2];
					busStops.add(stopName);
				}
				else {
					endOfFile = true;
				}
			}
				
				
			bufferedReader.close();
			fileReader.close();

	}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
System.out.print("first   " + busStops.get(8));
}
}
