package Threads.Recall.Basics.AllMethods;

public class Execution {
    public static void main(String[] args) {
        Shareable shareable = new Shareable();
        Thread producer = new Thread(new Producer(shareable), "Producer Thread");
        Thread consumer = new Thread(new Consumer(shareable), "Consumer Thread");
        Thread consumer2 = new Thread(new Consumer(shareable), "Consumer 2 Thread");

        consumer.start();
        consumer2.start();
        producer.start();
    }
}
