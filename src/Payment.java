public class Payment{
	double paymentRate;
	double driversCutRate = .75;
	
	public Payment(double pRate)
	{
		paymentRate = pRate;
	}
	
	
	public void paymentProcess(User customer, Driver driverPerson, double distance) {
		double fee = paymentRate * distance;
		if (checkBalance(customer, fee))
			makePayment(customer, driverPerson, fee);
		else {
			//TODO: write failure message
		}
			
	}
	
	public void dudeYouAreBroke() {
		
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
		driverPerson.setBalance(driverPerson.getBalance() + (fee * driversCutRate));
		customer.setBalance(customer.getBalance() - fee);
	}
}

