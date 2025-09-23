package visitor.filesystemhandling;

public interface FileSystemVisitor {
    void visit(FileElement file);
    void visit(Directory directory);
}
