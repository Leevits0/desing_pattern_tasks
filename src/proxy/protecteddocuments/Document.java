package proxy.protecteddocuments;

import java.time.LocalDate;
import java.util.Objects;

final class Document implements DocumentResource {
    private final String id;
    private final LocalDate creationDate;
    private final String content;

    Document(String id, LocalDate creationDate, String content) {
        this.id = Objects.requireNonNull(id);
        this.creationDate = Objects.requireNonNull(creationDate);
        this.content = Objects.requireNonNull(content);
    }

    @Override public String getId() { return id; }
    @Override public LocalDate getCreationDate() { return creationDate; }

    // For unprotected docs, this is called directly from Library.
    @Override public String getContent(User user) {
        return content;
    }

    // Only the proxy can call this to fetch protected content.
    String rawContent() { return content; }
}
