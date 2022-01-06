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

