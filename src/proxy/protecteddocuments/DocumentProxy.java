package proxy.protecteddocuments;


import java.time.LocalDate;
import java.util.Objects;

final class DocumentProxy implements DocumentResource {
    private final Document real;
    private final AccessControlService acs = AccessControlService.getInstance();

    DocumentProxy(Document real) {
        this.real = Objects.requireNonNull(real);
    }

    @Override public String getId() { return real.getId(); }
    @Override public LocalDate getCreationDate() { return real.getCreationDate(); }

    @Override
    public String getContent(User user) throws AccessDeniedException {
        if (acs.isAllowed(real.getId(), user.username())) {
            return real.rawContent();
        }
        throw new AccessDeniedException(
                "Access denied: '" + user.username() + "' cannot read '" + real.getId() + "'."
        );
    }
}
