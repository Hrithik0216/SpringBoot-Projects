package Threads.Recall.Basics;

public class ThreadExtends extends Thread{
    @Override
    public void run() {
        System.out.println("ThreadExtends: "+Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        ThreadExtends t = new ThreadExtends();
        t.start();
        System.out.println(t.isAlive());
        System.out.println(t.currentThread().getName());
    }
}
