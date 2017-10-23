public abstract class User {
	String userName;
	//also currerent location or start location
	Coordinate pickUp;
	Coordinate dropOff;
	boolean beingHelped;
	double userRating;
	double balance;
	int phoneNumber;

	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double money)
	{
		this.balance = money;
	}
	public Coordinate getPickUp()
	{
		return pickUp;
	}
	public Coordinate getDropOff()
	{
		return dropOff;
	}
	
}