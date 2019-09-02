package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCondition {
    public static void main(String[] args) {
        new Thread(()->{
            new Service().await();
        }).start();
    }
}

class Service {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void await(){
        try{
            lock.lock();
            condition.await();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}