import java.util.Random;
//MM1Simulation - test of Little's Law
public class MM1Simulation {	 
	static double runLength = 0;
	static double[] runLengths = {10, 100, 1000, 10000};
	//used to average performance measures. corresponding indexes to runLengths
	static double throughputSum;
	static double responseTimeSum;
	static double peopleInSystemSum;
	static final int EXPERIMENT_REPEATS = 3;
	 
	static double clockTime = 0; //time in minutes
	static double serviceCompletions = 0;
	static double peopleInSystem = 0;
	static double arrivalCount = 0;
	 
	static final double SERVICE_RATE = 5; //minute
	static final double INTERARRIVAL_RATE = 3.75; //per minute
	 
	//use same stream of numbers for consistency across different run lengths
	//genereated from random.org
	static final int[] seeds = {54, 97, 23};
	static int experimentNum = 0;
	static Random rand = new Random(seeds[experimentNum]);
	 
	static double nextArrival = nextInterarrivalTimeLength();
	static double nextDeparture = Double.POSITIVE_INFINITY;
	static double sumDeparture = 0;
	static double sumArrival = 0;
	static double lastArrival = 0;
	
	//Needed to calculate average queue length
	static double lastEventTime = 0;
	static double customerSeconds = 0;
	 
	public static void main(String[] args) {
		for(double length : runLengths){
			runLength = length;
			System.out.println("Simulation of run length " + runLength);
			for(experimentNum = 0; experimentNum < EXPERIMENT_REPEATS; ++experimentNum){	
				while(serviceCompletions < runLength){
					if (nextDeparture < nextArrival){
						processDepartureEvent();
					} else{
						processArrivalEvent();
					}
				}
				calcAndAddPerformanceMeasures();
				resetExperiment();
			}
			showResults();
			resetAfterRun();
		}	
	}
	
	private static void calcAndAddPerformanceMeasures() {
		double throughput = serviceCompletions / clockTime;
		double averageSystemResponseTime = (sumDeparture - sumArrival) / runLength;
		double averagePeopleInSystem = customerSeconds / clockTime;
		throughputSum += throughput;
		responseTimeSum+= averageSystemResponseTime;
		peopleInSystemSum += averagePeopleInSystem;
	}
	
	public static void resetExperiment(){
		clockTime = 0;
		serviceCompletions = 0;
		peopleInSystem = 0;
		arrivalCount = 0;
		nextArrival = nextInterarrivalTimeLength();
		nextDeparture = Double.POSITIVE_INFINITY;
		sumDeparture = 0;
		sumArrival = 0;
		lastArrival = 0;
	    lastEventTime = 0;
		customerSeconds = 0;
	}

	public static void resetAfterRun(){
		throughputSum = 0;
		responseTimeSum = 0;
		peopleInSystemSum = 0;
		experimentNum = 0;
		rand = new Random(seeds[experimentNum]); 
	}

	private static void showResults() {
		double throughput = throughputSum / (double) EXPERIMENT_REPEATS;
		double averageSystemResponseTime = responseTimeSum / (double) EXPERIMENT_REPEATS;;
		double averagePeopleInSystem = peopleInSystemSum / (double) EXPERIMENT_REPEATS;;
		System.out.println("Throughput: " + throughput);
		System.out.println("Average system response time (w): " + averageSystemResponseTime + " minutes");
		System.out.println("Average number of people in the system: " + averagePeopleInSystem + " customers");
		System.out.println("Average arrival rate * average system response time: " + (INTERARRIVAL_RATE * averageSystemResponseTime));
		System.out.println("Difference between L and λ*w: " + Math.abs(averagePeopleInSystem - (INTERARRIVAL_RATE * averageSystemResponseTime)));
		System.out.println();
	}

	private static double nextInterarrivalTimeLength(){
		return (-1/(INTERARRIVAL_RATE)) * Math.log(1 - rand.nextDouble());
	}

	private static double nextServiceTimeLength(){
		return (-1/(SERVICE_RATE)) * Math.log(1 - rand.nextDouble());
	}
	
	private static void processArrivalEvent(){
		clockTime = nextArrival;
		nextArrival = clockTime + nextInterarrivalTimeLength();
		
		integrateCustomerMinutes();
		
		++peopleInSystem;
		++arrivalCount;
		if (arrivalCount <= runLength){
			sumArrival += clockTime;
		}
		
		if (peopleInSystem == 1){
			nextDeparture = clockTime + nextServiceTimeLength();
		}
	}
	
	private static void processDepartureEvent(){
		clockTime = nextDeparture;
		
		integrateCustomerMinutes();
		
		++serviceCompletions;
		sumDeparture += clockTime;
		
		--peopleInSystem;
		if (peopleInSystem > 0){
			nextDeparture = clockTime + nextServiceTimeLength();
		} else {
			nextDeparture = Double.POSITIVE_INFINITY;
		}
	}

	private static void integrateCustomerMinutes() {
		customerSeconds += peopleInSystem * (clockTime - lastEventTime);
		lastEventTime = clockTime;
	}
}
