import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankQueue {
    private int numberOfTellers;
    private int maxQueueLength;
    private Queue<Customer> queue;
    private Lock queueLock;

    public BankQueue(int numberOfTellers, int maxQueueLength) {
        this.numberOfTellers = numberOfTellers;
        this.maxQueueLength = maxQueueLength;
        this.queue = new LinkedList<>();
        this.queueLock = new ReentrantLock();
    }

    public boolean addCustomer(Customer customer) {
        queueLock.lock();
        try {
            if (queue.size() >= maxQueueLength) {
                return false;
            } else {
                queue.add(customer);
                return true;
            }
        } finally {
            queueLock.unlock();
        }
    }

    public Customer serveCustomer() {
        queueLock.lock();
        try {
            if (!queue.isEmpty()) {
                return queue.poll();
            }
            return null;
        } finally {
            queueLock.unlock();
        }
    }

    public int getQueueSize() {
        queueLock.lock();
        try {
            return queue.size();
        } finally {
            queueLock.unlock();
        }
    }
}
