
public class Coffee extends Beverage {
	private boolean hasExtraShot;
	private boolean hasExtraSyrup;
	private final double EXTRAS_PRICE = .50; //50 cent for a extra shot or extra syrup
	
	public Coffee(String name, SIZE size, boolean extraShot, boolean extraSyrup) {
		super(name, TYPE.COFFEE, size);
		this.hasExtraShot = extraShot;
		this.hasExtraSyrup = extraSyrup;
	}
	
	@Override
	public String toString() {
		return  super.toString() + 
				"\nExtra Shot: " + this.hasExtraShot +
				"\nExtra Syrup: " + this.hasExtraSyrup + 
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
		
		//increase price based on extras
		if(hasExtraShot)
			price += EXTRAS_PRICE;
		if(hasExtraSyrup)
			price += EXTRAS_PRICE;
		
		return price;
	}
	
	public boolean equals(Coffee cof) {
		return (super.equals(cof) && cof.hasExtraShot == this.hasExtraShot && cof.hasExtraSyrup == this.hasExtraSyrup);
	}
	
	public boolean getExtraShot() {
		return this.hasExtraShot;
	}
	
	public void setExtraShot(boolean extraShot) {
		this.hasExtraShot = extraShot;
	}
	
	public boolean getExtraSyrup() {
		return this.hasExtraSyrup;
	}
	
	public void setExtraSyrup(boolean extraSyrup) {
		this.hasExtraSyrup = extraSyrup;
	}
	
	public double getExtrasPrice() {
		return this.EXTRAS_PRICE;
	}
	
	
	
	
}
