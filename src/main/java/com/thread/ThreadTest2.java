package com.thread;

public class ThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        volatileTest test = new volatileTest();
        test.start();
        Thread.sleep(1000);
        test.setFlag(false);
    }
}

class volatileTest extends Thread {
    private volatile boolean flag = true;
    private long count = 0;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println("进入run");
        while (flag) {
                count++;
        }
        System.out.println("退出run：count = " + count);
    }
}
