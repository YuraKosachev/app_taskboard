package tms.webapp.taskboard.core.models.entities.task;

import java.util.UUID;

public class TaskCountRequest {
    private final UUID userId;
    public TaskCountRequest(UUID userId) {
        this.userId = userId;
    }
    public UUID getUserId() {
        return userId;
    }
}
