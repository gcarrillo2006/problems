package evaluation.trains;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TrainsTest {
	
	@Before
	public void setUp() {
		Trains.registerGraph("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
	}
	
	@Test
	public void badInformation() {
		Trains.registerGraph("A F5");
		assertEquals(Trains.distance.get("AF"), new Integer("5"));
	}
	
	@Test
	public void registerGraph() {
		// Verify that the map contains the value 4
		assertEquals(Trains.distance.get("AE"), new Integer("7"));
	}
	
	@Test
	public void checkDistanceRouteABC() {
		// Check the distance in the route A to B passing from B
		assertEquals(Trains.distanceRoute("A", "B", "C"), "9");
	}
	
	@Test
	public void checkDistanceRouteAD() {
		// Check the distance in the route A to D
		assertEquals(Trains.distanceRoute("A", "D"), "5");
	}
	
	@Test
	public void checkDistanceRouteAEBCD() {
		// Check the distance in the route A to D passing from E, B and C
		assertEquals(Trains.distanceRoute("A", "E", "B", "C", "D"), "22");
	}
	
	@Test
	public void testWithAnUnexistenceRoute() {
		// Check the distance in the route A to D passing from E
		assertEquals(Trains.distanceRoute("A", "E", "D"), "NO SUCH ROUTE");
	}
	
	@Test
	public void checkHowManyTripsExists() {
		// Check the number of trips from City C to City C and a maximum of 3 stops included
		assertEquals(Trains.numberOfTrips("C", "C", 3), 2);
	}
	
	@Test
	public void checkShortestRoute() {
		// Check the shortest route
		assertEquals(Trains.shortestRoute("A","C"), 9);
		//assertEquals(Trains.shortestRoute("B","B"), 9);
	}
	
	@Test
	public void checkNumberOfDifferentRoutes() {
		// Check the number of different routes
		assertEquals(Trains.numberDifferentRoutes("C","C",30), 7);
	}

}
