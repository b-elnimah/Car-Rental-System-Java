package pack1;

import java.util.ArrayList;

public class CustomerContainer {
	
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public static void addCustomer (Customer customer) {
		customers.add(customer);
		System.out.println(getCustomerStringByID(customer.getCustNo()));
	}
	
	public static String getCustomerStringByID(int ID) {
		
		//TODO: fix spacing
		String custString = "";
		
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getCustNo() == ID) 
				return custString + customers.get(i);
		}
		
		return null;
	}
	
	public static Customer getCustomerByID(int ID) {
		for (int i = 0; i < customers.size(); i++) {
			
			if (customers.get(i).getCustNo() == ID) 
				return customers.get(i);
			
		}
		return null;
	}
	
	public static String getAllCustomers () {
		
		//TODO: fix spacing
		String custString = ""; 
		for (int i = 0; i < customers.size(); i++) {
			custString += customers.get(i).toString() + "\n";
		}
		
		return custString;
	}
	
	public static boolean editCustomer (Customer customer) {
		for (int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getCustNo() == customer.getCustNo()) {
				customers.set(i, customer);
				return true;
			}
		}
		return false;
	}
	
	public static String getCustomersWithVehicles () {
		String returnStr = "";
		for (int i = 0; i < customers.size(); i++) {
			returnStr += customers.get(i).getCustomerRentals();
		}
		
		return returnStr;
	}
	
}
//custNo| Name| tel | address| nationality| type| idcard/passport| bankName| visitStart| visitEnd