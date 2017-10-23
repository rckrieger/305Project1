public abstract class Requestor extends User{
	public Boolean validRequest(Map myMap) {
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