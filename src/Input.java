import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Input
{
	public static void main(String[] args)
	{
		ArrayList<Driver> drivers = new ArrayList();
		ArrayList<Rider> riders = new ArrayList();
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
		//new UberMap(drivers, riders)
	}
}