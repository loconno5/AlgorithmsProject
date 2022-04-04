package AlgorithmsProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import BST.Node;

public class TST<Value> {
	private int n; // size
	private Node<Value> root; // root of TST

	private static class Node<Value> {
		private char c; // character
		private Node<Value> left, mid, right; // left, middle, and right subtries
		private Value val; // value associated with string
	}

	/**
	 * Initializes an empty string symbol table.
	 */
	public TST() {
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return n;
	}

	/**
	 * Does this symbol table contain the given key?
	 * 
	 * @param key the key
	 * @return true if this symbol table contains {@code key} and false otherwise
	 */
	public boolean contains(String key) {
		if (key == null) {
			return false;
		}
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the given key.
	 * 
	 * @param key the key
	 * @return the value associated with the given key if the key is in the symbol
	 *         table and {@code null} if the key is not in the symbol table
	 * @return null if key is null
	 */
	public Value get(String key) {
		if (key == null) {
			return null;
		}
		if (key.length() == 0) {
			return null;
		}
		;
		Node<Value> x = get(root, key, 0);
		if (x == null)
			return null;
		return x.val;
	}

	// return subtrie corresponding to given key
	private Node<Value> get(Node<Value> x, String key, int d) {
		if (x == null) {
			return null;
		}
		if (key.length() == 0)
			throw new IllegalArgumentException("key must have length >= 1");
		char c = key.charAt(d);
		if (c < x.c) {
			return get(x.left, key, d);
		} else if (c > x.c) {
			return get(x.right, key, d);
		} else if (d < key.length() - 1) {
			return get(x.mid, key, d + 1);
		} else {
			return x;
		}
	}
	public void put(String key, Value val) {
		if(!contains(key)) {
			n++;
			root = put(root, key, val, 0);
		}
	}
	private Node<Value> put(Node<Value> x, String key, Value val, int y){
		char c = key.charAt(y);
		if(x ==null) {
			x = new Node<Value>();
			x.c = c;
		}
		if(c < x.c) {
			x.left = put(x.left, key, val, y);
		}
		else if (c > x.c){
			x.right = put(x.right, key, val, y);
		}
		else if(y < key.length()-1) {
			x.mid = put(x.mid, key, val, (y+1));
		}
		else {
			x.val = val;
		}
		return x;
	}

	public static void main(String[] args) {
		ArrayList<String> busStops = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(
					"C:\\Users\\leaho\\Documents\\Alg & Data 2\\project input\\stops.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			boolean endOfFile = false;
			while (!endOfFile) {
				String line = bufferedReader.readLine();
				if (line != null) {
					String[] stopData = line.split(",");
					String stopName = stopData[2];
					int len = stopName.length();
					String stopNameFixed = "";
					if (stopName.charAt(1) == ('B')) {
						String temp = "";
						temp += " ";
						temp += stopName.charAt(0);
						temp += stopName.charAt(1);
						for (int i = 0; i < len - 2; i++) {
							stopNameFixed += stopName.charAt(i + 2);
						}
						stopNameFixed += temp;
						busStops.add(stopNameFixed);
					} else if (stopName.contains("FLAGSTOP")) {
						String temp = " FLAGSTOP";
						for (int i = 0; i < len - 8; i++) {
							stopNameFixed += stopName.charAt(i + 8);
						}
						stopNameFixed += temp;
						busStops.add(stopNameFixed);

					} else {
						busStops.add(stopName);
					}

				} else {
					endOfFile = true;
				}
			}

			bufferedReader.close();
			fileReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//System.out.print("first   " + busStops.get(8754));
	}
}
