package pack1;

import java.util.ArrayList;

public abstract class Customer {
	
	private int custNo, tel;
	private String name, address, nationality;
	private ArrayList <Payment> payments;
	private ArrayList <Rental> rentals;
	
	
	public Customer(int custNo, String name, int tel, String address, String nationality) {
		this.custNo = custNo;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.nationality = nationality;
		this.payments = new ArrayList<Payment>();
		this.rentals = new ArrayList<Rental>();
		
	}
	
	public void addRental(Rental rental) {
		rentals.add(rental);
	}
	
	public void addPayment(Payment payment) {
		payments.add(payment);
	}
	
	public boolean isRentedbyThis(int vNo) {
		for (int i = 0; i < rentals.size(); i++) {
			if(rentals.get(i).getVehicleNo() == vNo) {
				if(!VehicleContainer.getVehicleByID(vNo).isAvailable())
					return true;
			}
			return false;
		}
		
		return false;
	}

	public Rental getRental (int vNo) {
		for (int i = 0; i < rentals.size(); i++) {
			if (rentals.get(i).getVehicleNo() == vNo)
				return rentals.get(i);
		}
		return null;
	}
	
	public static void recordDetails(Customer cust) {
		CustomerContainer.addCustomer(cust);
	}
	
	public void returnVehichle(int vNo, int damage) {
		for (int i = 0; i < rentals.size(); i++) {
			if(rentals.get(i).getVehicleNo() == vNo) 
				rentals.get(i).processReturn(damage);
		}
	}
	
	public void deductFromLastPayment(int vNo) {
		for (int i = 0; i < rentals.size(); i++) {
			if(rentals.get(i).getVehicleNo() == vNo) {
				payments.get(payments.size()-1).payDeductions(rentals.get(i).getDamageDeduction() + rentals.get(i).getLatenessDeduction());;
			}
		}
	}
	public static Customer findCustomer (int custNo) {
		return CustomerContainer.getCustomerByID(custNo);
	}
	
	public static boolean editCustomer (Customer customer)
	{
		return CustomerContainer.editCustomer(customer);
	}
	
	public String getCustomerRentals () {
		String rentalsStr = "";
		
		if(!rentals.isEmpty()) {
			rentalsStr += "Customer Details: \n" + this.toString() + "\nRentals for this customer:\n";
			for (int i = 0; i < rentals.size(); i++) {
				rentalsStr += i+1 + "- " + rentals.get(i) + "Vehicle: " + Vehicle.findVehicle(rentals.get(i).getVehicleNo()) + "\n";
			}
		}
		return rentalsStr;
	}
	
	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		//TODO fix spacing
		return String.format("|Customer Number: %d|Name: %s|Tel: %d|Address: %s|Nationality: %s|", custNo, name, tel, address, nationality);
	}
}
