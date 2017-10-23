import java.util.*;
public class UberMap{
	int NumRows = 300;
	int NumCols = 300;
	int[][] grid = new int[NumRows][NumCols];
	Driver[] allTheDrivers = new Driver[5];
	Requestor[] allTheCustomers = new Rider[10];
	public int getNumRows() {
		return NumRows;
	}
	public int getNumCols() {
		return NumCols;
	}
	private double distanceToPassenger(Driver driverPerson, Requestor customer) {
		return Math.pow((driverPerson.getPickUp().getX() - customer.getPickUp().getX()), 2) +
		Math.pow((driverPerson.getPickUp().getY() - customer.getPickUp().getY()),2);
	}
	private double distanceOfTrip(Requestor customer)
	{
		return Math.pow((customer.getDropOff().getX() - customer.getPickUp().getX()), 2) +
				Math.pow((customer.getDropOff().getY() - customer.getPickUp().getY()),2);
	}
	
	
	public Driver sendDriver(Requestor customer) {
		Map values = new HashMap<Double, Driver>();
		for (Driver car: allTheDrivers)
		{
			values.put(distanceToPassenger(car, customer), car);
		}
		Collections.sort((List<Double>) values.keySet());
		
	}
}