package com.thread;

import com.example.protobuf.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest1 {
    public static void main(String[] args) throws InterruptedException {
//        MyOneList list = new MyOneList();
//        MyThreadA a = new MyThreadA(list);
//        a.start();
//        MyThreadB b = new MyThreadB(list);
//        b.start();
//        Thread.sleep(1000);
//        System.out.println(list.getSize());
        Test1 test1 = new Test1();
        new Thread(()->{
            try {
                test1.ts(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A1").start();
        new Thread(()->{
            try {
                test1.ts(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A2").start();
    }
}
class Test1{
    private String lock="a1";
    public void ts(String name) throws InterruptedException {
        synchronized (lock){
            System.out.println(name+":start");
            lock="a2";
//            Thread.sleep(1000);
            System.out.println(name+":end");
        }
    }
}

class text{
    public static void t(){
        System.out.println("ts");
    }
}
class MyOneList {
    private List list = new ArrayList();

    public synchronized void add(String data) {
        if (list.size() > 0) return;
        list.add(data);
    }

    public synchronized int getSize() {
        return list.size();
    }
}

class MyService {
    public MyOneList addServiceMethod(MyOneList list, String data) {
        try {
            if (list.getSize() < 1) {
                Thread.sleep(500);
                list.add(data);
            }
        } catch (Exception ignored) {
        }
        return list;
    }
}

class MyThreadA extends Thread {
    private MyOneList list;

    MyThreadA(MyOneList list) {
        this.list = list;
    }

    @Override
    public void run() {
        MyService service = new MyService();
        service.addServiceMethod(list, "a");
    }
}

class MyThreadB extends Thread {
    private MyOneList list;

    MyThreadB(MyOneList list) {
        this.list = list;
    }

    @Override
    public void run() {
        MyService service = new MyService();
        service.addServiceMethod(list, "b");
    }
}