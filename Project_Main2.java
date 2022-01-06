package Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

//import Project.Customer;
//import Project.Event;

public class Project_Main2 {
	
	public static int tellers, maxQueueLength, customerID, notServed, served;
    
	public static double meanInterArrivalTime,simulationLength, meanServiceTime, interArrivalTime, serviceTime, arrival, clock,service,departure,delta,
	 waiting, dummyTime,waitTimeCounter, serviceTimeCounter;
	
	
	public BankArea bankArea;
	public static Random stream;
	public static LinkedList<Event> FEL = new LinkedList<>();
	public static LinkedList<PrintStatistics> printCust = new LinkedList<>();
	
	
	public static void initialization() {
	
		customerID = 0;
		arrival = 0;
		notServed = 0;
		served = 0;
		clock = 0;
		waitTimeCounter=0.0;
		serviceTimeCounter=0.0;
	}
	
	// generating inter arrival time and service time using exponential distribution
	public static void customerTimeData() {
		
		interArrivalTime = exponential(stream,meanInterArrivalTime);
		
		serviceTime = exponential(stream,meanServiceTime);
	}
	// fetching the last event from FEL
	public static double lastEventTime(LinkedList<Event> l) {
		double max = l.get(0).getTime();
		for(int i=0; i<l.size();i++){
		 	if(l.get(i).getTime() > max) {
		 		max = l.get(i).getTime();
		 	}
		 }
		return max; 
	}
	//fetching the imminent event from FEL
	public static Event minimumTimeEvent(LinkedList<Event> l) {
		double min = l.get(0).getTime();
		Event e = new Event();
		  int j=0;
		 	for(int i=0; i<l.size();i++){
		 		if(l.get(i).getTime() < min) {
		 			min = l.get(i).getTime();
		 			j=i;
		 		}
		 	}
		 	e = l.get(j);
		 	l.remove(j);
		 	return e;
	}
	
