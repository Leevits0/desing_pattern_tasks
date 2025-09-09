package strategy.algorithmperformacecomparison;



/** Merge Sort (O(n log n)) → good general-purpose sort, stable, predictable,
// good on large sets.


//  "It works by recursively dividing the input array into two halves,
//  recursively sorting the two halves,
//  and finally merging them back together to obtain the sorted array. "
// https://www.geeksforgeeks.org/sorting-algorithms/ */


public class MergeSortStrategy implements SortStrategy {
    @Override public void sort(int[] a) {
        if (a.length < 2) return;
        int[] tmp = new int[a.length];
        ms(a, tmp, 0, a.length - 1);
    }
    private void ms(int[] a, int[] t, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        ms(a, t, lo, mid);
        ms(a, t, mid + 1, hi);
        merge(a, t, lo, mid, hi);
    }
    private void merge(int[] a, int[] t, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) t[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        while (i <= mid) t[k++] = a[i++];
        while (j <= hi)  t[k++] = a[j++];
        for (int p = lo; p <= hi; p++) a[p] = t[p];
    }
    @Override public String name() { return "Merge Sort"; }
}

