package it.test.simple;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {

    private volatile static int index = 1;
    private volatile static boolean aHasPrint = false;      //记录第一个是否被打印过
    private static Object lock = new Object();


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
//        t.doSynchronized();
        t.doAtomicInteger();
    }


    private static int i = 1;

    private void doSynchronized() {
        int TOTAL = 100;
        Thread thread1 = new Thread(() -> {
            while (i <= TOTAL) {
                synchronized (lock) {
                    if (i % 2 == 1) {
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
        });
        Thread thread2 = new Thread(() -> {
            while (i <= TOTAL) {
                synchronized (lock) {
                    if (i % 2 == 0) {
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
        });
        thread1.start();
        thread2.start();
    }


//        private static final int MAX_PRINT_NUM = 100;
//        private static AtomicInteger count = new AtomicInteger(1);
//
//        public void printAB() {
//            // 声明CountDownLatch
//            CountDownLatch countDownLatch = new CountDownLatch(1);
//            new Thread(() -> {
//                while (count.get() <= MAX_PRINT_NUM) {
//                    if (count.get() % 3 == 0) {
//                        System.out.println("t1:" + count);
//                        count.getAndIncrement();
//                    }
//                }
//                // 偶数线程执行完则计数器减一
//                countDownLatch.countDown();
//            }).start();
//
//            new Thread(() -> {
//                while (count.get() <= MAX_PRINT_NUM) {
//                    if (count.get() % 3 == 1) {
//                        System.out.println("t2:" + count);
//                        count.getAndIncrement();
//                    }
//                }
//                // 奇数线程执行完则计数器减一
//                countDownLatch.countDown();
//            }).start();
//
//            new Thread(() -> {
//                while (count.get() <= MAX_PRINT_NUM) {
//                    if (count.get() % 3 == 2) {
//                        System.out.println("t3:" + count);
//                        count.getAndIncrement();
//                    }
//                }
//                // 奇数线程执行完则计数器减一
//                countDownLatch.countDown();
//            }).start();
//
//            try {
//                countDownLatch.await();
//            } catch (Exception e) {
//            }
//        }

}
