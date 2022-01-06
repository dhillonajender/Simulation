package Project;

public class PrintStatistics {
	
	private int cID;
	private double aTime;
	private double serviceTime;
	private double deptTime;
	private double waitingTime;
	private int qId;
	private int tellerID;
	
	public PrintStatistics(int cID, double aTime, double serviceTime, double deptTime, double waitingTime, int qId,
			int tellerID) {
		super();
		this.cID = cID;
		this.aTime = aTime;
		this.serviceTime = serviceTime;
		this.deptTime = deptTime;
		this.waitingTime = waitingTime;
		this.qId = qId;
		this.tellerID = tellerID;
	}
	

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public double getaTime() {
		return aTime;
	}

	public void setaTime(double aTime) {
		this.aTime = aTime;
	}

	public double getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}

	public double getDeptTime() {
		return deptTime;
	}

	public void setDeptTime(double deptTime) {
		this.deptTime = deptTime;
	}

	public double getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public int getTellerID() {
		return tellerID;
	}

	public void setTellerID(int tellerID) {
		this.tellerID = tellerID;
	}
	
	
	

}
