package com.test.question.multi_thread;

public class SynchronizedTest {
    private static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        syncTest.synchronizedNormalMethod();
        //SyncTest.synchronizedStaticMethod();
        //syncTest.synchronizedClass();
        //syncTest.synchronizedGetClass();
        //syncTest.synchronizedThis();
        //syncTest.synchronizedInstance();
    }
}
class SyncTest {
    public synchronized void synchronizedNormalMethod() {
        System.out.println("synchronizedNormalMethod");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized static void synchronizedStaticMethod() {
        System.out.println("synchronizedStaticMethod");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void synchronizedClass() {
        synchronized (SyncTest.class){
            System.out.println("synchronizedClass");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void synchronizedGetClass() {
        synchronized (this.getClass()){
            System.out.println("synchronizedGetClass");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void synchronizedThis() {
        synchronized (this){
            System.out.println("synchronizedThis");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static final Object LOCK = new Object();
    public void synchronizedInstance() {
        synchronized (LOCK){
            System.out.println("synchronizedInstance");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
