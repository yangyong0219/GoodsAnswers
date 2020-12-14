package it.test.simple;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestVolatile {
    public static volatile AtomicInteger race = new AtomicInteger(0);
    public static int a = 0;
    public static void increase() {
        race.getAndIncrement();
    }

    public static final int THREADS_COUNT = 20;
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        //等待所有线程执行完成, Idea因为会自动创建一条名为Monitor Ctrl-Break的线程
        //所以会导致while循环无法结束, 此处用>2
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i].join();
        }
        System.out.println(race);

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
