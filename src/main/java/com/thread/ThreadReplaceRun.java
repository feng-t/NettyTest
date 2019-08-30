package com.thread;

public class ThreadReplaceRun {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(500);
                System.out.println("子线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        System.out.println("主线程");

//        Tools tools = new Tools();
//        for (int i = 0; i < 20; i++) {
//            new Thread(()->{
//                tools.MethodA();
//            }).start();
//            new Thread(()->{
//                tools.MethodB();
//            }).start();
//
//        }
    }
}

class Tools {
    private boolean flag = false;

    public void MethodA() {
        synchronized (this) {
            try {
                while (flag) {
                    wait();
                }
                System.out.print("★\t");
                flag = !flag;
                notify();
            } catch (Exception ignored) {
            }
        }
    }

    public void MethodB() {
        synchronized (this) {
            try {
                while (!flag) {
                    wait();
                }
                System.out.print("☆\t");
                flag = !flag;
                notify();
            } catch (Exception ignored) {
            }
        }
    }
}
