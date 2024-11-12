import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Main {
    public static void main(String[] args) {

        Integer[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        Integer[] arr2 = { 5, 6, 7, 8, 10, 11 };
        // List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        // System.out.println(Lists.reverse(list));
        // System.out.println(Lists.charactersOf("null"));
        // System.out.println(Lists.partition(list, 2));
        Set<Integer> set1 = Sets.union(Sets.newTreeSet(Arrays.asList(arr)), Sets.newTreeSet(Arrays.asList(arr2)));
        Set<Integer> set2 = Sets.intersection(Sets.newTreeSet(Arrays.asList(arr)),
                Sets.newTreeSet(Arrays.asList(arr2)));
        System.out.println(set1);
        System.out.println(set2);
        System.out.println(Sets.difference(set1, set2));

    }

}
