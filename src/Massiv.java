import java.util.Arrays;
import java.util.List;

public class Massiv<T> {
    private final T[] mas;


    @SafeVarargs
    public Massiv(T... elements) {
        this.mas = Arrays.copyOf(elements, elements.length);
    }
    @SuppressWarnings("unchecked")
    public Massiv(List<T> elements) {
        this.mas = (T[]) elements.toArray();
    }

    public int size() {
        return mas.length;
    }

    public T get(int index) {
        if (index < 0 || index >= mas.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return mas[index];
    }

    public Massiv<T> set(int index, T newValue) {
        if (index < 0 || index >= mas.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        T[] newMas = Arrays.copyOf(mas, mas.length);
        newMas[index] = newValue;
        return new Massiv<>(newMas);
    }

    public boolean isEmpty() {
        return mas.length == 0;
    }

    public T[] toArray() {
        return Arrays.copyOf(mas, mas.length);
    }

    public String toString() {
        return Arrays.toString(mas);
    }
}