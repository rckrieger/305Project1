import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Random;
public class UberMap{
	int NumRows = 300;
	int NumCols = 300;
	int[][] grid = new int[NumRows][NumCols];
	Random randGenerator = new Random();
	ArrayList<Driver> allTheDrivers;
	ArrayList<Requestor> allTheRequestors;
	Thread[] trips;
	PrintWriter triplog =  null;
	int sucessfulRides = 0;
	int failedRides = 0;
	File triplogFile = new File("triplog.txt");
	Payment paymentSystem = new Payment(2.0);
	public UberMap(ArrayList<Driver> d, ArrayList<Requestor> r) throws FileNotFoundException, UnsupportedEncodingException {
		allTheDrivers = d;
		allTheRequestors = r;
		trips = new Thread[allTheRequestors.size()];
		triplog = new PrintWriter(triplogFile);
		int counter = 0;
		for (Driver carGuy: d)
		{
			carGuy.setStart(new Coordinate(randGenerator.nextInt(300), randGenerator.nextInt(300)));
		}
		for (Requestor passenger: r)
		{
			passenger.setStart(new Coordinate(randGenerator.nextInt(300), randGenerator.nextInt(300)));
			passenger.setEnd(new Coordinate(randGenerator.nextInt(300), randGenerator.nextInt(300)));
			while (passenger.getPickUp().equals(passenger.getDropOff()))
				passenger.setEnd(new Coordinate(randGenerator.nextInt(300), randGenerator.nextInt(300)));
			Runnable trip = new Trip(passenger, this, triplog);
			trips[counter++] = new Thread(trip);
		}
		for (Thread trip: trips)
			trip.start();	
		for (Thread trip: trips)
		{
			try {
				trip.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	

	public int getNumRows() {
		return NumRows;
	}
	public int getNumCols() {
		return NumCols;
	}
	private double distanceToPassenger(Driver driverPerson, Requestor customer) {
		return Math.sqrt(Math.pow((driverPerson.getPickUp().getX() - customer.getPickUp().getX()), 2) +
		Math.pow((driverPerson.getPickUp().getY() - customer.getPickUp().getY()),2));
	}
	public double distanceOfTrip(Requestor customer)
	{
		return Math.sqrt(Math.pow((customer.getDropOff().getX() - customer.getPickUp().getX()), 2) +
				Math.pow((customer.getDropOff().getY() - customer.getPickUp().getY()),2));
	}	
	public void rideComplete(Driver driverPerson, Requestor customer) {
		driverPerson.setRating();
		customer.setRating();
		driverPerson.setOccupied(false);
		driverPerson.setStart(customer.getDropOff());
		customer.setStart(customer.getDropOff());
	}
	public double waitTime(double dist, double trafficTime) {
		return trafficTime * dist;
	}
	public DriverDistTuple sendDriver(Requestor customer) {
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
				if (pair.getDriver().acceptsRide() &&
					paymentSystem.paymentProcess(customer, pair, triplog))
				{
					pair.getDriver().setOccupied(true);
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