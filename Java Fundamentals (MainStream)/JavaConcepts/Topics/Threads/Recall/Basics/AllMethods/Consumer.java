package Threads.Recall.Basics.AllMethods;

public class Consumer implements Runnable{
    Shareable shareable;
    public Consumer(Shareable shareable) {
        this.shareable = shareable;
    };

    @Override
    public void run(){
        while(true){
            shareable.consumeItem();
        }
    }
}
