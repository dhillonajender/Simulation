package Project;

public class Customer {

	private int custID;
	private double arrivalTime;
	private double sTime;
	private double dTime;
	private double wTime;
	
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	 public double getDTime() {
		return dTime;
	}
	public void setDTime(double wTime) {
		this.wTime = wTime;
	}
	 public double getWTime() {
			return dTime;
		}
	public void setWTime(double wTime) {
		this.wTime = wTime;
		}


	public double getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getSTime() {
		return sTime;
	}


	public void setSTime(double sTime) {
		this.sTime = sTime;
	}
	

	public Customer(int custID, double arrivalTime, double sTime, double dTime, double wTime) { 
		this.custID = custID;
		this.arrivalTime = arrivalTime;
		this.sTime = sTime;
		this.dTime = dTime; 
		this.wTime= wTime;
	}
	public Customer(int custID) {
		this.custID=custID;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
