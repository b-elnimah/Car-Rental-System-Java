package pack1;

import java.util.ArrayList;

public class VehicleContainer {
	
	private static ArrayList <Vehicle> vehicles = new ArrayList<Vehicle>();
	
	public static void addVehicle(Vehicle vehicle) {
		System.out.println("adding vehicle");
		vehicles.add(vehicle);
	}
	
	public static String getVehicleStringByID (int vehicleNo) {
		String vehicleString = "";
		
		for (int i = 0; i < vehicles.size(); i++) {
			if(vehicles.get(i).getVehicleNo() == vehicleNo) 
				return vehicleString + vehicles.get(i).toString();
		}
		
		return null;
	}
	
	public static Vehicle getVehicleByID (int vehicleNo) {
		for (int i = 0; i < vehicles.size(); i++) {
			if(vehicles.get(i).getVehicleNo() == vehicleNo) 
				return vehicles.get(i);
		}
		
		return null;
	}
	
	public static String getAllVehicles () {
		String vehicleString =  "";
		
		for (int i = 0; i < vehicles.size(); i++) {
			vehicleString += vehicles.get(i).toString() + "\n";
		}
		
		return vehicleString;
	}
	
	public static String getAllRentedVehicles() {
		String vehicleString =  "";

		for (int i = 0; i < vehicles.size(); i++) {
			if(!vehicles.get(i).isAvailable())
				vehicleString += vehicles.get(i).toString() + "\n";
		}
		
		return vehicleString;
	}
	
	public static void makeUnavailable(int vNo) {
		for (int i = 0; i < vehicles.size(); i++) {
			if(vehicles.get(i).getVehicleNo() == vNo) 
				vehicles.get(i).setAvailable(false);
			
		}
	}
	
	public static void makeAvailable(int vNo) {
		for (int i = 0; i < vehicles.size(); i++) {
			if(vehicles.get(i).getVehicleNo() == vNo)
				vehicles.get(i).setAvailable(true);
		}
	}
}
