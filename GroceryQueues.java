import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroceryQueues {
    private List<GroceryQueue> queues;
    private int maxQueueLength;

    public GroceryQueues(int numberOfQueues, int maxQueueLength) {
        this.queues = new ArrayList<>();
        for (int i = 0; i < numberOfQueues; i++) {
            queues.add(new GroceryQueue(maxQueueLength));
        }
        this.maxQueueLength = maxQueueLength;
    }

    public boolean addCustomer(Customer customer) {
        GroceryQueue bestQueue = null;
        int minQueueSize = Integer.MAX_VALUE;
        for (GroceryQueue queue : queues) {
            int size = queue.getQueueSize();
            if (size < minQueueSize) {
                minQueueSize = size;
                bestQueue = queue;
            }
        }

        if (bestQueue != null && minQueueSize < maxQueueLength) {
            return bestQueue.addCustomer(customer);
        } else {
            return false;
        }
    }

    public List<Customer> serveCustomers() {
        List<Customer> servedCustomers = new ArrayList<>();
        for (GroceryQueue queue : queues) {
            Customer servedCustomer = queue.serveCustomer();
            if (servedCustomer != null) {
                servedCustomers.add(servedCustomer);
            }
        }
        return servedCustomers;
    }

    public int getTotalQueueSize() {
        int totalSize = 0;
        for (GroceryQueue queue : queues) {
            totalSize += queue.getQueueSize();
        }
        return totalSize;
    }
}
