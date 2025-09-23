package proxy.protecteddocuments;

import java.util.Objects;

public final class User {
    private final String username;

    public User(String username) {
        this.username = Objects.requireNonNull(username);
    }

    public String username() {
        return username;
    }

    @Override public String toString() {
        return username;
    }
}
