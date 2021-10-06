package example;

public class SelectSorter extends BubbleSorter {
    
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
