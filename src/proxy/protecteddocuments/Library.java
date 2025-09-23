package proxy.protecteddocuments;

import java.time.LocalDate;
import java.util.*;

public final class Library {
    private final Map<String, DocumentResource> byId = new HashMap<>();

    // Unprotected: store the real document
    public void addUnprotected(String id, LocalDate created, String content) {
        byId.put(id, new Document(id, created, content));
    }

    // Protected: create real + proxy; store ONLY the proxy; seed permissions
    public void addProtected(String id, LocalDate created, String content, String... allowedUsernames) {
        Document real = new Document(id, created, content);
        DocumentProxy proxy = new DocumentProxy(real);
        byId.put(id, proxy);

        AccessControlService acs = AccessControlService.getInstance();
        for (String u : allowedUsernames) {
            acs.allow(u, id);
        }
    }

    public Optional<DocumentResource> find(String id) {
        return Optional.ofNullable(byId.get(id));
    }

    public Collection<DocumentResource> all() {
        return Collections.unmodifiableCollection(byId.values());
    }

    // Convenience for a clean Main
    public String getContent(String id, User user) throws AccessDeniedException, NoSuchElementException {
        DocumentResource res = byId.get(id);
        if (res == null) throw new NoSuchElementException("No document: " + id);
        return res.getContent(user);
    }
}
