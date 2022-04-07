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
		public char c; // character
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
		if (key == null) {
			throw new IllegalArgumentException("Inputted key for put() is null");
		} else if (!contains(key)) {

			n++;
			root = put(root, key, val, 0);
		}
	}

	private Node<Value> put(Node<Value> x, String key, Value val, int y) {
		char c = key.charAt(y);
		if (x == null) {
			x = new Node<Value>();
			x.c = c;
		}
		if (c < x.c) {
			x.left = put(x.left, key, val, y);
		} else if (c > x.c) {
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

	public List<Value> getList(String key) {
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