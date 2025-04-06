package Threads.Recall.Basics.AllMethods;

public class Producer implements Runnable {
    Shareable shareable;
    public Producer(Shareable shareable) {
        this.shareable = shareable;
    }

    @Override
    public void run() {
        while (true) {
            shareable.addItem();
        }
    }
}
