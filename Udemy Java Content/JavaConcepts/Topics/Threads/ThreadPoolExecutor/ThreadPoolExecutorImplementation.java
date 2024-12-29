package Threads.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ThreadPoolExecutorImplementation {
    public static void main(String[] args) {
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), new CustomThreadFactory(), new CustomExceptionHandler());
        customThreadPoolExecutor.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 10; i++) {
            customThreadPoolExecutor.submit(new IndependentTask());
        }
        customThreadPoolExecutor.shutdown();
        try {
            customThreadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
