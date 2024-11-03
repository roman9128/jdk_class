package task2;

public class Main {

    public static void main(String[] args) {

        ThreadA tA = new ThreadA();
        ThreadB tB = new ThreadB(tA);

        tA.start();
        tB.start();

        try {
            tB.join();
            tA.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}