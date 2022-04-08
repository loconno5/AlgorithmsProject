package AlgorithmsProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TST<Value> {
	private int n; // size
	private Node<Value> root; // root of TST
	private List<Value> vals; // possible values

	private class Node<Value> {
		public char ch; // character
		public Node<Value> left, mid, right; // left, middle, and right subtries
		public Value val; // value associated with string

		/**
		 * Initializes an empty string symbol table.
		 */
		public Node() {
			this.left = null;
			this.right = null;
			this.mid = null;
		}
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return n;
	}


	// return subtrie corresponding to given key
	private Node<Value> get(Node<Value> x, String key, int d) {
		if (x == null) {
			return null;
		}
		if (key.length() == 0) {
			throw new IllegalArgumentException("key must have length >= 1");
		}
		char c = key.charAt(d);
		if (c < x.ch) {
			return get(x.left, key, d);
		} else if (c > x.ch) {
			return get(x.right, key, d);
		} else if (d < (key.length() - 1)) {
			return get(x.mid, key, (d + 1));
		} else {
			return x;
		}
	}

	public void put(String key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException("Inputted key for put() is null");
		}
		root = put(root, key, val, 0);

	}

	private Node<Value> put(Node<Value> x, String key, Value val, int y) {
		char c = key.charAt(y);
		if (x == null) {
			x = new Node<Value>();
			x.ch = c;

		}
		if (c < x.ch) {
			x.left = put(x.left, key, val, y);
		} else if (c > x.ch) {
			x.right = put(x.right, key, val, y);

		} else if (y < key.length() - 1) {
			x.mid = put(x.mid, key, val, (y + 1));
		} else {
			x.val = val;
		}

		return x;
	}

	/*
	 * recursively searches subtries for given node adds node's value to list of
	 * possible values
	 */
	private void findChildVals(Node<Value> x) {

		if (x != null) {
			findChildVals(x.left);
			findChildVals(x.mid);
			findChildVals(x.right);

			if (x.val != null)
				vals.add(x.val);
		}
	}

	/*
	 * * Returns a list of all values associated with nodes in the trie found with
	 * the key and all of its subtries
	 * 
	 * @param key the search key
	 * 
	 * @return list of all values from nodes contained in the subtries of the node
	 * associated with the key, or in that node.
	 * 
	 * @throws IllegalArgumentException if key is null or if key's length is 0
	 */

	public List<Value> get(String key) {
		if (key == null) {
			throw new IllegalArgumentException("Inputted key for get() is null");
		}

		if (key.length() == 0) {
			throw new IllegalArgumentException("Inputted key must have length >= 1");
		}

		Node<Value> x = get(root, key, 0);
		if (x == null)
			return null;

		vals = new ArrayList<Value>();

		if (x.mid.val != null)
			vals.add(x.val);
		findChildVals(x.mid);

		return vals;
	}

}