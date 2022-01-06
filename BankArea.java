package Project;

import java.util.*;

//import Project.Customer;
//import Project.Teller;

public class BankArea {
	
	private int teller;
	private int queueLength;
	
	public BankArea(int teller, int queueLength, int tellerID) {
		this.teller = teller;
		this.queueLength = queueLength;
		
		for(int i=0; i< teller;i++) {
			addfreeTeller( new Teller(tellerID++));//adding all tellers to free tellers queue
		}
	}
	
	
	Queue<Customer> cust1 = new LinkedList<>();// waiting queue 1
	Queue<Customer> cust2 = new LinkedList<>();// waiting queue 2
	ArrayList<Customer> serviceCust = new ArrayList<>();// pseudo service list
	Queue<Teller> freeTQ = new LinkedList<>();// free teller queue
	Queue<Teller> busyTQ = new LinkedList<>();// busy teller queue
	
	public void addfreeTeller(Teller t) {
		freeTQ.add(t);
	}
	
	public int queue1Size(){
		return cust1.size();
	}
	public int queue2Size(){
		return cust2.size();
	}
	
	public void addintoWaitingQueue1(Customer c) {
		cust1.add(c);
	}
	public void addintoWaitingQueue2(Customer c) {
		cust2.add(c);
	}
	
	 public void addintoServiceList(Customer c) {
			serviceCust.add(c);
		}
	 public void removefromoWaitingQueue1(Customer c) {
		cust1.remove(c);
	}
	 public void removefromoWaitingQueue2(Customer c) {
			cust2.remove(c);
		}
	 
	  //returning min departure time of the customer from the pseudo service list 
	 public double getMindeparture(){
	  double min = serviceCust.get(0).getDTime();
	  int j=0;
	 	for(int i=0; i<serviceCust.size();i++){
	 		
	 		if(serviceCust.get(i).getDTime() < min) {
	 			min = serviceCust.get(i).getDTime();
	 			j=i;
	 		}
	 	}
	 	serviceCust.remove(j);
	 	return min;
	 	
	 }
	 public void removefromServiceList(Customer c) {
			serviceCust.remove(c);
		}
	 
	 //returning array list of customers in Waiting queue 1
	 public ArrayList<Customer> custIdCheckQ1() {
			ArrayList<Customer> arrQ1 = new ArrayList<>(cust1);
			return arrQ1;
		}
	 
	//returning array list of customers in Waiting queue 2
	 public ArrayList<Customer> custIdCheckQ2() {
			ArrayList<Customer> arrQ2 = new ArrayList<>(cust2);
			return arrQ2;
		}
	 
	 //returning the teller id serving the customer and adding the teller into busy teller queue
	 public int customerService(Customer c) {
		 int tID=0;
		 //checking whether free teller queue is not empty or full
		 if(freeTQ.size() > 0 && freeTQ.size() <= teller) {
			 tID=freeTQ.remove().getTellerID();// removing that teller from free queue
			 busyTQ.add(new Teller(tID,c.getCustID()));// adding that teller to busy queue and allot it to that customer
		 }
		 return tID;
	 }
	 
	 //removing customer from the system and freeing the teller
	 public void removeService(Customer c) {
		 //checking whether busy teller queue is not empty or full
		 if(busyTQ.size() > 0 && busyTQ.size() <= teller) {
			 Iterator<Teller> itr = busyTQ.iterator();
				while(itr.hasNext()) {
					Teller obj1 = itr.next();
					if(obj1.getCustomerID() == c.getCustID()) {
						freeTQ.add(new Teller(obj1.getTellerID()));// adding the teller to free teller queue and removing the customer
						busyTQ.remove(obj1);// remove teller from busy teller queue
						break;
					}
				}
		 }
	 }
	 
	 //remove customer from waiting queue 1
	 public void deleteCustomerfromQ1withID(Customer c){
			Iterator<Customer> itr = cust1.iterator();
			while(itr.hasNext()) {
				Customer obj1 = itr.next();
				if(obj1.getCustID() == c.getCustID()) {
					cust1.remove(obj1);
					break;
				}
			}
		}
	 
	 //remove customer from waiting queue 2
	 public void deleteCustomerfromQ2withID(Customer c){
			Iterator<Customer> itr = cust2.iterator();
			while(itr.hasNext()) {
				Customer obj2 = itr.next();
				if(obj2.getCustID() == c.getCustID()) {
					cust2.remove(obj2);
					break;
				}
			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
