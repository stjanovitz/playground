Test of Little's Law 
====================
This is a simulation of an M/M/1 queue that supports [Little's Law](https://en.wikipedia.org/wiki/Little%27s_law).

As the number of service completions increases, the throughput of the system reaches the mean
inter-arrival rate (3.75 customers per minute) of the system. Additionally, as the number of service
completions increases, the arrival rate (which the throughput matches) times the average system
response time approaches the average number of people in the systems. This can be seen in the
decreasing difference between the two quantities in the simulation output.


Simulation of run length 10.0	
Throughput: 3.464600127643878	
Average system response time (w): 0.5008566520227817 minutes	
Average number of people in system: 1.6549443845442766 customers	
Average arrival rate * average system response time: 1.8782124450854316	
Difference between L and λ*w: 0.223268060541155	

Simulation of run length 100.0	
Throughput: 3.4204514547425346	
Average system response time (w): 0.4883500578056714 minutes	
Average number of people in system: 1.7032806360465098 customers	
Average arrival rate * average system response time: 1.8313127167712677	
Difference between L and λ*w: 0.1280320807247579	

Simulation of run length 1000.0	
Throughput: 3.6345204838301566	
Average system response time (w): 0.8720789829357236 minutes	
Average number of people in system: 3.175236618033825 customers	
Average arrival rate * average system response time: 3.2702961860089634	
Difference between L and λ*w: 0.09505956797513848	

Simulation of run length 10000.0	
Throughput: 3.7718684903338464	
Average system response time (w): 0.8161613572387646 minutes	
Average number of people in system: 3.0801075641462767 customers	
Average arrival rate * average system response time: 3.0606050896453674	
Difference between L and λ*w: 0.01950247450090936	
