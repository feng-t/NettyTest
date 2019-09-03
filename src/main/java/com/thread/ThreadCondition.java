package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCondition {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        new Thread(()->{
            service.await();
        }).start();
        Thread.sleep(1000);
        service.signal();
    }
}

class Service {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void await(){
        try{
            lock.lock();
            System.out.println("A");
            condition.await();
            System.out.println("A");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void signal(){
        try {
            lock.lock();
            System.out.println("解锁");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}