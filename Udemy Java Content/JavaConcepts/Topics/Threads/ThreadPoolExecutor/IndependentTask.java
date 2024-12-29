package Threads.ThreadPoolExecutor;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IndependentTask implements Runnable{
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    @Override
    public void run() {
        try{
            System.out.println("The thread is "+Thread.currentThread().getName()+ " and started at "+formatter.format(date));
            Thread.sleep(5000);
            System.out.println("The thread is "+Thread.currentThread().getName()+ " and ended at"+formatter.format(date));
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
