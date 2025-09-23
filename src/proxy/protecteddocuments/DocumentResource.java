package proxy.protecteddocuments;

import java.time.LocalDate;

public interface DocumentResource {
    String getId();
    LocalDate getCreationDate();
    String getContent(User user) throws AccessDeniedException;
}
