package example;

public class QuickSorter implements Sorter {

    protected int[] a;

    @Override
    public void load(int[] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public String getPlan() {
        return this.plan;
    }

    @Override
    public void sort() {
        quickSort(0, this.a.length - 1);
    }

    private void quickSort(int begin, int end) {
        if (begin < end) {
            int q = partition(begin, end);
            quickSort(begin, q - 1);
            quickSort(q + 1, end);
        }
    }

    private int partition(int begin, int end) {
        int pivot = a[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (a[j] < pivot) {
                i++;
                if (i != j) {
                    swap(i, j);
                }
            }
        }
        if (i + 1 != end) {
            swap(i + 1, end);
        }
        return i + 1;
    }

}
