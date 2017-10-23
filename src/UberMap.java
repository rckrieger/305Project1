import java.io.PrintWriter;

import java.util.*;
public class UberMap{
	private int NumRows = 300;
	private int NumCols = 300;
	int[][] grid = new int[NumRows][NumCols];
	ArrayList<Driver> allTheDrivers;
	ArrayList<Requestor> allTheRequestors;
	int sucessfulRides = 0;
	int failedRides = 0;
	Payment paymentSystem = new Payment(1.5);
	
	
	public static Coordinate generateLocale() {
		Random randGenerator = new Random();
		return new Coordinate(randGenerator.nextInt(300), 
				randGenerator.nextInt(300));
	}
	
	private void populateLocations(ArrayList<Driver> d, ArrayList<Requestor> r)
	{
		for (Driver carGuy: d)
		{
			carGuy.setStart(generateLocale());
		}
		for (Requestor passenger: r)
		{
			passenger.setStart(generateLocale());
			passenger.setEnd(generateLocale());
			while (passenger.getPickUp().equals(passenger.getDropOff()))
				passenger.setEnd(generateLocale());
			
		}
	}
	
	public UberMap(ArrayList<Driver> d, ArrayList<Requestor> r) {
		allTheDrivers = d;
		allTheRequestors = r;
		this.populateLocations(d, r); 	
	}

	public int getNumRows() {
		return NumRows;
	}
	public int getNumCols() {
		return NumCols;
	}
	private double distanceToPassenger(Driver driverPerson, Requestor customer)
	{
		return Math.sqrt(Math.pow((driverPerson.getPickUp().getX() - 
				customer.getPickUp().getX()), 2) +
				Math.pow((driverPerson.getPickUp().getY() - 
				customer.getPickUp().getY()),2));
	}
	public double distanceOfTrip(Requestor customer)
	{
		return Math.sqrt(Math.pow((customer.getDropOff().getX() - 
				customer.getPickUp().getX()), 2) + Math.pow(
				(customer.getDropOff().getY() - customer.getPickUp().getY()),
				2));
	}	
	
	public void rideComplete(Driver driverPerson, Requestor customer,
			int newRating) {
		driverPerson.setRating(newRating);
		customer.setRating(newRating);
		driverPerson.setOccupied(false);
		driverPerson.setStart(customer.getDropOff());
		customer.setStart(customer.getDropOff());
	}
	public double waitTime(double dist, double trafficTime) {
		return trafficTime * dist;
	}
	public DriverDistTuple sendDriver(Requestor customer, 
			PrintWriter triplog) {
		while(true)
		{
			ArrayList<DriverDistTuple> possibleDrivers = new 
					ArrayList<DriverDistTuple>();
			for (Driver car: allTheDrivers)
			{
				if(!car.isOccupied()){
					possibleDrivers.add(new DriverDistTuple(car, 
						distanceToPassenger(car, customer)));
				}	
			}
			Collections.sort(possibleDrivers);
			for (DriverDistTuple pair: possibleDrivers)
			{
				if (pair.getDriver().acceptsRide() &&
					paymentSystem.paymentProcess(customer, pair, triplog))
				{
					pair.getDriver().setOccupied(true);
					sucessfulRides++;
					return pair;
				}
			}
			failedRides++;
			return null;
		}
	}
	
	boolean validRequest(Requestor customer) {
		if (customer.getDropOff().getY() > -1 &&
			customer.getDropOff().getX() > -1 &&
			customer.getDropOff().getY() < getNumCols() &&
			customer.getDropOff().getX() < getNumRows())
		{
			return true;
		}
		return false;
	}
	
}