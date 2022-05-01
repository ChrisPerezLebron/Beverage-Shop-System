
public class Smoothie extends Beverage{
	private int numOfFruits;
	private boolean hasProtein; 
	private final double PROTEIN_PRICE = 1.50; //$1.50 for protein 
	private final double FRUIT_PRICE = .50; //50 cent per fruit
	
	public Smoothie(String name, SIZE size, int nFruit, boolean protein) {
		super(name, TYPE.SMOOTHIE, size);
		this.numOfFruits = nFruit;
		this.hasProtein = protein;
		}
	
	public Smoothie(Smoothie s) {
		super(s.getBevName(), TYPE.SMOOTHIE, s.getSize());
		this.numOfFruits = s.numOfFruits;
		this.hasProtein = s.hasProtein; 
	}
	
	@Override
	public String toString() {
		return  super.toString() + 
				"\nHas Protein: " + this.hasProtein +
				"\nNumber of Fruits: " + this.numOfFruits + 
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
		
		//increase price based on protein
		if(hasProtein)
			price += PROTEIN_PRICE;
		
		//increase price base on number of fruits
		price += numOfFruits * FRUIT_PRICE;
		
		return price;
	}
	
	public boolean equals(Smoothie smooth) {
		return (super.equals(smooth) && this.numOfFruits == smooth.numOfFruits && this.hasProtein == smooth.hasProtein);
	}
	
	public boolean getAddProtein() {
		return this.hasProtein;
	}
	
	public void setAddProtein(boolean protein) {
		this.hasProtein = protein;
	}
	
	public int getNumOfFruits() {
		return this.numOfFruits;
	}
	
	public void setNumOfFruits(int nFruit) {
		this.numOfFruits = nFruit;
	}
	
	public double getProteinPrice() {
		return this.PROTEIN_PRICE;
	}
	
	public double getFruitPrice() {
		return this.FRUIT_PRICE;
	}
}
