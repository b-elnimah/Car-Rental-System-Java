package pack1;

import java.util.ArrayList;

public abstract class Vehicle {

	private int vehicleNo, modelYear;
	private String make, brand, model;
	private boolean available;
	private double dailyRate;
	private ArrayList <Rental> rentals;
	
	public Vehicle(int vehicleNo, String make, String brand, String model, int modelYear, boolean available,
			double dailyRate) {
		this.vehicleNo = vehicleNo;
		this.make = make;
		this.brand = brand;
		this.model = model;
		this.modelYear = modelYear;
		this.available = available;
		this.dailyRate = dailyRate;
		this.rentals = new ArrayList <Rental>();
	}
	
	public double getCharges(int days) {
		return this.getDailyRate() * days;
	}
	
	public static Vehicle findVehicle(int vNo) {
		return VehicleContainer.getVehicleByID(vNo);
	}
	
	public static void registerVehicle(Vehicle vehicle) {
		VehicleContainer.addVehicle(vehicle);
	}
	
	public void addRental(Rental rental) {
		this.rentals.add(rental);
	}
	
	public String getRentals () {
		
		//TODO: fix spacing
		String rentalsStr = "|Start|Days|End|Lateness Fee|Damage Fee|\n";
		
		if(!this.rentals.isEmpty()) {
			
			for (int i = 0; i < this.rentals.size(); i++) {
				rentalsStr += this.rentals.get(i).toString();
			}
			
			return rentalsStr;
		}
		
		else 
			return null;
	}
	
	public static void makeUnavailable(int vNo) {
		VehicleContainer.makeUnavailable(vNo);
	}

	public static void makeAvailable(int vNo) {
		VehicleContainer.makeAvailable(vNo);
	}
	public int getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(int vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	@Override
	public String toString() {
		// TODO fix spacing
		return String.format("|Number: %d|Make: %s|Brand: %s|Model: %s|Year: %d|Availablity: %b|Rate:%f", vehicleNo, make, brand, model, modelYear, available, dailyRate);
	}
}

