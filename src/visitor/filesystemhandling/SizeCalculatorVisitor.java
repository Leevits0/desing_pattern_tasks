package visitor.filesystemhandling;

public final class SizeCalculatorVisitor implements FileSystemVisitor {
    private double totalMb = 0.0;

    @Override
    public void visit(FileElement file) {
        totalMb += file.getSizeMb();
    }

    @Override
    public void visit(Directory directory) {
        // nothing to do for directories here
    }

    public double getTotalMb() {
        return totalMb;
    }
}
