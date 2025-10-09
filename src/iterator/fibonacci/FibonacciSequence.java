package iterator.fibonacci;

import java.util.Iterator;

/**
 * FibonacciSequence
 *
 * DESIGN DECISION (state location):
 * ---------------------------------
 * I keep *all iteration state* inside FibonacciIterator, not here. This makes
 * FibonacciSequence immutable and stateless: each call to iterator() returns
 * an independent iterator with its own internal (prev, curr, index) state.
 * Benefits:
 *   - Multiple iterators can traverse the sequence concurrently without
 *     interfering with one another.
 *   - Sequence instances are lightweight; they don’t retain changing state.
 *   - Clear separation of concerns: Sequence manufactures iterators; iterators
 *     handle traversal and on-the-fly computation.
 * Trade-off:
 *   - If you wanted to *share* progress across iterators (e.g., a pooled,
 *     memoized sequence), you’d instead hold shared state here. That would
 *     couple iterators together, which isn’t desired for this assignment.
 */
public class FibonacciSequence implements Sequence<Integer> {

    private final boolean bounded;
    private final int limit; // valid only when bounded == true

    /** Creates an unbounded Fibonacci sequence (client must stop iteration). */
    public FibonacciSequence() {
        this.bounded = false;
        this.limit = -1;
    }

    /**
     * Creates a bounded Fibonacci sequence that yields exactly `limit` numbers.
     * @throws IllegalArgumentException if limit < 0
     */
    public FibonacciSequence(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        }
        this.bounded = true;
        this.limit = limit;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciIterator(bounded ? limit : null);
    }
}