	public static void simulation() {
		
		BankArea bankArea = new BankArea(tellers, maxQueueLength, 1);
		
		double time=0;
		double delta = 0.0;
		// generating events and adding those events into FEL. 
		// prime idea is to calculate every event's statistics to do simulation in the next loop.
		while(time<=simulationLength){
			
			customerTimeData();// randomly generated inter-arrival and service time
			customerID++;// increasing the customer Id
			// for first 5 customers, waiting time will be zero as teller's count = 5
			if(customerID <= tellers)
				{
					time  = time + interArrivalTime;//arrival time
					service = serviceTime;
					serviceTimeCounter = serviceTimeCounter + serviceTime;
					departure = time + service;
					waiting = 0;
					waitTimeCounter=waitTimeCounter + waiting;
					FEL.add(new Event(customerID,time,'A'));// adding arrival event type with customer id and time into FEL
					FEL.add(new Event(customerID,time + waiting,'S'));
					// adding service event type with customer id and time into FEL,i.e when customer is ready to get served
					FEL.add(new Event(customerID,departure,'D'));// adding departure event type with customer id and time into FEL
					//adding customer into a pseudo service list so as to calculate the waiting time for the next customer and schedule the event accordingly	
					bankArea.addintoServiceList(new Customer(customerID,time,service,departure,waiting));
				 }
				else {
					time = time + interArrivalTime;//arrival time
					//time got updated so need to check the simulation length condition again
					if(time<=simulationLength) {
						//getting the min departure of the customers from pseudo service list to calculate the waiting time of arriving customer
						delta = bankArea.getMindeparture() - time;
						//if delta is positive then waiting time is delta otherwise it is zero.
						waiting = Math.max(0.0, delta);
						waitTimeCounter=waitTimeCounter + waiting;
						serviceTimeCounter = serviceTimeCounter + serviceTime;
						departure = time + waiting + serviceTime;
						FEL.add(new Event(customerID,time,'A'));
						FEL.add(new Event(customerID,time + waiting,'S'));     
						FEL.add(new Event(customerID,departure,'D'));
						bankArea.addintoServiceList(new Customer(customerID,time,serviceTime,departure,waiting));
						}
					}
	
		} 
		
		// doing the simulation according to the FEL event.
		double t =0.0;
		double lastEvent = lastEventTime(FEL);// fetching last event from FEL
		// simulation is going to run until we encounter last event
		while(t<lastEvent) { 
			Event event = new Event(); 
			event = minimumTimeEvent(FEL);// getting the imminent event from FEL
			t = event.getTime();// time of the event 
			
			// if event type is Arrival ======== add customer into shorter Waiting Queue
			if(event.getType() == 'A') {
				//if Queue 2 size > Queue 1 size and queue size is less than maxQueueLength then add customer into waiting Queue 1
				if((bankArea.queue2Size() > bankArea.queue1Size()) && ((bankArea.queue1Size() < maxQueueLength) && (bankArea.queue2Size() < maxQueueLength))) {
					bankArea.addintoWaitingQueue1(new Customer(event.getCustID()));
					printCust.add(new PrintStatistics(event.getCustID(),event.getTime(),0.0,0.0,0.0,1,0));
					//adding the object of PrintStatistics class(cID,arrivalTime,serviceTime,deptTime,waitingTime,qId,teller id) into printCust linked list 
				}
				//if Queue 1 size > Queue 2 size and queue size is less than maxQueueLength then add customer into waiting Queue 2
				else if((bankArea.queue1Size() > bankArea.queue2Size()) && ((bankArea.queue1Size() < maxQueueLength) && (bankArea.queue2Size() < maxQueueLength))) {
					bankArea.addintoWaitingQueue2(new Customer(event.getCustID()));
					printCust.add(new PrintStatistics(event.getCustID(),event.getTime(),0.0,0.0,0.0,2,0));
					}
				//if Queue 1 size = Queue 2 size and queue size is less than maxQueueLength then add customer into waiting Queue 1
				else if((bankArea.queue2Size() == bankArea.queue1Size()) && ((bankArea.queue1Size() < maxQueueLength) && (bankArea.queue2Size() < maxQueueLength))) {
					bankArea.addintoWaitingQueue1(new Customer(event.getCustID()));
					printCust.add(new PrintStatistics(event.getCustID(),event.getTime(),0.0,0.0,0.0,1,0));
					}
				else {
					notServed++;// when customer is not served
				}
			}
			
			// if event type is Service i.e customer is ready to get served ======== remove customer from Waiting queue and allot a teller
			else if(event.getType() == 'S'){
				int tID=0,k=0;
				// array list of customer currently in waiting queue 1
				ArrayList<Customer> arrQ1 = bankArea.custIdCheckQ1();
				for(int i = 0; i < arrQ1.size();i++) {
					// checking whether customer with event type = "S" is present in waiting queue1 or not 
					// if not then customer is not even present in the waiting queue then how it can be served
					if(arrQ1.get(i).getCustID() == event.getCustID()) {
						for(int j=0;j<printCust.size();j++) {
							if(printCust.get(j).getcID() == event.getCustID()) {
								k=j;
								// updating waiting time of PrintStatistics class for that particular customer in printCust linked list 
								printCust.get(j).setWaitingTime(event.getTime() - printCust.get(j).getaTime());
								break;
							}
						}
						bankArea.deleteCustomerfromQ1withID(new Customer(event.getCustID())); // remove from waiting Q1
						tID=bankArea.customerService(new Customer(event.getCustID())); // allot a Teller to customer for service and getting teller id
						printCust.get(k).setTellerID(tID);// updating teller id in PrintStatistics class for that particular customer in printCust linked list 
					}
				}
				// array list of customer currently in waiting queue 2
				ArrayList<Customer> arrQ2 = bankArea.custIdCheckQ2();
				for(int i = 0; i < arrQ2.size();i++) {
					// checking whether customer with event type = "S" is present in waiting queue2 or not 
					// if not then customer is not even present in the waiting queue then how it can be served
					if(arrQ2.get(i).getCustID() == event.getCustID()) {
						for(int j=0;j<printCust.size();j++) {
							if(printCust.get(j).getcID() == event.getCustID()) {
								k=j;
								// updating waiting time of PrintStatistics class for that particular customer in printCust linked list 
								printCust.get(j).setWaitingTime(event.getTime() - printCust.get(j).getaTime());
								break;
							}
						}
						bankArea.deleteCustomerfromQ2withID(new Customer(event.getCustID())); // remove customer from waiting Q2
						tID=bankArea.customerService(new Customer(event.getCustID()));// allot a Teller to customer for service and getting teller id
						printCust.get(k).setTellerID(tID);// updating teller id of PrintStatistics class for that particular customer in printCust linked list 
					}
				}
			}
			// if event type is Departure ======== remove customer from the system and free the respective teller
			else if(event.getType() == 'D') {
				served++;
				for(int j=0;j<printCust.size();j++) {
					if(printCust.get(j).getcID() == event.getCustID()) {
						// updating departure time and service time  of PrintStatistics class for that particular customer in printCust linked list 
						printCust.get(j).setDeptTime(event.getTime());
						printCust.get(j).setServiceTime(event.getTime() - printCust.get(j).getaTime() - printCust.get(j).getWaitingTime());;
						break;
					}
				}
				bankArea.removeService(new Customer(event.getCustID()));//removing customer & freeing the teller
				}
		}
	}
 

	public static void reportGeneration() {
		//printing the printCust linked list with all the data
    	System.out.println("Customer ID" + "\t" + "Arrival Time" + "\t\t" + "Service Time" + "\t\t" + "Departure"+ "\t\t" + "Waiting Time"+ "\t\t" +"Queue"+ "\t\t" + " Teller" );
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------" );
    	Iterator<PrintStatistics> itr = printCust.iterator();
		while(itr.hasNext()) {
			PrintStatistics obj1 = itr.next();
			System.out.printf("%d\t\t %7.2f\t\t %7.2f\t\t %7.2f\t\t %7.2f\t\t %d\t\t %d \n",obj1.getcID(),obj1.getaTime(),obj1.getServiceTime(),obj1.getDeptTime(),obj1.getWaitingTime(),obj1.getqId(),obj1.getTellerID());
			}
		System.out.println("Number of customer served : " + served );
		System.out.println("Number of customer left without being served : " + notServed );
		System.out.println("Average Waiting Time : " + (waitTimeCounter/served) );
		System.out.println("Average Service Time : " + (serviceTimeCounter/served) );
	}

	
	
	public static double exponential (Random rnd, double mean)
	{
		return -mean*Math.log(rnd.nextDouble());
	}
	
    public static void main(String[] args) {
		
		meanInterArrivalTime = 20;
		meanServiceTime = 60;
		tellers = 5;
		maxQueueLength = 10;
		simulationLength=9999;
		
		long seed = Long.parseLong("0",10);
		stream = new Random(seed);
		
		initialization();// setting up the input parameters
		simulation();// simulation logic
		reportGeneration();// prints output data    
	}

}