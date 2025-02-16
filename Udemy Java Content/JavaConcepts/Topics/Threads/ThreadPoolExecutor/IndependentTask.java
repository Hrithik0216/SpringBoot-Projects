package Threads.ThreadPoolExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IndependentTask implements Runnable {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    @Override
    public void run() {
        System.out.println("The thread is " + Thread.currentThread().getName() + " and started at " + formatter.format(date));
        sleep(5000);
        System.out.println("The thread is " + Thread.currentThread().getName() + " and ended at" + formatter.format(date));
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("The thread is " + Thread.currentThread().getName() + " and ended at" + formatter.format(date));
        }
    }
}
