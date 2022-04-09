package AlgorithmsProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VancouverBusSystem {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean validInput = false;
		for (int i = 0; i < 100; i++) {
			System.out.print("*");
		}
		System.out.print(" \n                        Welcome to the Vancouver Bus System!\n");
		for (int i = 0; i < 100; i++) {
			System.out.print("*");
		}
		design();

		while (!validInput) {
			System.out.println("\nChoose which feature you want (0, 1, 2 or 3):\n" + "0: Quit\n"
					+ "1: Shortest path between two bus stops\n" + "2: Search for a bus stop\n"
					+ "3: Search for trips at a given time\n");

			if (input.hasNextInt()) {
				int userChoice = input.nextInt();

				switch (userChoice) {
				case 0:
					validInput = true;
					System.out.println("Quit selected" + "\nGoodbye and have a nice day.");
					break;
				case 1:
					validInput = true;
					callShortestPaths();
					break;
				case 2:
					callTST();
					break;
				case 3:
					callArrivalTimeSearch();
					break;
				default:
					System.out.println("Choice not valid.");

				}
			}

			else {
				System.out.println("Please only enter numbers.");
				input.nextLine();
			}

		}
		input.close();

	}

	public static void design() {
		String space = "              ";
		System.out.print("\n" + space);
		for (int i = 0; i < 30; i++) {
			System.out.print("_");
		}
		System.out.println("\n              |                            |");
		System.out.println("              |    ***        ***          |");
		System.out.println("           ___|    ***        ***          |");
		System.out.println("           |                               |");
		System.out.print("           |");
		for (int i = 0; i < 31; i++) {
			System.out.print("_");
			if (i == 30) {
				System.out.print("|");
			}
		}
		System.out.println("\n               ***                  ***");
		System.out.println("              *   *                *   *");
		System.out.println("               ***                  ***");

	}

	public static void callArrivalTimeSearch() {
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
		if (scanner.hasNext()) {
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
			for (int i = 0; i < input.length(); i++) {
				if (i < 2) {
					hrs += input.charAt(i);

				} else if (i > 2 && i <= 4) {
					minSt += input.charAt(i);
				} else if (i > 5 && i <= 7) {
					secSt += input.charAt(i);
				}

			}
			hours = Integer.parseInt(hrs);
			mins = Integer.parseInt(minSt);
			secs = Integer.parseInt(secSt);
			System.out.println("Info for arrival time: " + input);
			try {
				FileReader fileReader = new FileReader(
						"C:\\Users\\leaho\\Documents\\Alg & Data 2\\project input\\stop_times.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				boolean endOfFile = false;
				while (!endOfFile) {
					String line = bufferedReader.readLine();
					if (line != null) {
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
			String hrs2 = "";
			String minSt2 = "";
			String secSt2 = "";
			for (int j = 1; j < arrTimes.size(); j++) {
				String check = arrTimes.get(j);
				for (int i = 0; i < check.length(); i++) {
					if (i < 2) {
						if (Character.isWhitespace(check.charAt(i))) {
							hrs2 += "0";
						} else {
							hrs2 += check.charAt(i);
						}
					} else if (i > 2 && i < 5) {
						minSt2 += check.charAt(i);
					} else if (i > 5 && i < 8) {
						secSt2 += check.charAt(i);
					}
				}
				hours2 = Integer.parseInt(hrs2);
				mins2 = Integer.parseInt(minSt2);
				secs2 = Integer.parseInt(secSt2);
				if (hours <= 23) {
					if (hours == hours2 && mins == mins2 && secs == secs2) {
						indexes.add(j);
					}

					hrs2 = "";
					minSt2 = "";
					secSt2 = "";
				}
			}
			ArrivalTimeSearch.printData(indexes, timeIDs, depTimes, stopID, pickUp, dropOff, stopSeq, headsigns);

		}
	}

	public static void callShortestPaths() {
		 ShortestPaths test = new ShortestPaths("C:\\Users\\leaho\\Documents\\Alg & Data 2\\project input\\stops.txt");
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		while (!valid) {
			System.out.print("Please enter two different bus stops(separated by a space): ");
			if (in.hasNextInt()) {
				valid = true;
				int stop1 = in.nextInt();
				int stop2 = in.nextInt();
				System.out.print("Finding shortest path from stop_id " + stop1 + " to stop_id " + stop2);

			} else {
				System.out.print("Invalid input. Please try again: ");
			}
		}
		in.close();
	}
	public static void callTST() {
		boolean validSearch = false;
		Scanner scanner = new Scanner(System.in);
		NameSearch n = new NameSearch();
		while (!validSearch) {
			System.out.print("Enter the stop you are looking for:\n");
			String inputStop = scanner.nextLine();
			if (n.displayStopDetails(inputStop.toUpperCase()))
				;
			validSearch = true;

		}

	}

}
