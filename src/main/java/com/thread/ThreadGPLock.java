package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadGPLock {
    public static void main(String[] args) {
        ThreadGPLockService gpLockService = new ThreadGPLockService();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {

                gpLockService.await();

            }).start();
        }
    }
}

class ThreadGPLockService {
    private ReentrantLock lock = new ReentrantLock(false);

    public void await() {
        try {
            lock.lock();
            System.out.println("调用次数:" + lock.getHoldCount());
            as();
        } finally {
            lock.unlock();
        }
    }

    public void as() {
        try {
            lock.lock();
            System.out.println("as:"+lock.getHoldCount());
        }finally {
            lock.unlock();
        }
    }

}
