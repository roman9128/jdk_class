package task1;

public class Main {
    static Object objectA = new Object();
    static Object objectB = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (objectA) {
                    try {
                        System.out.println("first thread object a");
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (objectB) {
                        System.out.println("first thread object b");
                    }
                }
            }

        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (objectB) {
                    System.out.println("sec thread object b");
                    synchronized (objectA) {
                        System.out.println("sec thread object a");
                    }
                }
            }

        });

        thread1.start();
        thread2.start();
    }
}
// deadlock