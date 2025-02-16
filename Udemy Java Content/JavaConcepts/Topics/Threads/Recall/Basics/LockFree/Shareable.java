package Threads.Recall.Basics.LockFree;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Shareable {
    AtomicLong incrementValue = new AtomicLong(0);
    /*
    * Only synchronized method synchronizes the 2 threads. Else use thread safe data structures
    * */
    public void increment(int i){
        incrementValue.addAndGet(i);
    }

    public long getIncrementValue(){
        return incrementValue.get();
    }


}
