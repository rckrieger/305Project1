public class Coordinate{
	public int xCoord;
	public int yCoord;
	public Coordinate(int xCoord, int yCoord)
	{
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	public int getX() {
		return xCoord;
	}
	public int getY() {
		return yCoord;
	}
	public boolean equals(Coordinate that) {
		if (this.getX() == that.getX() &&
			this.getY() == that.getY())
			return true;
		return false;
		
	}
}