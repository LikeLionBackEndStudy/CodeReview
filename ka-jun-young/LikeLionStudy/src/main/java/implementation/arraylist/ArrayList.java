package implementation.arraylist;

public class ArrayList {
    private int[] datum;
    private int size;

    ArrayList() {
        datum = new int[10];
        size = 0;
    }

    public void add(int data) {
        datum[size] = data;
        size++;
    }

    public int get(int idx) {
        return datum[idx];
    }

    public void removeAt(int idx) {
        for (int i = idx + 1; i < size; i++) {
            datum[i - 1] = datum[i];
        }
        size--;
    }

    public int size() {
        return size;
    }
}
