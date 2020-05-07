package pack1;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class runner {

	public static void main(String[] args) throws ParseException {
		

		Vehicle v1 = new Bus(111,"Tata","Tata","RX12",2007,true,400.00,15,"Bangladesh"); 
		Vehicle v2 = new Car(222,"BMW","BMW","RX12",2007,true,400.00,"Sedan","Automatic","V12");
		Vehicle.registerVehicle(v1);
		Vehicle.registerVehicle(v2);
		

		Customer c1 = new Resident(1111,"Khalood",66557265,"Al Markhiya","Qatari",123456789,"QNB");
		Customer c2 = new Vistor(2222,"Khalood",66557265,"Al Markhiya","Qatari","PF20128344",20190102,20190503);
		Customer.recordDetails(c1);
		Customer.recordDetails(c2);;

		System.out.println("here\n");
		System.out.println(c1);
		Scanner input=new Scanner(System.in);

		int menuChoice;
		boolean again=true;

		do {
			System.out.println("\nWelcome to the Car Rental System\n");
			System.out.println("1.Add new vehicle\n"
					+ "2.Add customer\n"
					+ "3.Rent a Car\n"
					+ "4.Return a Vehicle\n"
					+ "5.Iquire about a specific Vehicle\n"
					+ "6.Inquire about all rented Vehicles\n"
					+ "7.Iquire about customers\n"
					+ "8.Produce tabular list of all Vehciles and Customers\n"
					+ "9.Produce detailed tabular lists of all rented vehicles names of customers who rented them and expected dates of returns\n"
					+ "10.Quit System\n");
			System.out.print("Please select an option form the menu:");
			menuChoice=input.nextInt();

			switch(menuChoice)
			{
			case 1: 																					// Add New Vehicle
				System.out.println("Select the type of type of vehicle you would like to add\n"
					+ "1. Add a car\n"
					+ "2. Add a bus\n");
			System.out.print("Choice:");
			menuChoice=input.nextInt();
			if(menuChoice==1 || menuChoice == 2)
			{
				
				
				Vehicle temp= enterVehicleDetails(menuChoice);
				temp.registerVehicle(temp);
				System.out.println("Vehicle Added");
				System.out.println(VehicleContainer.getVehicleStringByID(temp.getVehicleNo()));
				
			}	
			
			else
				System.out.println("INVALID CHOICE RETURNING TO THE MAIN MENU!!!");

			break;
			case 2:																						//Add new Customer
				System.out.println("Select the type of type of Customer you would like to add\n"
					+ "1. Add a resident\n"
					+ "2. Add a vistor\n");
			System.out.print("Choice:");
			menuChoice=input.nextInt();
			
			if(menuChoice==1 || menuChoice == 2)
			{
				
				Customer temp= enterCustDetails(menuChoice);
				Customer.recordDetails(temp);
			}	
			
			else
				System.out.println("INVALID CHOICE RETURNING TO THE MAIN MENU!!!");
				break;
				
			case 3: 																					// rent vehicle 
				System.out.println("Please enter renter's customer number: ");
				int renterNo = input.nextInt();
				Customer renter = Customer.findCustomer(renterNo);
				
				while(renter == null) {
					System.out.println("Customer not found, please try again: ");
					renterNo = input.nextInt();
					renter = Customer.findCustomer(renterNo);
				}
				
				System.out.println("Please enter the vehicle's number: ");
				int vNo = input.nextInt();
				Vehicle rentedVehicle = Vehicle.findVehicle(vNo);
				
				while(rentedVehicle == null) {
					System.out.println("Vehicle not found, please try again: ");
					vNo = input.nextInt();
					rentedVehicle = Vehicle.findVehicle(vNo);
				}		
				
				if(!isRentValid(renter, rentedVehicle)) 
					System.out.println("Unable to rent vehicle");	
				
				else {
					System.out.println("Please enter the number of days to rent: ");
					int days = input.nextInt();
					createRental(renter, rentedVehicle, days);
				}
				
				break;
			case 4:																						// return vehicle
				System.out.println("Please enter Customer Number:");
				int returnerNo = input.nextInt();
				Customer returner = Customer.findCustomer(returnerNo);
				
				while(returner == null) {
					System.out.println("Customer not found, please try again: ");
					returnerNo = input.nextInt();
					returner = Customer.findCustomer(returnerNo);
				}
				
				System.out.println("Please enter Vehicle Number: ");
				
				int rNo = input.nextInt();
				Vehicle returningVehicle = Vehicle.findVehicle(rNo);
				
				while(returningVehicle == null) {
					System.out.println("Vehicle not found, please try again: ");
					rNo = input.nextInt();
					returningVehicle = Vehicle.findVehicle(rNo);
				}
				
				if(!returner.isRentedbyThis(rNo))
					System.out.println("Vehicle Not Currently Assosiated With Customer");
				else 
				{
					System.out.println("Please enter the ammount of damage done to the vehicle: ");
					int damage = input.nextInt();
					initiateReturn(returner, rNo, damage);
					System.out.println("\nCustomer:\n" + returner + "\n\nRental: " + returner.getRental(rNo) );
				}

				break;
			case 5:
				System.out.println("Please enter a vehicle number: ");
				int inputNo;
				inputNo = input.nextInt();
				if(VehicleContainer.getVehicleByID(inputNo) != null) {
					System.out.println(VehicleContainer.getVehicleStringByID(inputNo));
				}
				
				else {
					System.err.println("Vehicle Not Found!");
				}
				
				break;
			case 6:
	
				System.out.println(VehicleContainer.getAllRentedVehicles());
				
				break;
			case 7:
				
				System.out.println("Please Enter Customer Number:");
				int custNoin = input.nextInt();
				if(CustomerContainer.getCustomerByID(custNoin) != null)
					System.out.println(CustomerContainer.getCustomerStringByID(custNoin));
				else
					System.err.println("Customer Not Found");
				
				break;
			case 8:
				System.out.println("Vehicle List:-");
				System.out.println(VehicleContainer.getAllVehicles());
				System.out.println("Customer List:-");
				System.out.println(CustomerContainer.getAllCustomers());
				break;
			case 9:
				System.out.println(CustomerContainer.getCustomersWithVehicles());
				break;
			case 10: again=false;
			break;
			default: System.out.println("INVALID CHOICE RETURNING TO THE MAIN MENU!!!");

			}


		}while(again);
	}
	
	
	private static Customer enterCustDetails (int type) {
		
		Scanner input = new Scanner (System.in);
		int custNo, tel, idCard, visitStart, visitEnd;
		String name, address, nationality, bankName, passportNo;
		System.out.println("Please input the customer number:");
		custNo=input.nextInt();
		System.out.println("Please input the customer name:");
		input = new Scanner(System.in);
		name=input.nextLine();
		System.out.println("Please input a telephone number:");
		tel=input.nextInt();
		input = new Scanner(System.in);
		System.out.println("Please input an address:");
		address=input.nextLine();
		System.out.println("Please input the customer's nationality:");
		nationality=input.next();
		
		if (type == 1 ) {
			System.out.println("Please input the Customers ID number:");
			idCard=input.nextInt();
			System.out.println("Please input the Customers the Customers Bank's Name:");
			bankName=input.next();
			
			return new Resident(custNo, name, tel, address, nationality, idCard, bankName);
		}
			
			System.out.println("Please input the Customers passport number:");
			passportNo=input.next();
			System.out.println("Please input the start date the Customers of visit:");
			visitStart=input.nextInt();
			System.out.println("Please input the end date the Customers of visit:");
			visitEnd=input.nextInt();
			
			return new Vistor(custNo, name, tel, address, nationality, passportNo, visitStart, visitEnd);
		
	}
	
	
	private static Vehicle enterVehicleDetails(int type) {
		
		String bodyType, gearType, engineSize, make, brand, model, driverNationality;
		int modelYear, vehicleNo, numOfSeats;
		double dailyRate;
		
		Scanner input = new Scanner (System.in);

		System.out.println("Please enter the vehicle number:");
		vehicleNo=input.nextInt();
		System.out.println("Please enter the make:");
		make=input.next();
		System.out.println("Please enter the brand:");
		brand=input.next();
		System.out.println("Please enter the model:");
		model=input.next();
		System.out.println("Please enter the model year:");
		modelYear=input.nextInt();
		System.out.println("Please enter the daily rate:");
		dailyRate=input.nextDouble();
		
		if(type == 1) {
			System.out.println("Please enter the body type:");
			bodyType=input.next();
			System.out.println("Please enter the gear type:");
			gearType=input.next();
			System.out.println("Please enter the engine size:");
			engineSize=input.next();
			
			return new Car(vehicleNo,make,brand,model,modelYear,true,dailyRate,bodyType,gearType,engineSize);
		}
		
		System.out.println("Please enter the numer of seats:");
		numOfSeats=input.nextInt();
		System.out.println("Please enter the drivers Nationality:");
		driverNationality=input.next();
		
		return new Bus(vehicleNo,make,brand,model,modelYear,true,dailyRate,numOfSeats,driverNationality);
		
	}
	
	private static boolean isRentValid(Customer customer, Vehicle vehicle) {
		
		if(customer instanceof Vistor && vehicle instanceof Car && vehicle.isAvailable())
			return true;
		if(customer instanceof Resident && vehicle.isAvailable())
			return true;
			
		return false;
	}

	private static void createRental(Customer customer, Vehicle vehicle, int days) {
		
		Rental rental = new Rental (days, vehicle.getVehicleNo());
		customer.addRental(rental);
		
		if (customer instanceof Vistor) {
			Payment payment = new Payment (vehicle.getCharges(days), 15000);
			customer.addPayment(payment);
			System.out.println("Payment Reciept:\n" + payment);
		}
		
		Customer.editCustomer(customer);
		Vehicle.makeUnavailable(vehicle.getVehicleNo());
		
		System.out.println("\nCustomer:\n" + customer + "\n\nRental:\n" + rental + "\n\nPayment:\n");
	}
	

	private static void initiateReturn(Customer customer, int vNo, int damage) {
				 
		customer.returnVehichle(vNo, damage);
		
		if(customer instanceof Vistor) {
			customer.deductFromLastPayment(vNo);
		}
		
		else {
			Payment payment = new Payment(customer.getRental(vNo).getDamageDeduction() + customer.getRental(vNo).getLatenessDeduction() + Vehicle.findVehicle(vNo).getCharges(customer.getRental(vNo).getNumberOfDays()));
			customer.addPayment(payment);
		}
		
		Customer.editCustomer(customer);
	}

}
