package AlgorithmsProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrivalTimeSearch {

	static int[] quickSort(int a[]) {
		quickSort2(a, 0, (a.length - 1));
		return a;
	}

	static int[] quickSort2(int a[], int low, int high) {
		if (low < high) {
			int p = partition(a, low, high);
			quickSort2(a, low, p - 1);
			quickSort2(a, p + 1, high);
		}
		return a;
	}


	static int partition(int array[], int l, int h) {
		int temp = array[h];
		int ind = l - 1;
		for (int i = l; i <= h; i++) {
			if (array[i] < temp) {
				ind++;
				swap(array, ind, i);
			}
		}
		swap(array, ind + 1, h);
		return (ind + 1);
	}

	static void swap(int[] a, int num1, int num2) {
		int temp = a[num1];
		a[num1] = a[num2];
		a[num2] = temp;
	}
	public static void printData(ArrayList<Integer> indexes, ArrayList<String> timeIDs, ArrayList<String> depTimes, ArrayList<String> stopID
			, ArrayList<String> pickUp, ArrayList<String> dropOff, ArrayList<String> stopSeq, ArrayList<String> headsigns) {
		int[] iDsToSort = new int[indexes.size()];
		for (int i = 0; i < indexes.size(); i++) {
			iDsToSort[i] = Integer.parseInt(timeIDs.get(indexes.get(i)));
		}
		quickSort(iDsToSort);
		ArrayList<Integer> sortedIndexes = new ArrayList<Integer>();
		for (int i = 0; i < indexes.size(); i++) {
			for (int j = 0; j < indexes.size(); j++) {
				if (iDsToSort[i] == Integer.parseInt(timeIDs.get(indexes.get(j)))) {
					sortedIndexes.add(indexes.get(j));
				}
			}
		}
		for (int j = 0; j < sortedIndexes.size(); j++) {
			System.out.println("Trip ID: " + timeIDs.get(sortedIndexes.get(j)) + ", departure time: "
					+ depTimes.get(sortedIndexes.get(j)) + ", stop ID: " + stopID.get(sortedIndexes.get(j))
					+ ", stop sequence: " + stopSeq.get(sortedIndexes.get(j)) + ", headsigns: "
					+ headsigns.get(sortedIndexes.get(j)) + ", pick up: " + pickUp.get(sortedIndexes.get(j))
					+ ", drop off: " + dropOff.get(j));
		} 
	}


} 
