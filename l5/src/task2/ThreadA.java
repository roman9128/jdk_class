package task2;

public class ThreadA extends Thread {

    private volatile Boolean switcher = false;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                switcher = !switcher;
                System.out.println(switcher);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    public boolean getSwitcher() {
        return switcher;
    }
}