package pack1;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class Rental {

	private LocalDate startDate;
	private int numberOfDays;
	private LocalDate returnDate;
	private double latenessDeduction;
	private double damageDeduction;
	private int vehicleNo;
	
	
	

	public Rental(LocalDate startDate, int numberOfDays, double latenessDeduction, double damageDeduction, int vehicleNo) {
		this.startDate = startDate;
		this.numberOfDays = numberOfDays;
		this.latenessDeduction = latenessDeduction;
		this.damageDeduction = damageDeduction;
		this.returnDate = null;
		this.vehicleNo = vehicleNo;
		
	}
	
	public Rental(int numberOfDays, double latenessDeduction, double damageDeduction, int vehicleNo) {
		this.startDate = LocalDate.now();
		this.numberOfDays = numberOfDays;
		this.latenessDeduction = latenessDeduction;
		this.damageDeduction = damageDeduction;
		this.returnDate = null;
		this.vehicleNo = vehicleNo;
	}
	
	public Rental(int nunmberOfDays, int vehicleNo) {
		this.startDate = LocalDate.now();
		this.numberOfDays = nunmberOfDays;
		this.vehicleNo = vehicleNo;
	}
	
	public void processReturn(int damage) {
		this.recordReturnDate();
		this.calcLatenessDeduction();
		this.calcDamageDeduction(damage);
		Vehicle.makeAvailable(this.vehicleNo);
	}
	public int calcOverduedays(LocalDate date) {
		long daysBetween = DAYS.between(startDate,date);
		return (int) (daysBetween - this.numberOfDays);
	}
	public void recordReturnDate() {
		this.returnDate = LocalDate.now();

	}
	public void calcLatenessDeduction() { 
		this.latenessDeduction =  this.calcOverduedays(this.returnDate) * Vehicle.findVehicle(this.vehicleNo).getDailyRate() * 1.5;
	}
	public void calcDamageDeduction(int damage) {
		//No information about damage deduction in project. calculated by multiplying damage by 2.5
		this.damageDeduction = damage*2.5;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public double getLatenessDeduction() {
		return latenessDeduction;
	}

	public void setLatenessDeduction(double latenessDeduction) {
		this.latenessDeduction = latenessDeduction;
	}

	public double getDamageDeduction() {
		return damageDeduction;
	}

	public void setDamageDeduction(double damageDeduction) {
		this.damageDeduction = damageDeduction;
	}

	public int getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(int vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	@Override
	public String toString() {
		// TODO fix spacing
		return String.format("|Start Date: %s|Days: %d|Return Date: %s|Lateness: %f|Damage: %f|\n", this.startDate.toString(),numberOfDays,(returnDate == null) ? "N/A" : returnDate.toString(),latenessDeduction,damageDeduction);
	}
	
}
