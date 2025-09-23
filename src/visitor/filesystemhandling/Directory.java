package visitor.filesystemhandling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Directory implements FileSystemElement {
    private final String name;
    private final List<FileSystemElement> children = new ArrayList<>();

    public Directory(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Directory add(FileSystemElement child) {
        children.add(Objects.requireNonNull(child));
        return this; // fluent
    }

    public List<FileSystemElement> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override public String getName() { return name; }

    @Override
    public void accept(FileSystemVisitor visitor) {
        // Pre-order: visit directory, then its children
        visitor.visit(this);
        for (FileSystemElement child : children) {
            child.accept(visitor);
        }
    }

    @Override public String toString() {
        return name + "/";
    }
}
