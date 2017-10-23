import java.io.PrintWriter;
import java.util.Random;
public abstract class User {
	String userName;
	//also current location or start location
	Coordinate pickUp;
	Coordinate dropOff;
	boolean beingHelped;
	double userRating;
	int numRatings;
	double balance;
	int phoneNumber;
	Random randGenerator = new Random();
	public User(String userName, double balance) {
		this.userName = userName;
		this.balance = balance;
		numRatings = 0;
		userRating = 5;
	}
	public void setStart(Coordinate locale) {
		this.pickUp = locale;
	}
	public void setEnd(Coordinate locale) {
		this.dropOff = locale;
	}
	public String getName() {
		return this.userName;
	}
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double money)
	{
		this.balance = money;
	}
	public double getRating()
	{
		return userRating;
	}
	public void setRating()
	{
		double total = numRatings * userRating + randGenerator.nextInt(4) + 1;
		this.userRating = total/ ((double) ++numRatings);
	}
	public Coordinate getPickUp()
	{
		return pickUp;
	}
	public Coordinate getDropOff()
	{
		return dropOff;
	}

	public void printUser(PrintWriter writer) {
		writer.printf("%s - Balance: %.2f Location: (%d,%d) ", userName, balance, pickUp.getX(), pickUp.getY());	
	}
	
}