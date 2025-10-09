package iterator.fibonacci;

import java.util.Iterator;

/**
 * Sequence
 *
 * A simple interface representing a (possibly computed) sequence that can
 * produce a fresh iterator on demand.
 */
public interface Sequence<T> {
    Iterator<T> iterator();
}
