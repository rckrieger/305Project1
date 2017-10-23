import java.util.ArrayList;

public class ArrayListTuple {
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private ArrayList<Requestor> riders = new ArrayList<Requestor>();
	ArrayListTuple(ArrayList<Driver> drivers, ArrayList<Requestor> riders){
		this.drivers = drivers;
		this.riders = riders;
	}
	public ArrayList<Driver> getDrivers() {
		return drivers;
	}
	public ArrayList<Requestor> getRiders() {
		return riders;
	}
}
