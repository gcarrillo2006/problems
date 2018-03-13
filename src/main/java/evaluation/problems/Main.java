/**
 * 
 */
package evaluation.problems;

import java.util.InputMismatchException;
import java.util.Scanner;

import evaluation.trains.Trains;

/**
 * 
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Trains application");
		System.out.println(
				"Please register the graphs in format XY1 where X is a letter of a city, Y is a letter of another city and 1 is a number for separation use whitespace and press enter");
		sc.useDelimiter("\\s");
		String graphsText = sc.nextLine();
		String[] graphs = graphsText.split(" ");
		int i = 0;
		boolean proceed = true;
		while (i < graphs.length) {
			// Validation over monkey test to get a correct register
			if (!graphs[0].matches("[A-Z]{2}[0-9]{1}"))
				proceed = false;
			i++;
		}
		if (proceed) {
			Trains.registerGraph(graphs);
			menu(sc);
		} else {
			System.out.println("Invalid data exiting now!");
		}
		sc.close();
	}

	private static void menu(Scanner sc) {
		System.out.println("Select an option for testing");
		System.out.println("1 Distance routes");
		System.out.println("2 Number of trips");
		System.out.println("3 Shorter route available");
		System.out.println("4 Number of different routes");
		String option = sc.nextLine();
		try {
			switch (option) {
			case "1":
				System.out.println(
						"Insert the letter of the cities in other use whitespace to separate the cities letters");
				String route = sc.nextLine();
				System.out
						.println("The distance of the route " + route + ": " + Trains.distanceRoute(route.split(" ")));
				break;
			case "2":
				System.out.println("Insert the letter of the origin city");
				String origin = sc.nextLine();
				System.out.println("Insert the letter of the destination city");
				String destination = sc.nextLine();
				System.out.println("Enter the max number of trips to verify (only numbers)");
				int maxNumberOfTrips = sc.nextInt();
				System.out.println(
						"The number of trips are: " + Trains.numberOfTrips(origin, destination, maxNumberOfTrips));
				break;
			case "3":
				System.out.println("Insert the letter of the origin city");
				origin = sc.nextLine();
				System.out.println("Insert the letter of the destination city");
				destination = sc.nextLine();
				System.out.println("The shorter route available is: " + Trains.shortestRoute(origin, destination));
				break;
			case "4":
				System.out.println("Insert the letter of the origin city");
				origin = sc.nextLine();
				System.out.println("Insert the letter of the destination city");
				destination = sc.nextLine();
				System.out.println("Enter the max distance to verify (only numbers)");
				int maxDistance = sc.nextInt();
				System.out.println("The number of different routes are: "
						+ Trains.numberDifferentRoutes(origin, destination, maxDistance));
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		// Validation if invalid number registered when required
		} catch (InputMismatchException ime) {
			System.out.println("Invalid Number registered");
			sc.nextLine();
		} finally {
			System.out.println("Do you want to exit? (y/n)");
			String exit = sc.next("[y|n]");
			if (!exit.equals("y")) {
				sc.nextLine();
				menu(sc);
			}
		}
	}

}
