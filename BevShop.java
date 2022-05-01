import java.util.ArrayList;

public class BevShop implements BevShopInterfce{

	private int numAlcohol;
	private final int MAX_ALCOHOL = 3; //max num of alcohol bevs per order
	private ArrayList<Order> orderList;
	private final int MIN_AGE_ALCOHOL = 21; //21 is min age for alc
	private Order currentOrder; 
	private boolean currentOrderIsStored; //TRUE if currentOrder has been stored in orderList FALSE otherwise
	private int MAX_FRUIT = 6; 
	
	public BevShop() {
		this.numAlcohol = 0;
		this.orderList = new ArrayList<Order>();
	}
	
	public boolean validTime(int time) {
		boolean flag = false;
		if(time <= BevShopInterfce.MAX_TIME && time >= BevShopInterfce.MIN_TIME)
			flag = true;
		
		return flag;
	}
	
	
	public boolean eligbleForMore() {
		boolean flag = false;
		if(numAlcohol < MAX_ALCOHOL)
			flag = true;
		
		return flag;
	}
	
	public boolean validAge(int age) {
		boolean flag = false;
		if(age > MIN_AGE_ALCOHOL)
			flag = true;
		
		return flag;
	}
	
	public void startNewOrder(int time, DAY day, String customerName, int customerAge) {
		storeCurrentOrder();
		
		currentOrder = new Order(time, day, new Customer(customerName, customerAge));
		numAlcohol = 0;
		currentOrderIsStored = false;
	}
	
	public void processCoffeeOrder(String bevName, SIZE size, boolean extraShot, boolean extraSyrup) {
		if(currentOrder != null)
			currentOrder.addNewBeverage(bevName, size, extraShot, extraSyrup);
	}
	
	public void processAlcoholOrder(String bevName, SIZE size) {
		if(currentOrder != null && eligibleForMore() &&
			currentOrder.getCustomer().getAge() >= MIN_AGE_ALCOHOL) {
			
			currentOrder.addNewBeverage(bevName, size);
			numAlcohol++;
		}		
	}
	
	public void processSmoothieOrder(String bevName, SIZE size, int numOfFruits, boolean addProtein) {
		if(currentOrder != null && !isMaxFruit(numOfFruits))
			currentOrder.addNewBeverage(bevName, size, numOfFruits, addProtein);
		 if(currentOrder == null)
			System.out.println("Current Order does not exist. Start new order before processing a beverage.");
	}
	
	public boolean isMaxFruit(int numFruit) {
		boolean flag = false;
		if(numFruit >= MAX_FRUIT)
			flag = true;
		
		return flag;
	}
	
	public void sortOrders() {
		storeCurrentOrder();
		
		//using selection sort algorithm 
		int pos;
		Order tempOrder; 
		
		//outer loop determines start position
		for(int i = 0; i < orderList.size(); i++) {
			
			pos = i; 
			//inner loop finds lowest value between start position and eng of array
			for(int j = i + 1; j < orderList.size(); j++) 
				if(orderList.get(j).getOrderNo() < orderList.get(i).getOrderNo())
					pos = j; 
			
			tempOrder = orderList.get(pos);
			orderList.set(pos, orderList.get(i));
			orderList.set(i, tempOrder);
		}
	}
	
	public int findOrder(int orderNo) {
		storeCurrentOrder();
		int pos = -1;
		boolean notFound = true;
		int count = 0;
		while(count < orderList.size() && notFound) {
			if(orderList.get(count).getOrderNo() == orderNo) {
				notFound = false;
				pos = count;
			}
			
			count++;
		}
		
		return pos;
		/*
		 * I thought using binary search was better because we have a sortOrders method but the JUnit tests specifically desires no sorting prior to fndOrder's return.
		 * this could of been avoided with more info. This class overall did not have much guidance but is tested in a very specific manner via the JUnit tests. 
		sortOrders(); 
		
		int index = -1; 
		
		//binary search algorithm
		int low = 0;
		int high = orderList.size();
		
		while(low <= high) {
			int mid = low + ((high-low)/2);
			if(orderList.get(mid).getOrderNo() < orderNo)
				low = mid + 1;
			else if(orderList.get(mid).getOrderNo() > orderNo)
				high = mid -1; 
			else if(orderList.get(mid).getOrderNo() == orderNo)
				index = mid;
				break; 
		}
		
		return index;
		*/
	}
	
	public Order getCurrentOrder() {
		return new Order(currentOrder);
	}
	
	public int getNumOfAlcoholDrink() {
		return this.numAlcohol;
	}
	
	public int totalNumOfMonthlyOrders() {
		storeCurrentOrder();
		
		int size = orderList.size();
		
		if(size <= 30)
			return size;
		
		//if more than one month of orders subtract a month of orders until it has less than a month of orders
		while(size > 30)
			size -= 30;
		
		return size;
	}
	
	public Order getOrderAtIndex(int index) {
		storeCurrentOrder();
		
		return new Order(orderList.get(index));
	}
	
	public double totalMonthlySale() {
		storeCurrentOrder();
		
		double total = 0;
		
		int count = 0;
		while(count < orderList.size() && count <= 30) {
			total += orderList.get(count).calcOrderTotal();
			count++;
		}
		return total;
	}
	
	public double totalOrderPrice(int orderNo) {
		return orderList.get(findOrder(orderNo)).calcOrderTotal(); 
	}
	
	public boolean eligibleForMore() {
		return (numAlcohol < MAX_ALCOHOL); 
	}
	
	public String toString() {
		storeCurrentOrder();
		
		String str = "All Orders:" +"\n"; 
		for(Order o : orderList)
			str += o.toString() + "\n";
		
		str += "Sales for Last Month: " + totalMonthlySale(); 
		
		return str;
			
	}
	
	/*this stores the current order.
	 * issue is that if you run a method that stores the order then add a beverage to the current order it will not reflect within the beverageList
	 */
	public void storeCurrentOrder() {
		if(currentOrder != null && !(currentOrderIsStored)) {
			orderList.add(currentOrder);
			currentOrderIsStored = true;
		}
	}
	
	public int getMinAgeForAlcohol() {
		return this.MIN_AGE_ALCOHOL;
	}
	
	public int getMaxOrderForAlcohol() {
		return this.MAX_ALCOHOL;
	}
}
