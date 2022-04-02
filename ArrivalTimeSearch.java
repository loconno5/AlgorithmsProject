package AlgorithmsProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrivalTimeSearch {
	
	static int [] quickSort (int a[]){
		 quickSort2(a, 0, (a.length-1));
		 return a;
	 }
	 static int [] quickSort2(int a[], int low, int high) {
		 if(low < high) {
			 int p = partition(a, low, high);
			 quickSort2(a, low, p-1);
			 quickSort2(a, p+1, high);
		 }
		 return a;
	 }
	 static int partition(int array[], int l, int h) {
		 int temp = array[h];
		 int ind = l-1;
		 for(int i=l;i <= h; i++) {
			 if(array[i] < temp) {
				 ind++;
				 swap(array, ind, i);
			 }
		 }
		 swap(array, ind + 1, h);
		 return (ind + 1);
	 }
	 static void swap (int[] a, int num1, int num2) {
		 int temp = a[num1];
		 a[num1] = a[num2];
		 a[num2] = temp;
	 }

	public static void main(String[] args) {
		ArrayList<String> arrTimes = new ArrayList<String>();
		ArrayList<String> timeIDs = new ArrayList<String>();
		ArrayList<String> depTimes = new ArrayList<String>();
		ArrayList<String> stopID = new ArrayList<String>();
		ArrayList<String> stopSeq = new ArrayList<String>();
		ArrayList<String> headsigns = new ArrayList<String>();
		ArrayList<String> pickUp = new ArrayList<String>();
		ArrayList<String> dropOff = new ArrayList<String>();
		ArrayList<String> shape = new ArrayList<String>();
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter an arrival time: (hh:mm:ss) ");
		if(scanner.hasNext()) {
			String input = scanner.next();
			int hours = 0;
			int mins = 0;
			int secs = 0;
			int hours2 = 0;
			int mins2 = 0;
			int secs2 = 0;
			String hrs = "";
			String minSt = "";
			String secSt = "";
			for(int i =0; i< input.length(); i++) {
				if(i < 2) {
			hrs += input.charAt(i);
					
				}
				else if (i > 2 && i <= 4) {
			minSt += input.charAt(i) ;
				}
				else if (i > 5 && i <= 7) {
					secSt += input.charAt(i) ;
						}

			}
			hours = Integer.parseInt(hrs);
			mins = Integer.parseInt(minSt);
			secs = Integer.parseInt(secSt);
			System.out.println("Info for arrival time: " + input);
		try {
			FileReader fileReader = new FileReader("C:\\Users\\leaho\\Documents\\Alg & Data 2\\project input\\stop_times.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			boolean endOfFile = false;
			while(!endOfFile) {
				String line = bufferedReader.readLine();
				if(line != null) {
					String[] timeData = line.split(",");
					String time = timeData[1];
						arrTimes.add(time);
						timeIDs.add(timeData[0]);
						depTimes.add(timeData[2]);
						stopID.add(timeData[3]);
						stopSeq.add(timeData[4]);
						headsigns.add(timeData[5]);
						pickUp.add(timeData[6]);
						dropOff.add(timeData[7]);
						//shape.add(timeData[8]);
					
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
		 String hrs2 = "";
		 String minSt2 = "";
	     String secSt2 = "";
			for(int j =1;j<arrTimes.size();j++) {
			String check = arrTimes.get(j);
			for(int i =0; i< check.length(); i++) {
				if(i < 2) {
					if (Character.isWhitespace(check.charAt(i))) {
						hrs2 += "0";
					}
					else {
			hrs2 += check.charAt(i);
				}
				}
				else if (i > 2 && i < 5) {
			minSt2 += check.charAt(i) ;
				}
				else if (i > 5 && i < 8) {
					secSt2 += check.charAt(i) ;
						}
			}
			hours2 = Integer.parseInt(hrs2);
			mins2 = Integer.parseInt(minSt2);
			secs2 = Integer.parseInt(secSt2);
			if(hours == hours2 && mins == mins2 && secs == secs2) {
				indexes.add(j);
			/*	System.out.println("Trip ID: " + timeIDs.get(j) + ", departure time: " + depTimes.get(j) + ", stop ID: " + stopID.get(j)
				+ ", stop sequence: " + stopSeq.get(j) +", headsigns: " + headsigns.get(j) + ", pick up: " + pickUp.get(j) + ", drop off: "
				+ dropOff.get(j)); */
			}
			
			hrs2 = "";
			minSt2 = "";
			secSt2 = "";
			}
		}
		int[] iDsToSort = new int[indexes.size()];
		for(int i=0;i<indexes.size(); i++) {
			iDsToSort[i] = Integer.parseInt(timeIDs.get(indexes.get(i)));
		}
		quickSort(iDsToSort);
		ArrayList<Integer> sortedIndexes = new ArrayList<Integer>();
		for(int i=0;i<indexes.size(); i++) {
			for(int j=0;j<indexes.size();j++) {
				if(iDsToSort[i] == Integer.parseInt(timeIDs.get(indexes.get(j)))){
					sortedIndexes.add(indexes.get(j));
				}
			}
		}
		for(int j=0;j<sortedIndexes.size();j++) {
				System.out.println("Trip ID: " + timeIDs.get(sortedIndexes.get(j)) + ", departure time: " + depTimes.get(sortedIndexes.get(j)) + ", stop ID: " + stopID.get(sortedIndexes.get(j))
			+ ", stop sequence: " + stopSeq.get(sortedIndexes.get(j)) +", headsigns: " + headsigns.get(sortedIndexes.get(j)) + ", pick up: " + pickUp.get(sortedIndexes.get(j)) + ", drop off: "
			+ dropOff.get(j)); 
		}
	}

}
