package com.thread;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadReadWriteLock {
    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                service.read();
            }).start();
        }
    }

    static class Service{
        private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
//        private ReentrantLock lock=new ReentrantLock();
        public void read(){
            try {
                lock.writeLock().lock();
                ;
//                lock.lock();
                System.out.println("获取读锁："+Thread.currentThread().getName());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
//                lock.unlock();
            }
        }
    }
}

