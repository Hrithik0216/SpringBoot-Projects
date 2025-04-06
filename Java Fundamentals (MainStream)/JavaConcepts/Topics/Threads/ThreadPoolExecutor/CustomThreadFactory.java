package Threads.ThreadPoolExecutor;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r);
        th.setDaemon(true);
        th.setPriority(Thread.NORM_PRIORITY);
        return th;
    }
}
