import java.math.*;
import java.lang.*;

public class Driver extends User {
	boolean occupied;
	String vehicle;
	double distAwayFromCustomer;
	public Driver(String userName, double balance, String title, boolean occupied) {
		super(title, balance);
		this.vehicle = title;
		this.occupied = occupied;
	}
	public void setDistAwayFromCustomer(double value) {
		distAwayFromCustomer = value;
	}
	public double getDistAwayFromCustomer() {
		return distAwayFromCustomer;
	}
	public boolean isOccupied() {
		return occupied;
	}
	
	public boolean acceptsRide() {
		return (Math.random() <= .1);
	}
	public boolean setOccupied(boolean value) {
		return occupied = value;
	}
}