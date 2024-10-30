import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main3 {
    public static void main(String[] args) {

        Map<String, String> phonebook = new HashMap<>();
        phonebook.put("123456", "nyghnyt");
        phonebook.put("1234465356", "ngfhn");
        phonebook.put("732683386", "fyndythn");
        phonebook.put("12338656", "yhtyth");
        phonebook.put("38938963", "dfythyth");
        phonebook.put("386386", "atrhyt");
        phonebook.put("7563465322", "ztrhyth");
        phonebook.put("83928973893", "ydhyth");

        // String result = findMinNumber(phonebook);
        String result = findBigName(phonebook);
        System.out.println(result);

    }

    public static String findMinNumber(Map<String, String> map) {
        String result = String.valueOf(map.keySet().stream().map(Long::parseLong)
                .collect(Collectors.toSet())
                .stream().min(Comparator.naturalOrder()).get());
        return result + " - " + map.get(result);
    }

    public static String findBigName(Map<String, String> map) {
        // return map.entrySet().stream()
        // .filter(entry ->
        // entry.getValue().equals(map.values().stream().max(Comparator.naturalOrder()).get()))
        // .collect(Collectors.toList()).toString();
        String result = map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
                .get().getKey();
        return result + " - " + map.get(result);
    }

}