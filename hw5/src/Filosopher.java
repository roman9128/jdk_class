import java.util.Random;

public class Filosopher extends Thread {

    private static final int MAX_EAT_COUNT = 3;

    private String name;
    private boolean canEat;
    private boolean isHungry;
    private int eatCount;
    private Fork leftFork;
    private Fork rightFork;

    public Filosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;
        this.canEat = true;
        this.isHungry = true;
        this.eatCount = 0;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (isHungry) {
            try {
                Thread.sleep(new Random().nextInt(new Random().nextInt(100, 300)));
                if (canEat) {
                    eat();
                } else {
                    System.out.println(this.name + " can't eat now");
                    Thread.sleep(new Random().nextInt(new Random().nextInt(200, 300)));
                    canEat = true;
                }
                if (eatCount == MAX_EAT_COUNT) {
                    System.out.println(this.name + " is not hungry anymore & thinks");
                    isHungry = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void eat() {
        if (eatCount < MAX_EAT_COUNT) {
            try {
                if (leftFork.isOnTable() && rightFork.isOnTable()) {
                    leftFork.setOnTable(false);
                    rightFork.setOnTable(false);
                    System.out.println(this.name + " took forks ## " + leftFork.getPosition() + " & " + rightFork.getPosition());
                    Thread.sleep(new Random().nextInt(500, 1000));
                    System.out.println(this.name + " ate " + (eatCount + 1) + " time");
                    eatCount++;
                    canEat = false;
                    leftFork.setOnTable(true);
                    rightFork.setOnTable(true);
                    System.out.println(this.name + " put forks ## " + leftFork.getPosition() + " & " + rightFork.getPosition() + " on table and thinks");
                    Thread.sleep(new Random().nextInt(400, 900));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}