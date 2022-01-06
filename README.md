# Simulation
Multi Server multi queue banking system

Simulating the multi-server bank queuing system for maximum simulation length of 9999 time units. The bank has 5 tellers and 2 customer queue, where customers arrive randomly and walk into the queues.
The arriving customer will enter that queue, having shorter queue length so that the waiting time of the customers is reduced. However, when customers walks into the queues, the customers will get served by the tellers who are idle whereas the customers in the waiting queues must wait till a teller becomes idle.

The inter- arrival time of the customers is exponentially distributed with a mean of 20 time units.
The service time of the customers is exponentially distributed with a mean of 10 time units.
Maximum number of customers i.e. queue length is 10.

The output should display the following – 
1. All details of each customer .i.e arrival time, service time, waiting time, departure time, queue, teller served.
2. Total customers arrived 
3. Number of customer left without being served 
4. Number of customers served 
5. Average waiting time and Average service time 

In order to simulate this system on JAVA. We made four classes-
1. Project_Main2.java

It is the main class of our program in which we have initialized all the variables and provided the main logics for the flow of the system. In this class, we have made 3 main methods-
• Initialization – In this method all the variables are initialized and their value is set as zero.
• Simulation – This method contains the main flow of the system. As the customer arrives, it first checks for the length of the waiting queue, if it has not reached its maximum limit then puts the customer in queue, calculates its arrival time and waiting time and then the service is provided to the customer while calculating its departure time.
• Report Generation – This method prints all the desired output values.
Other than these three methods, it contains the exponential function method, customer time data method and main method which calls the three main functions.

2. BankArea.java

It is the class that manages all the queues. We use different queues to manage the waiting customers, customers under service, free tellers and busy tellers.

3. Teller.java

This class is used for all the teller related calculations. This class contains the constructor and the getter – setter methods to access or set the values of various attributes related to teller.

4. Customer.java

This class is used for all the customer related calculations. This class contains the constructor and the getter – setter methods to access or set the values of various attributes related to customer.

5. Event.java

This class is used for defining events and its attributes.This class contains the constructor and the getter – setter methods to access or set the values of various attributes related to event.

6. PrintStatistics.java

This class is used for defining a prinatble instance for each cumstomer.This class contains the constructor and the getter – setter methods to access or set the values of various attributes related to a customer that can be used for printing purpose.
