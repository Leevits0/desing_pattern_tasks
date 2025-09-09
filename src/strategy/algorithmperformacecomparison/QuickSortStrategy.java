package strategy.algorithmperformacecomparison;

/** Quick Sort (O(n log n)) → fastest in practice for large random sets.
// 1. Pick a pivot element.
// 2. Put smaller elements on the left, larger on the right.
//    (Pivot ends up in its correct position.)
// 3. Recursively sort the left and right parts.
// Fast on average O(n log n), but worst case O(n²) if pivots are bad.
// https://www.geeksforgeeks.org/sorting-algorithms/ */

public class QuickSortStrategy implements SortStrategy {
    @Override public void sort(int[] a) {
        if (a.length < 2) return;
        qs(a, 0, a.length - 1);
    }
    private void qs(int[] a, int lo, int hi) {
        while (lo < hi) {
            int p = hoare(a, lo, hi);
            if (p - lo < hi - p) { qs(a, lo, p); lo = p + 1; }
            else { qs(a, p + 1, hi); hi = p; }
        }
    }
    private int hoare(int[] a, int lo, int hi) {
        int m = median3idx(a, lo, lo + (hi - lo)/2, hi), pivot = a[m];
        int i = lo - 1, j = hi + 1;
        while (true) {
            do { i++; } while (a[i] < pivot);
            do { j--; } while (a[j] > pivot);
            if (i >= j) return j;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
    }
    private int median3idx(int[] a, int i, int j, int k) {
        int ai=a[i], aj=a[j], ak=a[k];
        if (ai < aj) return (aj < ak) ? j : (ai < ak ? k : i);
        else         return (ai < ak) ? i : (aj < ak ? k : j);
    }
    @Override public String name() { return "Quick Sort (Hoare, M3)"; }
}
