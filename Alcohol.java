
public class Alcohol extends Beverage{
	private boolean offeredOnWeekends;
	private double WEEKEND_PRICE = 0.60; //60 cent on weekends
	
	public Alcohol(String name, SIZE size, boolean weekends) {
		super(name, TYPE.ALCOHOL, size);
		this.offeredOnWeekends = weekends;
	}
	
	@Override
	public String toString() {
		return  super.toString() + 
				"\nSold On Weekends: " + this.offeredOnWeekends +
				"\nPrice: " + calcPrice(); 
	}
	
	@Override
	public double calcPrice() {
		double price = getBasePrice(); 
		
		//increase price based on size
		if(getSize().equals(SIZE.MEDIUM))
			price += getSIZE_UPGRADE_PRICE(); 
		else if(getSize().equals(SIZE.LARGE))
			price += 2 * getSIZE_UPGRADE_PRICE(); 
		
		//increase price based on if its the weekend
		if(offeredOnWeekends)
			price += WEEKEND_PRICE;
		
		return price;
	}
	
	public boolean equals(Alcohol alc) {
		return (super.equals(alc) && alc.offeredOnWeekends == this.offeredOnWeekends);
	}
	
	//get and set for offeredOnWeekends excluded
	
	public boolean getOfferedOnWeekends() {
		return this.offeredOnWeekends;
	}
	
	public void setOfferedOnWeekends(boolean weekends) {
		this.offeredOnWeekends = weekends; 
	}
	
	public double getWeekendPrice() {
		return this.WEEKEND_PRICE;
	}
	
}
