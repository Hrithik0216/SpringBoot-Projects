package Threads.SharedResource;

public class Implementation {
    public static void main(String[] args) {
        SharedResoruce sharedResoruce = new SharedResoruce();
        Thread producerThread = new Thread(()-> {
            sharedResoruce.addItem();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumerThread = new Thread(()-> sharedResoruce.consumeItem());
        consumerThread.start();
        producerThread.start();

    }
}
