public class Main {
    public static void main(String[] args) {
        int simulationMinutes = 2; // for 1 min

        BankQueue bankQueue = new BankQueue(3, 5);
        GroceryQueues groceryQueues = new GroceryQueues(3, 2);

        QueueSimulator simulator = new QueueSimulator(simulationMinutes, bankQueue, groceryQueues);
        simulator.simulate();
        simulator.printResults();
    }
}
