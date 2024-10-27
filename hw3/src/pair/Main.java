package pair;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<Integer, String>(20, "text");
        System.out.println(pair);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }

}
