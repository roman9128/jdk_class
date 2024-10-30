import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main1 {
    public static void main(String[] args) throws Exception {

        List<String> names = new ArrayList<>();
        names.add("Anna");
        names.add("Anastasia");
        names.add("Elena");
        names.add("Lev");
        names.add("Ivan");
        names.add("Semion");
        names.add("Roman");
        names.add("Innokentij");
        names.add("Svetlana");

        Collections.sort(names);
        System.out.println(names);

        Collections.reverse(names);
        System.out.println(names);

        // Collections.sort(names, new Comparator<String>() {
        // @Override
        // public int compare(String o1, String o2) {
        // return o1.length() - o2.length();
        // }
        // });
        names.sort(Comparator.comparing(String::length));
        System.out.println(names);

    }
}
