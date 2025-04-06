package Threads.Recall.Basics;

public class Basics2 {
    public synchronized void method1(){
        try{
            System.out.println("method1() is called");
            Thread.sleep(2000);
            System.out.println("method1() is reached");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void method2(){
        System.out.println("method2() before synchronized block");
        synchronized (this){
            System.out.println("method2() is inside synchronized");
        }
    }

    public void method3(){
        System.out.println("method3() is called");
    }

    public synchronized void syncMethod(){
        System.out.println("syncMethod() is called with: "+Thread.currentThread().getName());
        try{
            Thread.sleep(2000);
            System.out.println("Lock is released");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("syncMethod() is ended wiht: "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Basics2 obj = new Basics2();
//        Thread t1 = new Thread(()-> obj.method1());
//        Thread t2 = new Thread(()-> obj.method2());
//        Thread t3 = new Thread(()-> obj.method3());
//        t1.start();
//        t2.start();
//        t3.start();
        Basics2 obj2 = new Basics2();
        Thread t4 = new Thread(()-> obj2.syncMethod(),"Thread 4");
        Thread t5 = new Thread(()-> obj2.syncMethod(),"Thread 5");
//        Thread t5 = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                obj2.syncMethod();
//            }
//        });
        t4.start();
        t5.start();
    }
}
