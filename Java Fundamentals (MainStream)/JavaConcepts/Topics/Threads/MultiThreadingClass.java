package Threads;

public class MultiThreadingClass {
    public static void main(String[] args) {
        System.out.println("Thread Main: "+Thread.currentThread().getName());
        RunnableInterfaceImplementation runnableInterfaceImplementation = new RunnableInterfaceImplementation();
        Thread t1 = new Thread(runnableInterfaceImplementation);
        Thread t2 = new Thread(()->{runnableInterfaceImplementation.thisIsFOrCheckinMonitorLock(2);});
        Thread t3 = new Thread(()->{runnableInterfaceImplementation.thisIsFOrCheckinMonitorLock(3);});
        Thread t4 = new Thread(()->{runnableInterfaceImplementation.thisIsFOrCheckinMonitorLock(4);});
        t1.start();
        t2.start();
        t3.start();
        t4.start();



    }
}
