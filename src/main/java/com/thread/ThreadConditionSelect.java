package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConditionSelect {
    public static void main(String[] args) throws InterruptedException {
        ThreadConditionSelectService service1 = new ThreadConditionSelectService();
        new Thread(() -> {
            service1.awaitA();
        }).start();
        new Thread(() -> {
            service1.awaitB();
        }).start();
        Thread.sleep(500);
        service1.signalAll_A();
    }
}

class ThreadConditionSelectService {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println("A:wait");
            conditionA.await();
            System.out.println("A:run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try {
            lock.lock();
            System.out.println("B:wait");
            conditionB.await();
            System.out.println("B:run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_A() {
        try {
            lock.lock();
            conditionA.signalAll();
            System.out.println("A:signalAll");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_B() {
        try {
            lock.lock();
            conditionB.signalAll();
            System.out.println("B:signalAll");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
