package tms.webapp.taskboard.core.models;

import java.util.UUID;

public class UserFilter {
    private final UUID uuid;

    public UserFilter(UUID uuid) {
        this.uuid = uuid;
    }
    public UUID getUuid() {
        return uuid;
    }
}
