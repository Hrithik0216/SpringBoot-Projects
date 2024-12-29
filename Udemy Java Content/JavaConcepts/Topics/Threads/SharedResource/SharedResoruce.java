package Threads.SharedResource;

public class SharedResoruce {
    private boolean isSharedResource = false;

    public synchronized void addItem() {
        isSharedResource = true;
        System.out.println(Thread.currentThread().getName() + "Producer calls the notify method");
        notifyAll();
    }

    public synchronized void consumeItem() {

        if (!isSharedResource) {
            try {
                System.out.println(Thread.currentThread().getName() + " SharedResource is waiting in consumer thread");
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        isSharedResource = false;
        System.out.println(Thread.currentThread().getName() + " SharedResource is Consumed in consumer thread");

    }
}
