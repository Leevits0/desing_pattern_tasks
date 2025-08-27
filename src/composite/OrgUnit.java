package composite;

public interface OrgUnit {
    String getName();

    /** Sum of salaries in this node (employee salary or dept subtree). */
    double totalSalary();

    /** XML of this node (indent controls pretty printing). */
    String toXml(int indent);

    /** Default “do nothing” for leaves; composites will override. */
    default void add(OrgUnit child) {
        throw new UnsupportedOperationException("Cannot add to leaf: " + getClass().getSimpleName());
    }

    default void remove(OrgUnit child) {
        throw new UnsupportedOperationException("Cannot remove from leaf: " + getClass().getSimpleName());
    }
}
