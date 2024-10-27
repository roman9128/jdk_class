package arr;

public class Main {

    public static void main(String[] args) {

        Integer[] arr1 = {1};
        String[] arr2 = {"aa"};

        System.out.println(compareArrays(arr1, arr2));

    }

    private static <T1, T2> boolean compareArrays(T1[] t1, T2[] t2) {
        if (t1.length != t2.length) {
            return false;
        }
        for (int i = 0; i < t1.length; i++) {
            if (!t1[i].getClass().equals(t2[i].getClass())) {
                return false;
            }
        }
        return true;
    }
}

/*
 * Напишите обобщенный метод compareArrays(),
 * который принимает два массива и возвращает true,
 * если они одинаковые, и false в противном случае.
 * Массивы могут быть любого типа данных, но должны иметь одинаковую длину
 * и содержать элементы одного типа попарно по индексам.
 */