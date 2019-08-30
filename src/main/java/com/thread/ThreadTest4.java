package com.thread;

public class ThreadTest4 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread1 t1 = new Thread1(o);
        t1.start();
        Thread.sleep(1);
        for (int i = 0; i < 100; i++) {
            if (i==50){
                synchronized (o){
                    System.out.println("开始解锁");
                    o.notifyAll();
                    System.out.println("结束解锁");
                }
            }
        }

    }
}

class Thread1 extends Thread {
    private Object lock;

    Thread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("wait开始");
                lock.wait();
                System.out.println("wait结束:");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
