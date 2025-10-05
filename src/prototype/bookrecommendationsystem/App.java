package prototype.bookrecommendationsystem;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private static final Scanner in = new Scanner(System.in);
    private static RecommendationRepository repo = new RecommendationRepository();
    private static final File SAVE_FILE = new File("recommendations.ser");

    public static void main(String[] args) {
        seedSampleData(repo);
        System.out.println("=== Book Recommendation System (Prototype Pattern) ===");

        boolean running = true;
        while (running) {
            showMenu();
            String choice = prompt("Choose an option: ").trim();
            switch (choice) {
                case "1" -> listRecommendations();
                case "2" -> viewRecommendation();
                case "3" -> createFromScratch();
                case "4" -> cloneAndModify();
                case "5" -> saveAll();
                case "6" -> loadAll();
                case "0" -> { running = false; System.out.println("Goodbye!"); }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("""
            -------------------------------------------------
            1) List recommendations
            2) View a recommendation
            3) Create recommendation from scratch
            4) Clone an existing recommendation and modify
            5) Save all to disk
            6) Load all from disk
            0) Exit
            -------------------------------------------------""");
    }

    private static void listRecommendations() {
        if (repo.size() == 0) {
            System.out.println("(no recommendations yet)");
            return;
        }
        for (int i = 0; i < repo.size(); i++) {
            Recommendation r = repo.get(i);
            System.out.printf("%d) %s (%d books)%n", i + 1, r.getTargetAudience(), r.getBooks().size());
        }
    }

    private static void viewRecommendation() {
        int idx = askIndex("Enter recommendation # to view");
        Recommendation r = repo.get(idx);
        if (r == null) {
            System.out.println("Not found.");
            return;
        }
        System.out.println("-------------------------------------------------");
        System.out.println(r);
        System.out.println("-------------------------------------------------");

        boolean back = false;
        while (!back) {
            System.out.println("""
                a) Add a book
                r) Remove a book
                t) Change target audience
                b) Back
                """);
            String ch = prompt("Option: ").trim().toLowerCase();
            switch (ch) {
                case "a" -> addBookTo(r);
                case "r" -> removeBookFrom(r);
                case "t" -> {
                    String ta = prompt("New target audience: ");
                    r.setTargetAudience(ta);
                    System.out.println("Audience updated.");
                }
                case "b" -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void createFromScratch() {
        String audience = prompt("Target audience (e.g., 'Teens who love fantasy'): ");
        Recommendation r = new Recommendation(audience);
        System.out.println("Add books (leave title empty to stop).");
        while (true) {
            Book b = promptBook();
            if (b == null) break;
            r.addBook(b);
        }
        repo.add(r);
        System.out.println("Created new recommendation.\n");
    }

    private static void cloneAndModify() {
        int idx = askIndex("Enter recommendation # to clone");
        Recommendation original = repo.get(idx);
        if (original == null) {
            System.out.println("Not found.");
            return;
        }
        Recommendation clone = original.clonePrototype(); // Prototype in action
        System.out.println("Cloned. You can now modify the clone without affecting the original.");
        String newAudience = prompt("New target audience (or leave empty to keep '" + clone.getTargetAudience() + "'): ");
        if (!newAudience.isBlank()) clone.setTargetAudience(newAudience);

        boolean done = false;
        while (!done) {
            System.out.println("""
                --- Edit Cloned Recommendation ---
                a) Add a book
                r) Remove a book
                v) View current clone
                d) Done (save clone)
                x) Discard
                """);
            String ch = prompt("Option: ").trim().toLowerCase();
            switch (ch) {
                case "a" -> addBookTo(clone);
                case "r" -> removeBookFrom(clone);
                case "v" -> System.out.println(clone);
                case "d" -> {
                    repo.add(clone);
                    System.out.println("Clone saved as a new recommendation.");
                    done = true;
                }
                case "x" -> { System.out.println("Clone discarded."); done = true; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void saveAll() {
        try {
            RecommendationRepository.saveToFile(repo, SAVE_FILE);
            System.out.println("Saved to " + SAVE_FILE.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    private static void loadAll() {
        if (!SAVE_FILE.exists()) {
            System.out.println("No save file found (" + SAVE_FILE.getName() + ").");
            return;
        }
        try {
            repo = RecommendationRepository.loadFromFile(SAVE_FILE);
            System.out.println("Loaded " + repo.size() + " recommendation list(s).");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load failed: " + e.getMessage());
        }
    }

    // ---- Helpers -------------------------------------------------------------

    private static void seedSampleData(RecommendationRepository repo) {
        Recommendation teensFantasy = new Recommendation("Teens who love fantasy");
        teensFantasy.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937));
        teensFantasy.addBook(new Book("Eragon", "Christopher Paolini", "Fantasy", 2002));
        repo.add(teensFantasy);

        Recommendation scifiBeginners = new Recommendation("Adults new to Sci-Fi");
        scifiBeginners.addBook(new Book("The Martian", "Andy Weir", "Sci-Fi", 2011));
        scifiBeginners.addBook(new Book("Project Hail Mary", "Andy Weir", "Sci-Fi", 2021));
        repo.add(scifiBeginners);
    }

    private static String prompt(String msg) {
        System.out.print(msg);
        return in.nextLine();
    }

    private static int askIndex(String label) {
        listRecommendations();
        String s = prompt(label + " (#): ");
        try {
            return Integer.parseInt(s.trim()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static Book promptBook() {
        String title = prompt("  Title: ").trim();
        if (title.isEmpty()) return null;
        String author = prompt("  Author: ").trim();
        String genre = prompt("  Genre (optional): ").trim();
        String yearStr = prompt("  Publication year (optional): ").trim();
        int year = 0;
        if (!yearStr.isBlank()) {
            try { year = Integer.parseInt(yearStr); } catch (NumberFormatException ignored) {}
        }
        if (genre.isBlank()) genre = "Unknown";
        return new Book(title, author, genre, year);
    }

    private static void addBookTo(Recommendation r) {
        Book b = promptBook();
        if (b != null) {
            r.addBook(b);
            System.out.println("Book added.");
        } else {
            System.out.println("No book added.");
        }
    }

    private static void removeBookFrom(Recommendation r) {
        if (r.getBooks().isEmpty()) {
            System.out.println("No books to remove.");
            return;
        }
        System.out.println(r);
        String s = prompt("Remove which #? ");
        try {
            int idx = Integer.parseInt(s.trim()) - 1;
            if (r.removeBookByIndex(idx)) {
                System.out.println("Removed.");
            } else {
                System.out.println("Invalid index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }
}
