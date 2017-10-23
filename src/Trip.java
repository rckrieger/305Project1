import java.io.PrintWriter;

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
		System.out.printf("a thread began for %s%n", customer.getName());
		try {
			if (map.validRequest(customer))
			{
				writer.printf("%s picked a valid drop off%n", customer.getName());
				writer.flush();
			}
			else
			{
				writer.printf("%s did not pick a valid drop off%n", customer.getName());
				writer.flush();

				//Todo: reset endpt
			}
			DriverDistTuple driverInfo = map.sendDriver(customer);
			if (driverInfo == null)
			{
				writer.printf("%s could not afford any rides%n", customer.getName()); 
				writer.flush();

			}
			else {
				writer.printf("%s your driver %s accepted the ride and will be there in %.2f %n", 
						customer.getName(), driverInfo.getDriver().getName(), map.waitTime(driverInfo.getDist(), 15.0));
				writer.flush();
				writer.printf("%s your driver is on their way to you%n", customer.getName());
				writer.flush();
				Thread.sleep((int)map.waitTime(driverInfo.getDist(), 15.0));
				writer.printf("%s your driver is ready to pick you up%n", customer.getName());
				writer.flush();
				writer.printf("%s your driver is on their way to your destination%n", customer.getName());
				writer.flush();
				Thread.sleep((int)map.waitTime(map.distanceOfTrip(customer), 15.0));
				map.rideComplete(driverInfo.getDriver(), customer);
				writer.printf("%s your trip is complete%n", customer.getName());
				writer.flush();
			}
		}
		catch (InterruptedException exception)
		{
			exception.printStackTrace();
		}
			
	}
}