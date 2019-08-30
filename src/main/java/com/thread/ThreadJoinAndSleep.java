package com.thread;

public class ThreadJoinAndSleep {
    public static ThreadLocal tl = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                tl.set(Thread.currentThread().getName() + "");
                System.out.println(Thread.currentThread().getName()+":"+tl.get());
            });
            t.setName("" + i);
            t.start();
        }
        System.out.println(Thread.currentThread().getName()+":"+tl.get());
    }

}
