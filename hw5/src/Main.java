import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        List<Filosopher> filosophers = new ArrayList<>(Arrays.asList(
                new Filosopher("f1", fork5, fork1),
                new Filosopher("f2", fork1, fork2),
                new Filosopher("f3", fork2, fork3),
                new Filosopher("f4", fork3, fork4),
                new Filosopher("f5", fork4, fork5)));

        for (Filosopher filosopher : filosophers) {
            filosopher.start();
        }
    }
}