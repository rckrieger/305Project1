import java.io.PrintWriter;

public class Payment{
	double paymentRate;
	double driversCutRate = .75;
	
	public Payment(double pRate)
	{
		paymentRate = pRate;
	}	
	
	public boolean paymentProcess(User customer, DriverDistTuple driverPerson,
			PrintWriter writer) {
		double fee = paymentRate * driverPerson.getDist();
		if (checkBalance(customer, fee))
		{
			makePayment(customer, driverPerson.getDriver(), fee);
			writer.printf("%s's payment transaction is performed, "
				+ "the cost was $%.2f%n", customer.getName(), fee);
			return true;
		}
		
		else {
			writer.printf("%s could not afford the ride with %s %n", 
				customer.getName(), driverPerson.getDriver().getName()); 	
			return false;
		}
			
	}
	
	public boolean checkBalance(User customer, double fee) {
		//check to see if the customer's balance is >= to trip cost
		if (Double.compare(customer.getBalance(), fee) > -1)
		{
			return true;
		}
		return false;
	}
	
	public void makePayment(User customer, Driver driverPerson, double fee)
	{
		driverPerson.setBalance(driverPerson.getBalance() + 
			(fee * driversCutRate));
		customer.setBalance(customer.getBalance() - fee);
	}
}

