package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadReentrantLock {
    public static void main(String[] args) {
        methodA a = new methodA();
        new Thread(()->{a.mA();}).start();
        new Thread(()->{a.mB();}).start();
    }
}
class methodA{
    private Lock lock=new ReentrantLock();
    private int A;
    public void mA(){
        try {
            ++A;
            lock.lock();
            System.out.println("ma running");

            Thread.sleep(1000);
            A++;
            System.out.println("ma end:"+A);
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }

    }
    public void mB(){
        try {
            lock.lock();
            System.out.println("mb running");
            Thread.sleep(1000);
            System.out.println("mb end:"+A);
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }

    }
}

