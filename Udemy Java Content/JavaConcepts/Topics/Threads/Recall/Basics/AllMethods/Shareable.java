package Threads.Recall.Basics.AllMethods;

public class Shareable {
    boolean isAvailable = false;

    public synchronized void consumeItem() {
        while (!isAvailable) {
            System.out.println("item not available to consume..."+Thread.currentThread().getName());
            try {
                System.out.println("Waiting for item to consume by..."+Thread.currentThread().getName());
                wait();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Consumed item as it is available by " + Thread.currentThread().getName());
        isAvailable = false;
        notifyAll();
    }

    public synchronized void addItem() {
        while (isAvailable) {
            try {
                wait();
                System.out.println("waiting to add an item as it is available by " + Thread.currentThread().getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try{
            Thread.sleep(3000);
            System.out.println("Adding item after 3 seconds by" + Thread.currentThread().getName());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Going to make item available by " + Thread.currentThread().getName());
        isAvailable = true;
        notifyAll();
    }
}
