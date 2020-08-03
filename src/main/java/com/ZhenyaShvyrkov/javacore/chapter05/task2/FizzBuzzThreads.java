package main.java.com.ZhenyaShvyrkov.javacore.chapter05.task2;
import java.util.concurrent.*;

public class FizzBuzzThreads{
    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(10);
        int [] array = new int[1];
        CyclicBarrier barrier = new CyclicBarrier(4);

        Thread threadA = new Thread(() -> {
            fizzBuzz.fizz(() -> {
                for(int i = 1; i <= fizzBuzz.n; i++) {
                    array[0] = i;
                    if (array[0] % 3 == 0) {
                        System.out.print("fizz");
                        if(i != fizzBuzz.n ){
                            System.out.print(", ");
                        }
                    }
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        Thread threadB = new Thread(() -> {
            fizzBuzz.fizz(() -> {
                for(int i = 1; i <= fizzBuzz.n; i++) {
                    array[0] = i;
                    if (array[0] % 5 == 0) {
                        System.out.print("buzz");
                        if(i != fizzBuzz.n){
                            System.out.print(", ");
                        }
                    }

                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        Thread threadC = new Thread(() -> {
            fizzBuzz.fizz(() -> {
                for(int i = 1; i <= fizzBuzz.n; i++) {
                    array[0] = i;
                    if (array[0] % 3 == 0 && array[0] % 5 == 0) {
                        System.out.print("fizzbuzz");
                        if(i != fizzBuzz.n){
                            System.out.print(", ");
                        }
                    }
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        Thread threadD = new Thread(() -> {
            fizzBuzz.fizz(() -> {
                for(int i = 1; i <= fizzBuzz.n; i++) {
                    array[0] = i;
                    if (array[0] % 3 != 0 && array[0] % 5 != 0) {
                        System.out.print(array[0]);
                        if(i != fizzBuzz.n){
                            System.out.print(", ");
                        }
                    }
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
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
