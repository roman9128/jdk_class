package task3;

import java.util.concurrent.CountDownLatch;

public class Race extends Thread {

    private CountDownLatch readySignal;
    private CountDownLatch startSignal;
    private CountDownLatch finishSignal;

    public Race(CountDownLatch readySignal, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.readySignal = readySignal;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            readySignal.await();
            System.out.println("Ready");
            Thread.sleep(100);
            System.out.println("Set");
            Thread.sleep(100);
            System.out.println("Go");
            startSignal.countDown();
            finishSignal.await();
            System.out.println("fin");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}