package visitor.filesystemhandling;

public class Main {
    public static void main(String[] args) {
        // Build a small tree:
        Directory root = new Directory("root")
                .add(new FileElement("readme.txt", 0.2))
                .add(new FileElement("cover.jpg", 1.8))
                .add(new Directory("docs")
                        .add(new FileElement("design.pdf", 4.5))
                        .add(new FileElement("report.txt", 1.1)))
                .add(new Directory("bin")
                        .add(new FileElement("tool", 2.0))
                        .add(new FileElement("script.sh", 0.05)));

        // 1) Size calculation
        SizeCalculatorVisitor sizeVisitor = new SizeCalculatorVisitor();
        root.accept(sizeVisitor);
        System.out.printf("Total size: %.2f MB%n", sizeVisitor.getTotalMb());

        // 2) Search by extension
        SearchVisitor txtSearch = SearchVisitor.byExtension(".txt");
        root.accept(txtSearch);
        System.out.println("TXT files:");
        txtSearch.getResults().forEach(f -> System.out.println(" - " + f));

        // 3) Another search example: filenames containing "re"
        SearchVisitor nameSearch = SearchVisitor.byNameContains("re");
        root.accept(nameSearch);
        System.out.println("Files with 're' in name:");
        nameSearch.getResults().forEach(f -> System.out.println(" - " + f));
    }
}
