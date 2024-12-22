package Threads.SharedResourceQueue;


import static java.lang.Thread.sleep;

public class MainApplication {
    public static void main(String[] args) {
        int sizze = 5;
        SharedResource sharedResource = new SharedResource(sizze);
        Thread consumerThread = new Thread(() -> {
            while (true) {
                sharedResource.consumeItem();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }

        });
        Thread producerThread = new Thread(() -> {
            int item = 1;
            while (true) {
                sharedResource.addItem(item++);
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }

        });
        consumerThread.start();
        producerThread.start();

    }
}
