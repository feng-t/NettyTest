package com.thread;

public class ThreadTestCP {
    public static String value;

    public static void main(String[] args) {
        String lock = "aaa";
        P p = new P(lock);
        C c = new C(lock);

    }
}

class P {
    private String lock;

    P(String lock) {
        this.lock = lock;
    }

    public void setVale() {
        try {
            synchronized (lock) {
                if (!"".equals(ThreadTestCP.value)) {
                    lock.wait();
                }
                ThreadTestCP.value = System.currentTimeMillis() + "";
                System.out.println("生产者：" + ThreadTestCP.value);
                lock.notify();
            }
        } catch (Exception ignored) {
        }
    }
}

class C {
    private String lock;

    C(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if ("".equals(ThreadTestCP.value)) {
                    lock.wait();
                }
                System.out.println("消费者：" + ThreadTestCP.value);
                ThreadTestCP.value = "";
                lock.notify();
            }
        } catch (Exception ignored) {
        }
    }
}