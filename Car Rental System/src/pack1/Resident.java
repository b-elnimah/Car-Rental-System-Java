package pack1;

public class Resident extends Customer {
	
	private int idCard;
	private String bankName;
	
	public Resident(int custNo, String name, int tel, String address, String nationality, int idCard, String bankName) {
		super(custNo, name, tel, address, nationality);
		this.idCard = idCard;
		this.bankName = bankName;
	}
	
	public void recordDetails() {
		
	}
	
	@Override
	public String toString() {
		// TODO fix spacing
		return super.toString() + String.format("Residency: Resident|ID/Passport: %d|Bank: %s|Visit Start: N/A|Visit End: N/A|", idCard, bankName);
	}

}

