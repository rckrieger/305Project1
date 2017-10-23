public abstract class User {
	String userName;
	//also currerent location or start location
	Coordinate pickUp;
	Coordinate dropOff;
	boolean beingHelped;
	double userRating;
	double balance;
	int phoneNumber;
	public User(String userName, double balance) {
		this.userName = userName;
		this.balance = balance;
	}
	public void setStart(Coordinate locale) {
		this.pickUp = locale;
	}
	public void setEnd(Coordinate locale) {
		this.dropOff = locale;
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
	public Coordinate getPickUp()
	{
		return pickUp;
	}
	public Coordinate getDropOff()
	{
		return dropOff;
	}
	
}