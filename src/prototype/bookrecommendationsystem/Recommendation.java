package prototype.bookrecommendationsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recommendation implements Prototype<Recommendation>, Cloneable, Serializable {
    private String targetAudience;
    private final List<Book> books = new ArrayList<>();

    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    // Deep-copy constructor
    public Recommendation(Recommendation other) {
        this.targetAudience = other.targetAudience;
        for (Book b : other.books) {
            this.books.add(b.clonePrototype()); // deep copy each Book
        }
    }

    @Override
    public Recommendation clonePrototype() {
        return new Recommendation(this);
    }

    @Override
    protected Recommendation clone() {
        return clonePrototype();
    }

    // Post-clone modifiers
    public void setTargetAudience(String newAudience) {
        this.targetAudience = newAudience;
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public boolean removeBookByIndex(int index) {
        if (index < 0 || index >= books.size()) return false;
        books.remove(index);
        return true;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Target audience: ").append(targetAudience).append("\n");
        if (books.isEmpty()) {
            sb.append("  (no books)\n");
        } else {
            for (int i = 0; i < books.size(); i++) {
                sb.append(String.format("  %d) %s%n", i + 1, books.get(i)));
            }
        }
        return sb.toString();
    }
}
