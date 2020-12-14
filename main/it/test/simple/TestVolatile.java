package it.test.simple;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestVolatile {
    public static volatile int race = 0;
    public static int a = 0;
    public static void increase() {
        race++;
    }

    public static final int THREADS_COUNT = 20;
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        threads[0] = new Thread(new Runnable() {
            @Override
            public void run() {
                while (a > 10) {
                    System.out.println(a);
                }
            }
        });
        threads[1] = new Thread(new Runnable() {
            @Override
            public void run() {
                while (a<100) {
                    a++;
                    System.out.println("a--" + a);
                }
            }
        });
        AtomicInteger a = new AtomicInteger(10);
        a.incrementAndGet();
        System.out.println(a);

    }

    private ReentrantLock lock = new ReentrantLock();
    public void modifyPublicResources(){
        lock.lock();
        //操作同步资源
        lock.unlock();
    }


    public synchronized void testMethods1(){
        //操作同步资源
    }

    public void testMethod2(){
        synchronized (this) {
            //操作同步资源
        }
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.getAndIncrement();


    }



}
