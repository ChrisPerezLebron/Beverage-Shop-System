
public abstract class Beverage {
	private String bevName;
	private TYPE type; 
	private SIZE size; 
	private final double BASE_PRICE = 2.0; //$2.0 starting price for all bevs
	private final double SIZE_UPGRADE_PRICE = 1.0; //$1.0 to go up a size. That is, no up charge for small, $1.00 extra for medium, and $2.00 extra for large
	
	public Beverage(String name, TYPE type, SIZE size) {
		this.bevName = name;
		this.type = type; 
		this.size = size;
	}
	
	public abstract double calcPrice();
	
	public String toString() {
		return  "Name: " + this.bevName +
				"\nSize: " + this.size;
	}
	
	public boolean equals(Beverage bev) {
		boolean flag = false;
		
		if(bev.bevName.equals(this.bevName) && 
				bev.type.equals(this.type) && 
				bev.size.equals(this.size))
			flag = true;
		return flag;
	}
	
	public void setBevName(String str) {
		this.bevName = str;
	}
	
	public String getBevName() {
		return this.bevName;
	}
	
	public void setType(TYPE type) {
		this.type = type;
	}
	
	public TYPE getType() {
		return this.type;
	}
	
	public void setSize(SIZE size) {
		this.size = size;
	}
	
	public SIZE getSize() {
		return this.size;
	}
	
	public double getBasePrice() {
		return this.BASE_PRICE;
	}
	
	public double getSIZE_UPGRADE_PRICE() {
		return this.SIZE_UPGRADE_PRICE;
	}
	
	
}
