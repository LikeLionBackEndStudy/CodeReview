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
        size--;
    }

    public int size() {
        return size;
    }
}
