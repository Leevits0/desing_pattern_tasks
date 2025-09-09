package strategy.algorithmperformacecomparison;

public interface SortStrategy {

    void sort(int[] arr);

    default String name() {

        return getClass().getSimpleName();
    }
}
