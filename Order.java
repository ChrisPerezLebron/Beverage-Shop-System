import java.util.ArrayList;
import java.util.Random;
public class Order implements OrderInterface, Comparable<Order>{
	private int orderNumber;
	private int orderTime; //holds time from 0 to 24 so military time
	private DAY day;
	private Customer customer;
	private ArrayList<Beverage> beverageList; 
	
	public void generateOrderNumber() {
		Random rand = new Random(); 
		this.orderNumber = rand.nextInt(80001) + 10000;
	}
	
	public Order(int time, DAY day, Customer cust) {
		generateOrderNumber();
		this.orderTime = time;
		this.day = day;
		this.customer = new Customer(cust); 
		this.beverageList = new ArrayList<Beverage>(); 
	}
	
	public Order(Order o) {
		this.orderNumber = o.orderNumber;
		this.orderTime = o.orderTime;
		this.day = o.day;
		this.customer = new Customer(o.customer); 
		//cant find a way to do a deep copy because this uses polymorphism to store the drinks
		this.beverageList = o.beverageList;
	}
	
	public void addNewBeverage(String bevName, SIZE size, boolean extraShot, boolean extraSyrup) {
		beverageList.add(new Coffee(bevName, size, extraShot, extraSyrup)); 
	}
	
	public void addNewBeverage(String bevName, SIZE size, int numOfFruits, boolean addProtein) {
		beverageList.add(new Smoothie(bevName, size, numOfFruits, addProtein)); 
	}
	
	public void addNewBeverage(String bevName, SIZE size) {
		boolean weekends = false;
		if(this.day.equals(DAY.SATURDAY) || this.day.equals(DAY.SUNDAY))
			weekends = true;
			
		beverageList.add(new Alcohol(bevName, size, weekends)); 
			
	}
	
	
	
	@Override
	public String toString() {
		String str =  "Order Number: " + this.orderNumber + 
					  "\nTime: " + this.orderTime + 
					  "\nDay: " + this.day + 
					  "\nCustomer Name: " + this.customer.getName() +
					  "\nCustomer Age: " + this.customer.getAge() +
					  "\nOrder Total: " + calcOrderTotal(); 
		for(Beverage bev : beverageList) {
			str += bev.toString(); 
		}
		
		return str;
	}
	
	
	public int compareTo(Order order) {
		int result = -100;  // because -100 is unreasonable and you will know something went wrong 
		
		if (this.orderNumber < order.orderNumber)
			result = -1;
		else if (this.orderNumber > order.orderNumber)
			result = 1;
		else if (this.orderNumber == order.orderNumber)
			result = 0;
		
		return result;
	}
	
	public void setOrderNo(int num) {
		this.orderNumber = num;
	}
	
	public int getOrderNo() {
		return this.orderNumber;
	}
	
	public void setCustomer(Customer cust) {
		this.customer = cust;
	}
	
	public Customer getCustomer() {
		return new Customer(this.customer);
	}
	
	public int getTotalItems() {
		return beverageList.size();
	}
	
	public double calcOrderTotal() {
		double total = 0;
		
		for(Beverage bev : beverageList)
			total += bev.calcPrice();
		
		return total;
	}
	
	public boolean isWeekend() {
		boolean weekends = false;
		if(this.day.equals(DAY.SATURDAY) || this.day.equals(DAY.SUNDAY))
			weekends = true;
		
		return weekends;
	}
	
	public int findNumOfBeveType(TYPE type) {
		int count = 0;
		
		for(Beverage bev : beverageList) 
			if(type.equals(bev.getType()))
				count++;
		
		return count;
	}
	
	public Beverage getBeverage(int itemNo) {
		//because no copy instructor exists that can take beverage objects and create smoothie etc object I cna only return a refrence copy.
		//however, interface states shallow copy. I assume this is what it means since beverage does not have a inner object to deep copy.
		return beverageList.get(itemNo);
	}
	
	public int getOrderTime() {
		return this.orderTime;
	}
	
	public void setOrderTime(int time) {
		this.orderTime = time;
	}
	
	public DAY getOrderDay() {
		return this.day;
	}
	
	public void setOrderDay(DAY orderDay) {
		this.day = orderDay;
	}
	
	
	
}
