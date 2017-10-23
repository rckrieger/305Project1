import java.io.PrintWriter;
import java.util.Random;

public class Trip implements Runnable{
	private Requestor customer;
	UberMap map;
	PrintWriter writer;
	public Trip (Requestor customer, UberMap map, PrintWriter triplog) {
		 this.customer = customer;
		 this.map = map;
		 this.writer = triplog;
	}

	@Override
	public void run() {
		Random randGenerator = new Random();
		try {
			if (map.validRequest(customer))
			{
				writer.printf("%s picked a valid drop off%n", 
						customer.getName());
				writer.flush();
			}
			else
			{
				//unreachable b/c of how I structured my code
				writer.printf("%s did not pick a valid drop off%n", 
						customer.getName());
				writer.flush();
			}
			DriverDistTuple driverInfo = map.sendDriver(customer, writer);
			if (driverInfo == null)
			{
				writer.printf("%s could not afford any rides%n", 
						customer.getName()); 
				writer.flush();

			}
			else {
				writer.printf("%s your driver %s accepted the ride and will"
						+ " be there in %.2f minutes %n", 
						customer.getName(), driverInfo.getDriver().getName(), 
						map.waitTime(driverInfo.getDist(), .7));
				writer.flush();
				writer.printf("%s your driver %s is on their way to you%n", 
						customer.getName(), driverInfo.getDriver().getName());
				writer.flush();
				Thread.sleep((int)map.waitTime(driverInfo.getDist(), 7.0));
				writer.printf("%s your driver %s is ready to pick you up%n", 
						customer.getName(), driverInfo.getDriver().getName());
				writer.flush();
				writer.printf("%s your driver %s is on their way to your "
						+ "destination%n", 
						customer.getName(), driverInfo.getDriver().getName());
				writer.flush();
				Thread.sleep((int)map.waitTime(map.distanceOfTrip(customer),
						.7));
				int score = randGenerator.nextInt(4) + 1;
				map.rideComplete(driverInfo.getDriver(), customer, score);
				writer.printf("%s your trip is complete.You rated %s %d "
						+ "stars. You have arrived at (%d, %d)%n", 
						customer.getName(), driverInfo.getDriver().getName(), 
						score, customer.getDropOff().getX(), 
						customer.getDropOff().getY());
				writer.flush();
				writer.flush();
			}
		}
		catch (InterruptedException exception)
		{
			exception.printStackTrace();
		}
			
	}
}