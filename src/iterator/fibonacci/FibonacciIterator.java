package iterator.fibonacci;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FibonacciIterator
 *
 * Iterates over Fibonacci numbers starting 1, 1, 2, 3, 5, ...
 * Uses Integer (per assignment). Note that the sequence will overflow
 * Integer.MAX_VALUE if iterated long enough; this is expected per spec.
 */
public class FibonacciIterator implements Iterator<Integer> {

    // If limit == null -> unbounded sequence; otherwise yield exactly limit items.
    private final Integer limit;
    private int produced = 0;

    // Internal state: we start with a = 0, b = 1, so next() returns b first -> 1
    private int a = 0;
    private int b = 1;

    public FibonacciIterator(Integer limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return limit == null || produced < limit;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more Fibonacci numbers available for this iterator.");
        }

        // Yield current b, then advance: (a, b) -> (b, a+b)
        int result = b;
        int next = a + b;
        a = b;
        b = next;

        produced++;
        return result;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() not supported for computed sequences.");
    }
}
