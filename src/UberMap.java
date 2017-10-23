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
		while(true)
		{
			ArrayList<DriverDistTuple> possibleDrivers = new ArrayList<DriverDistTuple>();
			for (Driver car: allTheDrivers)
			{
				if(!car.isOccupied()){
					possibleDrivers.add(new DriverDistTuple(car, distanceToPassenger(car, customer)));
				}	
			}
			Collections.sort(possibleDrivers);
			for (DriverDistTuple pair: possibleDrivers)
			{
				if (pair.getDriver().acceptsRide())
				{
					pair.getDriver().setOccupied(true);
					return pair.getDriver();
				}
			}
		}
	}
	private class DriverDistTuple implements Comparable<DriverDistTuple>{
		double dist;
		Driver dude;
		DriverDistTuple(Driver dude, double dist)
		{
			this.dist = dist;
			this.dude = dude;
		}
		public double getDist() {
			return this.dist;
		}
		public Driver getDriver() {
			return this.dude;
		}

		@Override
		public int compareTo(DriverDistTuple o) {
			if (this.getDist() > o.getDist())
				return 1;
			else if (this.getDist() < o.getDist())
				return -1;
			else
				return 0;
		}
		
	}
}