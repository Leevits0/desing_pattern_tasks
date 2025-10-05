package prototype.bookrecommendationsystem;

import java.io.Serializable;

public class Book implements Prototype<Book>, Cloneable, Serializable {
    private String title;
    private String author;
    private String genre;
    private int publicationYear;

    public Book(String title, String author) {
        this(title, author, "Unknown", 0);
    }

    public Book(String title, String author, String genre, int publicationYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    // Copy constructor (used for deep copies)
    public Book(Book other) {
        this.title = other.title;
        this.author = other.author;
        this.genre = other.genre;
        this.publicationYear = other.publicationYear;
    }

    @Override
    public Book clonePrototype() {
        return new Book(this);
    }

    @Override
    protected Book clone() {
        return clonePrototype();
    }

    // Getters & setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getPublicationYear() { return publicationYear; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    @Override
    public String toString() {
        String year = (publicationYear > 0) ? String.valueOf(publicationYear) : "n/a";
        return String.format("“%s” by %s [%s, %s]", title, author, genre, year);
    }
}
