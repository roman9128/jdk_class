package task2;

public class ThreadB extends Thread {

    private int counter = 100;
    private ThreadA threadA;

    public ThreadB(ThreadA threadA) {
        this.threadA = threadA;
    }

    @Override
    public void run() {
        while (counter >= 0) {
            if (threadA.getSwitcher()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(counter--);
            }
        }
    }
}