package main.java.com.ZhenyaShvyrkov.javacore.chapter05.task1;

import java.util.concurrent.Semaphore;

class Foo {
    Semaphore firstSem = new Semaphore(1);
    Semaphore secondSem = new Semaphore(0);
    Semaphore thirdSem = new Semaphore(0);
    public void first() {
        try {
            firstSem.acquire();
            System.out.print("first");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            secondSem.release();
        }
    }
    public void second() {
        try {
            secondSem.acquire();
            System.out.print("second");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            thirdSem.release();
        }
    }
    public void third() {
        try {
            thirdSem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("third");
    }
}


