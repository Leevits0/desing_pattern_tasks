package proxy.protecteddocuments;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();

        // Users
        User alice = new User("alice");
        User bob   = new User("bob");
        User carol = new User("carol");

        // Documents
        lib.addUnprotected("public-001", LocalDate.of(2025, 1, 10), "Public handbook v1.0");
        lib.addProtected("secret-007", LocalDate.of(2025, 2, 14), "Quarterly earnings (CONFIDENTIAL)", "alice", "carol");
        lib.addProtected("pii-123",    LocalDate.of(2025, 3, 3),  "Employee PII (STRICTLY CONFIDENTIAL)", "bob");

        // Minimal output: index + a few access attempts
        System.out.println("== Library index ==");
        lib.all().forEach(d -> System.out.println(d.getId() + " | created " + d.getCreationDate()));

        System.out.println("\n== Access attempts ==");
        attempt(lib, "public-001", alice);
        attempt(lib, "public-001", bob);
        attempt(lib, "secret-007", alice);
        attempt(lib, "secret-007", bob);
        attempt(lib, "secret-007", carol);
        attempt(lib, "pii-123", alice);
        attempt(lib, "pii-123", bob);
    }

    private static void attempt(Library lib, String docId, User user) {
        try {
            String content = lib.getContent(docId, user);
            System.out.println(user + " -> " + docId + " : OK | \"" + content + "\"");
        } catch (AccessDeniedException e) {
            System.out.println(user + " -> " + docId + " : " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println(user + " -> " + docId + " : not found");
        }
    }
}
