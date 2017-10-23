import java.math.*;
public class Driver extends User {
	boolean occupied;;
	Car vehicle;
	double distAwayFromCustomer;
	public void setDistAwayFromCustomer(double value) {
		distAwayFromCustomer = value;
	}
	public double getDistAwayFromCustomer() {
		return distAwayFromCustomer;
	}
	
	
	public boolean acceptsRide() {
		return (Math.random() <= .1);
	}
}