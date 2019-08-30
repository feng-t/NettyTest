package com.httpserver;

public class mstest {

    public static void main(String[] args) {
//        Run run = new Run();
//
//        Thread a = new Thread(run, "a");
//        Thread b = new Thread(run, "b");
//        Thread c = new Thread(run, "c");
//        Thread d = new Thread(run, "d");
//        Thread e = new Thread(run, "e");
//        a.start();
//        b.start();
//        c.start();
//        d.start();
//        e.start();
        new Thread(()->{
                Ser.doGet("a","aa");
        }).start();


        new Thread(()->{

                Ser.vs("b","bb");

        }).start();
//        System.out.println(5 & 14);
    }

}

class Ser {
    private static String nameRef;
    private static String passRef;
    static synchronized void doGet(String name, String pass) {
        vs(name,pass);
    }
    public static void vs(String name, String pass){
        try {
            nameRef=name;
            if ("a".equals(name)) {
                Thread.sleep(1);
            }
            passRef=pass;

            System.out.println("name = " + nameRef + ",pass = " + passRef);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run implements Runnable {

    private int count = 5;

    @Override
    public void run() {
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + ":" + count);
        }
    }
}