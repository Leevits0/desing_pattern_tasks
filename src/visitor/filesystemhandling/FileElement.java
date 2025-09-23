package visitor.filesystemhandling;


import java.util.Objects;

public final class FileElement implements FileSystemElement {
    private final String name;
    private final double sizeMb;

    public FileElement(String name, double sizeMb) {
        this.name = Objects.requireNonNull(name);
        if (sizeMb < 0) throw new IllegalArgumentException("sizeMb must be >= 0");
        this.sizeMb = sizeMb;
    }

    public double getSizeMb() { return sizeMb; }

    @Override public String getName() { return name; }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }

    @Override public String toString() {
        return name + " (" + sizeMb + " MB)";
    }
}
