public class DriverDistTuple implements Comparable<DriverDistTuple>{
		double dist;
		Driver dude;
		DriverDistTuple(Driver dude, double dist)
		{
			this.dist = dist;
			this.dude = dude;
		}
		public double getDist() {
			return this.dist;
		}
		public Driver getDriver() {
			return this.dude;
		}

		@Override
		public int compareTo(DriverDistTuple o) {
			if (this.getDist() == o.getDist())
			{	if(this.getDriver().getRating() > o.getDriver().getRating())
					return 1;
				else if (this.getDriver().getRating() < 
						o.getDriver().getRating())
					return -1;
				else
					return 0;
			}
			else if (this.getDist() < o.getDist())
				return -1;
			else {
				return 1;
			}
		}
		
	}