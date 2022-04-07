package AlgorithmsProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameSearch {
    static TST<String[]> TST;
	static List<String> input;

	public NameSearch() {

		TST = new TST<String[]>();
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
					String adjusted = adjustStopName(stopName);
					busStops.add(adjusted);

					TST.put(adjusted, stopData);
					

				} else {
					endOfFile = true;
				}
			}

			bufferedReader.close();
			fileReader.close();

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean displayStopDetails(String stopName) {

		if (stopName.replaceAll(" ", "").equals("")) {
			System.out.println("Inputted key must have length >= 1");
		}

		else {

			List<String[]> stopDetails = getStopDetails(stopName, input);

			if (stopDetails == null || stopDetails.size() == 0) {
				System.out.println("No stops match this search criteria");
				return false;
			}

			else {

				for (int i = 0; i < stopDetails.size(); i++) {
					String[] busStop = stopDetails.get(i);
					String stopOutput = "";

					System.out.println("Stop details for stop : " + busStop[2]);

					for (int j = 0; j < busStop.length; j++) {
						stopOutput += (": " + (!busStop[j].replaceAll(" ", "").equals("") ? busStop[j] : "unknown")
								+ ", ");
					}

					System.out.println(stopOutput.substring(0, stopOutput.length() - 2));
					System.out.println();
				}
			}

		}
		return true;
	}

	public static String adjustStopName(String stopName) {
		int len = stopName.length();
		String stopNameFixed = "";
		String returnStop = "";
		if (stopName.charAt(1) == ('B')) {
			String temp = "";
			temp += " ";
			temp += stopName.charAt(0);
			temp += stopName.charAt(1);
			for (int i = 0; i < len - 2; i++) {
				stopNameFixed += stopName.charAt(i + 2);
			}
			stopNameFixed += temp;
			returnStop = stopNameFixed;
		} else if (stopName.contains("FLAGSTOP")) {
			String temp = " FLAGSTOP";
			for (int i = 0; i < len - 8; i++) {
				stopNameFixed += stopName.charAt(i + 8);
			}
			stopNameFixed += temp;
			returnStop = stopNameFixed;
		} else {
			returnStop = stopName;
		}
		return returnStop;
	}

	public static List<String[]> getStopDetails(String stopName, List<String> input) {
		String adjustedStopName = adjustStopName(stopName);
		List<String[]> stopDetails = TST.getList(adjustedStopName);

		if (stopDetails == null || stopDetails.size() == 0) {
			return null;
		}
		String curr = "";
		boolean same = false;
		for (int i = 0; i < stopDetails.size(); i++) {
			curr = stopDetails.get(i)[2];
			String[] stopWords = curr.split(" ");
			for (int j = 0; j < input.size(); j++) {
				if (stopWords[j].equals(input.get(j))) {
					same = true;
				} else {
					stopDetails.remove(i);
				}
			}
		}

		return stopDetails;
	}

}
