package it.test.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {

    private static final int index = 1;
    private static final boolean aHasPrint = false;      //记录第一个是否被打印过
    private static final Object lock = new Object();


    public static void main(String[] args) {
//        Thread t1 = new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 50; i++) {
//                    synchronized (lock) {
//                        while (aHasPrint) {
//                            try {
//                                lock.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        System.out.println("A:" + index);
//                        index++;
//                        aHasPrint = true;
//                        lock.notifyAll();
//                    }
//                }
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 50; i++) {
//                    synchronized (lock) {
//                        while (!aHasPrint) {               //如果A没有打印过则阻塞
//                            try {
//                                lock.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        System.out.println("B:" + index);
//                        index++;
//                        aHasPrint = false;
//                        lock.notifyAll();
//                    }
//                }
//            }
//        });
//
//
//        t1.start();
//        t2.start();
//    }

        TestThread t = new TestThread();
//        t.printAB();
        t.doSynchronized();
//        t.printTwo();
//        t.doAtomicInteger();
    }


    private static int i = 1;

    private void doSynchronized() {
        int TOTAL = 100;
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            while (i <= TOTAL) {
                synchronized (lock) {
                    if (i % 3 == 0) {
                        System.out.println("t1:" + i++);
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            countDownLatch.countDown();
        });
        executorService.execute(() -> {
            while (i <= TOTAL) {
                synchronized (lock) {
                    if (i % 3 != 0) {
                        System.out.println("t2:" + i++);
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            countDownLatch.countDown();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打印结束aaaaaa");

    }


    int a = 1;
    private void printTwo() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Object lock1 = new Object();
        new Thread(() -> {
            while (a <= 100) {
                synchronized (lock1) {
                    if (a % 2 == 0) {
                        System.out.println("t1:" + a++);
                        lock1.notifyAll();
                    } else {
                        try {
                            lock1.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            while (a <= 100) {
                synchronized (lock1) {
                    if (a % 2 == 1) {
                        System.out.println("t2:" + a++);
                        lock1.notifyAll();
                    } else {
                        try {
                            lock1.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            countDownLatch.countDown();
        }).start();
        try{
            countDownLatch.await();
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("输出完毕");
    }


    private static final int MAX_PRINT_NUM = 100;
    private static int count = 1;

    public void printAB() {
        // 声明CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            while (count <= MAX_PRINT_NUM) {
                if (count % 3 == 0) {
                    System.out.println("t1:" + count);
                    count++;
                }
            }
            // 偶数线程执行完则计数器减一
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            while (count <= MAX_PRINT_NUM) {
                if (count % 3 == 1) {
                    System.out.println("t2:" + count);
                    count++;
                }
            }
            // 奇数线程执行完则计数器减一
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            while (count <= MAX_PRINT_NUM) {
                if (count % 3 == 2) {
                    System.out.println("t3:" + count);
                    count++;
                }
            }
            // 奇数线程执行完则计数器减一
            countDownLatch.countDown();
        }).start();

        try {
            countDownLatch.await();
        } catch (Exception e) {
        }
    }

}
