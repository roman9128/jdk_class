package task1;
import java.io.DataInput;
import java.io.InputStream;

public class Container<T extends Comparable<T>, V extends InputStream & DataInput, K extends Number> {

    private T t;
    private V v;
    private K k;

    public Container(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }

    public void className() {
        System.out.println(t.getClass().getName());
        System.out.println(v.getClass().getName());
        System.out.println(k.getClass().getName());
    }
}