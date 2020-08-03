package main.java.com.ZhenyaShvyrkov.javacore.chapter05.task1;


public class TestThreads {
    public static void main(String[] args) {
        Foo foo = new Foo();

        Thread threadA = new Thread(() -> foo.first());
        threadA.start();
        Thread threadB = new Thread(() -> foo.second());
        threadB.start();
        Thread threadC = new Thread(() -> foo.third());
        threadC.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
