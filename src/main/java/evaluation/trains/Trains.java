package evaluation.trains;

import java.util.HashMap;
import java.util.Map;

public class Trains {

	static Map<String, Integer> distance = new HashMap<>();
	
	static int totalNumberOfTrips, shortestRoute, numberDifferentRoutes;

	// Define de distance values
	/*static {
		distance.put("AB", 5);
		distance.put("BC", 4);
		distance.put("CD", 8);
		distance.put("DC", 8);
		distance.put("DE", 6);
		distance.put("AD", 5);
		distance.put("CE", 2);
		distance.put("EB", 3);
		distance.put("AE", 7);
	}*/

	/**
	 * Method that loads the map
	 * @param values
	 */
	public static void registerGraph(String...values) {
		for (String value : values) {
			value = value.trim().replaceAll(" ","");
			distance.put(value.substring(0, 2), Integer.parseInt(value.substring(2)));
		}
		
	}

	/**
	 * Method that calculates the distance for a route
	 * @param cities
	 * @return calculated distance as string
	 */
	public static String distanceRoute(String...cities) {
		String lastCity = null;
		int totalDistance = 0;
		String route;
		for (String city : cities) {
			if (lastCity != null) {
				route = lastCity.concat(city);
				try {
					totalDistance += distance.get(route);
				} catch(NullPointerException npe) {
					return "NO SUCH ROUTE";
				}
			}
			lastCity = city;
		}
		return String.valueOf(totalDistance);
	}
	
	/*
	 * Iterates over all alternatives of routes
	 */
	private static void verifyNumberOfTrip(String origin, String destination, int maxNumberOfTrips) {
		distance.forEach((k,v) -> {
			if (k.startsWith(origin)) {
				if (k.endsWith(destination)) {
					// Modify the total number of trips
					Trains.totalNumberOfTrips++;
				}
				if (maxNumberOfTrips > 1){
					verifyNumberOfTrip(k.substring(1), destination, maxNumberOfTrips-1);
				}
			}
		});
	}
	
	
	/**
	 * Method that defines the number of available trips to a destination
	 * @param origin
	 * @param destination
	 * @param maxNumberOfTrips
	 * @return
	 */
	public static int numberOfTrips(String origin, String destination, int maxNumberOfTrips) {
		totalNumberOfTrips = 0;
		verifyNumberOfTrip(origin, destination, maxNumberOfTrips);
		return totalNumberOfTrips;
	}

	/*
	 * Iterates over the most reduced alternatives of routes
	 */
	private static void verifyShortestRoute(String origin, String destination, int totalNumberOfDistanceTravel) {
		distance.forEach((k,v) -> {
			if (k.startsWith(origin)) {
				if (k.endsWith(destination)) {
					// Obtain the shortest Route
					if (Trains.shortestRoute == 0 || totalNumberOfDistanceTravel+v < Trains.shortestRoute)
						Trains.shortestRoute = totalNumberOfDistanceTravel+v;
				} else {
					verifyShortestRoute(k.substring(1), destination, totalNumberOfDistanceTravel+v);
				}
			}
		});
	}

	/**
	 * Method that defines the shortest route available
	 * @param origin
	 * @param destination
	 * @return
	 */
	public static int shortestRoute(String origin, String destination) {
		shortestRoute = 0;
		verifyShortestRoute(origin, destination, 0);
		return shortestRoute;
	}

	/*
	 * Iterates over all alternatives of routes
	 */
	private static void verifyDifferentRoutes(String origin, String destination, int maxNumberOfDistance) {
		distance.forEach((k,v) -> {
			if (k.startsWith(origin)) {
				// Verify that the max distance doesn't pass the available distance
				if (k.endsWith(destination) && maxNumberOfDistance > v) {
					// Number of total different routes
					Trains.numberDifferentRoutes++;
				} 
				if (maxNumberOfDistance > v){
					verifyDifferentRoutes(k.substring(1), destination, maxNumberOfDistance-v);
				}
			}
		});
	}
	/**
	 * Method that defines the number of different routes
	 * @param origin
	 * @param destination
	 * @param maxDistance
	 * @return
	 */
	public static int numberDifferentRoutes(String origin, String destination, int maxDistance) {
		numberDifferentRoutes = 0;
		verifyDifferentRoutes(origin, destination, maxDistance);
		return numberDifferentRoutes;
	}

}
