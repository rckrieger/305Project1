import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Input
{
	
	private static ArrayListTuple readInput() {
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		ArrayList<Requestor> riders = new ArrayList<Requestor>();
		File inputFile = null;
		Scanner inputStream  = null;
		try {
			inputFile = new File("test.txt") ;
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			inputStream = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (inputStream.hasNext()) {
			//my code assumes the input file is properly formated 
			String userType = inputStream.next();
			String userName = inputStream.next();
			double balance = inputStream.nextDouble();
			
			if (userType.equals("Passenger"))
			{
				riders.add(new Rider(userName, balance));
			}
			else {
				String carTitle = inputStream.next();
				boolean occupied = inputStream.nextBoolean();
				drivers.add(new Driver(userName, balance, carTitle, occupied));
			}
	    }
		
		return new ArrayListTuple(drivers, riders);
	}
	
	public static void main(String[] args)
	{
		ArrayListTuple inputArrayLists = readInput();
		ArrayList<Driver> drivers = inputArrayLists.getDrivers();
		ArrayList<Requestor> riders = inputArrayLists.getRiders();
		UberMap floorplan = null;
		floorplan = new UberMap(drivers, riders);
		startTrips(floorplan);
		finalstates(floorplan);
	}
	
	public static PrintWriter intailizeTriplog() {
		File triplogFile = null;
		try {
			triplogFile = new File("triplog.txt");
		}catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter triplog =  null;
		try {
			triplog = new PrintWriter(triplogFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return triplog;
	}
	
	private static void startTrips(UberMap floorplan)
	{
		int counter = 0;
		PrintWriter triplog = intailizeTriplog();
		Thread[] trips;
		trips = new Thread[floorplan.allTheRequestors.size()];
		for (Requestor passenger: floorplan.allTheRequestors)
		{
			Runnable trip = new Trip(passenger, floorplan, triplog);
			trips[counter++] = new Thread(trip);
		}
		for (Thread trip: trips)
		{
			trip.start();	
		}
		for (Thread trip: trips)
		{
			try {
				trip.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	
	private static void finalstates(UberMap map) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("finaloutput.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.printf("Sucessful Trips: %d, Failed Trips: %d%n", 
				map.sucessfulRides, map.failedRides);
		writer.flush();
		for  (Driver carGuy: map.allTheDrivers)
		{
			carGuy.printUser(writer);
			writer.printf("%n");
			writer.flush();
		}
		for  (Requestor rider: map.allTheRequestors)
		{
			rider.printUser(writer);
			writer.printf("%n");
			writer.flush();
		}
	}
	
}