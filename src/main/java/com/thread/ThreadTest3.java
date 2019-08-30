package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest3 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new volaitleTest().start();
        }
    }
}

class volaitleTest extends Thread {
    public static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        synchronized (volaitleTest.class) {
            count.addAndGet(100);
            System.out.println("count:" + count);
        }
    }
}
