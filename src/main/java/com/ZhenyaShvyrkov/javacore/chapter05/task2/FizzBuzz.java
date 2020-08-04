package main.java.com.ZhenyaShvyrkov.javacore.chapter05.task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class FizzBuzz{
    final int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable f) {
        f.run();
    }

    public void buzz(Runnable b) {
        b.run();
    }

    public void fizzbuzz( Runnable fb) {
        fb.run();
    }

    public void number(Runnable n) {
        n.run();
    }
    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        int [] array = new int[1];
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        Thread threadA = new Thread(() -> {
            fizzBuzz.fizz(() -> {
                for(int i = 1; i <= fizzBuzz.n; i++) {
                    lock.lock();
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    array[0] = i;
                    if(array[0] % 3 == 0 && array[0] % 5 == 0) {}
                    else if (array[0] % 3 == 0) {
                        System.out.print("fizz");
                        if(i != fizzBuzz.n ){
                            System.out.print(", ");
                        }
                    }
                    condition.signal();
                    lock.unlock();
                }
            });
        });
        Thread threadB = new Thread(() -> {
            fizzBuzz.buzz(() -> {
                for(int i = 1; i <= fizzBuzz.n; i++) {
                    lock.lock();
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    array[0] = i;
                    if(array[0] % 3 == 0 && array[0] % 5 == 0) {}
                    else if (array[0] % 5 == 0) {
                        System.out.print("buzz");
                        if(i != fizzBuzz.n){
                            System.out.print(", ");
                        }
                    }
                    condition3.signal();
                    lock.unlock();
                }
            });
        });
        Thread threadC = new Thread(() -> {
            fizzBuzz.fizzbuzz(() -> {
                for(int i = 1; i <= fizzBuzz.n; i++) {
                    lock.lock();
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    array[0] = i;
                    if (array[0] % 3 == 0 && array[0] % 5 == 0) {
                        System.out.print("fizzbuzz");
                        if(i != fizzBuzz.n){
                            System.out.print(", ");
                        }
                    }
                    condition2.signal();
                    lock.unlock();
                }
            });
        });
        Thread threadD = new Thread(() -> {
            fizzBuzz.number(() -> {
                for(int i = 1; i <= fizzBuzz.n; i++) {
                    lock.lock();
                    array[0] = i;
                    if (array[0] % 3 != 0 && array[0] % 5 != 0) {
                        System.out.print(array[0]);
                        if(i != fizzBuzz.n){
                            System.out.print(", ");
                        }
                    }
                    condition1.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            });
        });
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
    }
}
