package visitor.filesystemhandling;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public final class SearchVisitor implements FileSystemVisitor {
    private final Predicate<FileElement> matches;
    private final List<FileElement> results = new ArrayList<>();

    // Common convenience constructors:

    // Match by extension like ".txt" (case-insensitive, dot optional)
    public static SearchVisitor byExtension(String ext) {
        String norm = ext.startsWith(".") ? ext.toLowerCase(Locale.ROOT) : ("." + ext).toLowerCase(Locale.ROOT);
        return new SearchVisitor(f -> f.getName().toLowerCase(Locale.ROOT).endsWith(norm));
    }

    // Match by substring in filename (case-insensitive)
    public static SearchVisitor byNameContains(String needle) {
        String n = needle.toLowerCase(Locale.ROOT);
        return new SearchVisitor(f -> f.getName().toLowerCase(Locale.ROOT).contains(n));
    }

    // Fully custom predicate
    public SearchVisitor(Predicate<FileElement> predicate) {
        this.matches = predicate;
    }

    @Override
    public void visit(FileElement file) {
        if (matches.test(file)) results.add(file);
    }

    @Override
    public void visit(Directory directory) {
        // nothing to do at directory nodes for the default search
    }

    public List<FileElement> getResults() {
        return List.copyOf(results);
    }
}
