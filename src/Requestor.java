public abstract class Requestor extends User{
	public Requestor(String userName, double balance){
		super(userName, balance);
	}
	public Boolean validRequest(UberMap myMap) {
		if (super.getDropOff().getY() > -1 &&
			super.getDropOff().getX() > -1 &&
			super.getDropOff().getY() < myMap.getNumCols() &&
			super.getDropOff().getX() < myMap.getNumRows())
		{
			return true;
		}
		return false;
	}
	
	
}