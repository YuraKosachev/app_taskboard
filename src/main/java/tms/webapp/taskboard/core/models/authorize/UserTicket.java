package tms.webapp.taskboard.core.models.authorize;

import java.util.UUID;

public class UserTicket {
    private final UUID id;
    private final String name;
    private final String email;

    public UserTicket(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
