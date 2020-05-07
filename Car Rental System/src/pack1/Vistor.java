package pack1;

public class Vistor extends Customer{
	
	private String passportNo;
	private int visitStart;
	private int visitEnd;
	
	public Vistor(int custNo, String name, int tel, String address, String nationality, String passportNo,
			int visitStart, int visitEnd) {
		super(custNo, name, tel, address, nationality);
		this.passportNo = passportNo;
		this.visitStart = visitStart;
		this.visitEnd = visitEnd;
	}
	
	public void recordDetails() {
		
	}
	
	@Override
	public String toString() {
		// TODO fix spacing
		return super.toString() + String.format("Residency: Visitor|ID/Passport: %s|Bank: N/A|Visit Start: %d|Visit End: %d|", passportNo,visitStart,visitEnd ) ;
		
	}
	
	

}
