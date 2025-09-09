package strategy.algorithmperformacecomparison;


// Insertion Sort (O(n²)) → best for small/narrow datasets.


/** "It is like sorting playing cards in your hands.
// You split the cards into two groups: the sorted cards and the unsorted cards.
// Then, you pick a card from the unsorted group,
// and put it in the right place in the sorted group."
// https://www.geeksforgeeks.org/sorting-algorithms/ */


public class InsertionSortStrategy implements SortStrategy {
    @Override public void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i], j = i - 1;
            while (j >= 0 && a[j] > key) { a[j + 1] = a[j]; j--; }
            a[j + 1] = key;
        }
    }
    @Override public String name() { return "Insertion Sort"; }
}
