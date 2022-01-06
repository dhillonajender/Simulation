package Project;

public class Teller {
	
	private int tellerID;
	private int customerID;
	
	public int getTellerID() {
		return tellerID;
	}
	public void setTellerID(int tellerID) {
		this.tellerID = tellerID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public Teller(int tellerID,int customerID)
    {
        this.tellerID = tellerID;
        this.customerID=customerID;
    }
	public Teller(int tellerID) {
		this.tellerID = tellerID;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
