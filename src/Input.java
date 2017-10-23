import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Input
{
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{
		ArrayList<Driver> drivers = new ArrayList();
		ArrayList<Requestor> riders = new ArrayList();
		File inputFile = null;
		Scanner inputStream  = null;
		try {
			inputFile = new File("test.txt") ;
		}
		catch(Exception e) {
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
		UberMap floorplan = new UberMap(drivers, riders);
		System.out.println("All done!");
		finalstates(floorplan);
		System.out.println("check output");

	}
	public static void finalstates(UberMap map) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("finaloutput.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.printf("Sucessful Trips: %d, Failed Trips: %d%n", map.sucessfulRides, map.failedRides);
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