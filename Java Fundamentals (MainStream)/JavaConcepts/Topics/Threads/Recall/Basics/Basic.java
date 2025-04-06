package Threads.Recall.Basics;

public class Basic implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello World: "+ Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        System.out.println("Thread starts with: "+Thread.currentThread().getName());
        Basic basic = new Basic();
        Thread thread = new Thread(basic);
        thread.start();
    }
}
