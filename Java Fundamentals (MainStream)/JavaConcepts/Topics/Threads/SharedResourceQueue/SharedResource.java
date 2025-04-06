package Threads.SharedResourceQueue;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<Integer> queue = new LinkedList<Integer>();
    private int capacity;
    public SharedResource(int capacity) {
        this.capacity = capacity;
    }


    synchronized int addItem(int i){
        while(queue.size()==capacity){
            System.out.println("The queue is full and is waitin in producer");
            try{
                wait();
            }catch (InterruptedException  e){
                System.out.println(e);
            }
            return -1;
        }
        System.out.println("Adding "+i+" to the queue");
        queue.add(i);
        notifyAll();
        return i;
    }

    synchronized int consumeItem(){
        while (queue.isEmpty()){
            try{
                System.out.println("The queue is empty. It is waiting for items" +"The consumer Thread is: "+Thread.currentThread().getName());
                wait();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        int item = queue.remove();
        System.out.println("Consumed " + item + ". Consumer Thread: " + Thread.currentThread().getName());
        notifyAll(); // Notify waiting producers
        return item;
    }
}
