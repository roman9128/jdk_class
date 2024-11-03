package task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    private static final int COUNT_RUNNERS = 3;

    public static void main(String[] args) {

        CountDownLatch readySignal = new CountDownLatch(COUNT_RUNNERS);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(COUNT_RUNNERS);

        List<Runner> runners = new ArrayList<>(Arrays.asList(
                new Runner("Nif", readySignal, startSignal, finishSignal),
                new Runner("Naf", readySignal, startSignal, finishSignal),
                new Runner("Nuf", readySignal, startSignal, finishSignal)));

        new Race(readySignal, startSignal, finishSignal).start();
        for (Runner runner : runners) {
            runner.start();
        }
    }
}