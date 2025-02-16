package Threads.Recall.Basics.LockFree;

public class Execution {
    public static void main(String[] args) {
        Shareable shareable = new Shareable();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                shareable.increment(i);  // Fix: Use `1` instead of `i`
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                shareable.increment(i);  // Fix: Use `1` instead of `i`
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        long result = shareable.getIncrementValue();
        System.out.println("Final Count: " + result);
    }
}
