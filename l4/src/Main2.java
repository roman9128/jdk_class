import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main2 {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("Anna");
        names.add("Anastasia");
        names.add("Anastasia");
        names.add("Elena");
        names.add("Lev");
        names.add("Lev");
        names.add("Ivan");
        names.add("Ivan");
        names.add("Semion");
        names.add("Semion");
        names.add("Roman");
        names.add("Roman");
        names.add("Innokentij");
        names.add("Svetlana");

        Set<String> set = makeSet(names);
        System.out.println(set);

        // System.out.println("Shortest in list: " + findShortestWord(names));
        // System.out.println("Longest in list: " + findLongestWord(names));

        // System.out.println("Shortest in set: " + findShortestWord(set));
        // System.out.println("Longest in set: " + findLongestWord(set));

        // System.out.println("First in set: " + findFirst(set));
        // System.out.println("Last in set: " + findLast(set));

        removeIfContains(set, 'a');
        System.out.println("After removal " + set);

    }

    public static void removeIfContains(Set<String> set, char letter) {
        set.removeIf(string -> string.toLowerCase().contains(String.valueOf(letter).toLowerCase()));
    }

    public static String findFirst(Set<String> set){
        return set.stream().min(Comparator.naturalOrder()).orElse(null);
    }

    public static String findLast(Set<String> set){
        return set.stream().min(Comparator.reverseOrder()).orElse(null);
    }

    public static String findLongestWord(List<String> list) {
        list.sort(Comparator.comparing(String::length));
        return list.getLast();

    }

    public static String findShortestWord(List<String> list) {
        list.sort(Comparator.comparing(String::length));
        return list.getFirst();

    }

    public static String findLongestWord(Set<String> set) {
        return set.stream().max(Comparator.comparing(String::length)).orElse(null);

    }

    public static String findShortestWord(Set<String> set) {
        return set.stream().min(Comparator.comparing(String::length)).orElse(null);

    }

    public static HashSet<String> makeSet(List<String> list) {
        return new HashSet<>(list);
    }
}