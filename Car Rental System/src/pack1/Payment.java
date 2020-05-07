package pack1;

import java.time.LocalDate;

public class Payment {

	private LocalDate payDate;
	private double totalAmountPaid;
	private double totalDepositPaid;
	private double totalDepositReturned;
	
	public Payment(double totalAmountPaid, double totalDepositPaid) {
		payDate = LocalDate.now();
		this.totalDepositPaid = totalDepositPaid;
		this.totalAmountPaid = totalAmountPaid + this.totalDepositPaid;
		this.totalDepositReturned = 0;
		
		System.out.println("\nA payment was created:\n" + this.toString());
	}
	
	public Payment(double totalAmountPaid) {
		payDate = LocalDate.now();
		this.totalAmountPaid = totalAmountPaid;
		this.totalDepositPaid = 0;
		this.totalDepositReturned = 0;
		
		System.out.println("\nA payment was created:\n" + this.toString());
	}
	
	public void payDeductions(double deduction) {
		if(deduction >= this.totalDepositPaid) {
			this.totalDepositReturned = 0;
			this.totalAmountPaid += deduction;
		}
			
		else {
			this.totalDepositReturned = this.totalDepositPaid - deduction;
			this.totalAmountPaid -= this.totalDepositReturned;
		}
		
		System.out.println("\nA daduction was made from a preexisting payment:\n" + this.toString());
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("|Date: %s|Total Paid: %f|Deposit: %f|Returned: %f", payDate.toString(), totalAmountPaid, totalDepositPaid, totalDepositReturned);
	}
}
