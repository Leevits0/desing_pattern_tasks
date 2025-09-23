package proxy.protecteddocuments;

import java.util.*;

final class AccessControlService {
    private final Map<String, Set<String>> permissions = new HashMap<>();

    private AccessControlService() {}

    private static class Holder {
        private static final AccessControlService INSTANCE = new AccessControlService();
    }

    static AccessControlService getInstance() {
        return Holder.INSTANCE;
    }

    synchronized void allow(String username, String documentId) {
        permissions.computeIfAbsent(username, k -> new HashSet<>()).add(documentId);
    }

    synchronized boolean isAllowed(String documentId, String username) {
        return permissions.getOrDefault(username, Collections.emptySet()).contains(documentId);
    }
}
