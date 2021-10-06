package example;

public class SelectSorter implements Sorter {

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
        for (int i = a.length - 1; i > 0; i--) {
            int maxIndex = selectMaxIndex(i);
            swap(maxIndex, i);
        }
    }

    private int selectMaxIndex(int end) {
        int maxIndex = 0;
        int max = -1;
        for (int i = 0; i <= end; i++) {
            if (a[i] > max) {
                max = a[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
