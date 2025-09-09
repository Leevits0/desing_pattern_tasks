package strategy.algorithmperformacecomparison;

import java.util.*;

public class SortingBenchmark {
    public static void run() {
        int SMALL = 30, LARGE = 100_000, TRIALS = 3;
        // Generate two random test arrays: one small and one large.
        int[] small = randomArray(SMALL, 42), large = randomArray(LARGE, 43);

        // List of sorting strategies to test (Insertion, Merge, Quick).
        List<SortStrategy> algos = List.of(
                new InsertionSortStrategy(),
                new MergeSortStrategy(),
                new QuickSortStrategy()
        );
        Sorter sorter = new Sorter(null);

        // Warm-up run (to let the JVM optimize the code before timing).
        int[] warm = randomArray(10_000, 99);
        sorter.setStrategy(new QuickSortStrategy());
        sorter.sort(warm);

        // Run benchmarks for both dataset sizes and print a results table.
        for (int size : new int[]{SMALL, LARGE}) {
            int[] base = (size == SMALL) ? small : large;
            System.out.printf("%nDataset: %,d elements%n", size);
            System.out.println("----------------------------------------------");
            System.out.printf("%-28s %10s %10s%n", "Algorithm", "Avg ms", "Verified");
            System.out.println("----------------------------------------------");

            for (SortStrategy s : algos) {
                long total = 0;
                boolean ok = true;
                // Repeat each algorithm several times (TRIALS) for a fair average.
                for (int t = 0; t < TRIALS; t++) {
                    // Copy the base array so each trial sorts the same unsorted data.
                    int[] data = Arrays.copyOf(base, base.length);
                    sorter.setStrategy(s);
                    long t0 = System.nanoTime();
                    sorter.sort(data);
                    long t1 = System.nanoTime();
                    total += (t1 - t0);
                    // Verify that the array is actually sorted.
                    ok &= sorted(data);
                }
                // Convert total nanoseconds to average milliseconds.
                double ms = total / (1_000_000.0 * TRIALS);
                System.out.printf("%-28s %10.3f %10s%n", s.name(), ms, ok ? "yes" : "NO");
            }
        }
    }

    // Generate an array of n random integers with a fixed seed.
    private static int[] randomArray(int n, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt();
        return a;
    }

    // Check if an array is sorted in non-decreasing order.
    private static boolean sorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i] < a[i - 1]) return false;
        return true;
    }
}
