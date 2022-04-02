package AlgorithmsProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrivalTimeSearch {

	public static void main(String[] args) {
		ArrayList<String> arrTimes = new ArrayList<String>();
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
			for(int j =1;j<arrTimes.size();j++) {
			hours = Integer.parseInt(hrs);
			mins = Integer.parseInt(minSt);
			secs = Integer.parseInt(secSt);
			String check = arrTimes.get(j);
			  hrs = "";
			 minSt = "";
		     secSt = "";
			for(int i =0; i< check.length(); i++) {
				if(i < 2) {
					if (check.indexOf(' ') == 0) {
						hrs += '0';
					}
					else {
			hrs += check.charAt(i);
				}
				}
				else if (i > 2 && i < 5) {
			minSt += check.charAt(i) ;
				}
				else if (i > 5 && i < 8) {
					secSt += check.charAt(i) ;
						}
			}
			hours2 = Integer.parseInt(hrs);
			mins2 = Integer.parseInt(minSt);
			secs2 = Integer.parseInt(secSt);
			if(hours == hours2 && mins == mins2 && secs == secs2) {
				System.out.print("equals yay");
			}
			}
		}

	}

}
