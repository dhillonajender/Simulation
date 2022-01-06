package Project;

public class Event {
	private int custID;
	private double time;
	private char type;
	
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Event(int custID, double time, char type) {
		super();
		this.custID = custID;
		this.time = time;
		this.type = type;
	}
	public Event() {
		super();
	}
	
	
	
}
