import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QueueSimulator {
    private int simulationTime;
    private BankQueue bankQueue;
    private GroceryQueues groceryQueues;
    private List<Customer> allCustomers;
    private List<Customer> unservedCustomers;

    public QueueSimulator(int simulationTime, BankQueue bankQueue, GroceryQueues groceryQueues) {
        this.simulationTime = simulationTime;
        this.bankQueue = bankQueue;
        this.groceryQueues = groceryQueues;
        this.allCustomers = new ArrayList<>();
        this.unservedCustomers = new ArrayList<>();
    }

    public void simulate() {
        Random rand = new Random();
        int currentTime = 0;
        
        while (currentTime < simulationTime * 60) {
            if (rand.nextBoolean()) {
                Customer customer = new Customer(currentTime);
                if (!bankQueue.addCustomer(customer)) {
                    unservedCustomers.add(customer);
                } else {
                    allCustomers.add(customer);
                }
            } else {
                Customer customer = new Customer(currentTime);
                if (!groceryQueues.addCustomer(customer)) {
                    unservedCustomers.add(customer);
                } else {
                    allCustomers.add(customer);
                }
            }
            
            // Serving customers
            bankQueue.serveCustomer();
            groceryQueues.serveCustomers();
            
            currentTime++;
            try {
                Thread.sleep(1000); // Each second of simulation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printResults() {
        int totalCustomers = allCustomers.size();
        int totalUnservedCustomers = unservedCustomers.size();
        int totalServedCustomers = totalCustomers - totalUnservedCustomers;

        long totalServiceTime = 0;
        for (Customer customer : allCustomers) {
            if (customer.isServed()) {
                totalServiceTime += customer.getServiceTime();
            }
        }

        double averageServiceTime = (double) totalServiceTime / totalServedCustomers;

        System.out.println("Total customers arrived: " + totalCustomers);
        System.out.println("Total customers forced to leave: " + totalUnservedCustomers);
        System.out.println("Total customers served: " + totalServedCustomers);
        System.out.println("Average service time: " + averageServiceTime);
    }
}
