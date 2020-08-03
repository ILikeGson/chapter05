package main.java.com.ZhenyaShvyrkov.javacore.chapter05.task2;

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
}
