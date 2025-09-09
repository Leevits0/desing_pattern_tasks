package strategy.algorithmperformacecomparison;

public class Sorter {

    private SortStrategy strategy;

    public Sorter(SortStrategy strategy) { this.strategy = strategy; }

    public void setStrategy(SortStrategy strategy) { this.strategy = strategy; }

    public void sort(int[] arr) {
        if (strategy == null) throw new IllegalStateException("No strategy set");
        strategy.sort(arr);
    }

    public String strategyName() { return strategy == null ? "<none>" : strategy.name(); }
}
