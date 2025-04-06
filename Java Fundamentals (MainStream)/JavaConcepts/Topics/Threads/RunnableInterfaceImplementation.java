package Threads;

public class RunnableInterfaceImplementation implements Runnable {

    synchronized void thisIsFOrCheckinMonitorLock(int i) {
        try {
            System.out.println("The monitor lock and its thread number: " + i + " " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void run() {
        System.out.println("Hello World");
        System.out.println("For RunnableInterfaceImplementation: " + Thread.currentThread().getName());
    }
}
