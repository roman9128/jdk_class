package task2;

import java.util.Arrays;

public class MyCollection<T> {

    private Object[] arr;
    private int size;

    public MyCollection(int length) {
        this.arr = new Object[length];
    }

    public void add(T element) {
        if (size >= arr.length) {
            Object[] arr2 = Arrays.copyOf(arr, size * 2);
            arr = arr2;
        }
        arr[size++] = element;
    }

    public void remove(T element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(element)) {
                for (int j = i; j < arr.length - 1; j++) {
                    arr[j] = arr[i + 1];
                }
                break;
            }

        }
    }

}