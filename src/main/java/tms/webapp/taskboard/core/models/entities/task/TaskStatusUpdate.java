package tms.webapp.taskboard.core.models.entities.task;

import tms.webapp.taskboard.core.enums.TaskStatus;

import java.util.UUID;

public class TaskStatusUpdate {
    private final UUID taskId;
    private final TaskStatus status;
    public TaskStatusUpdate(final UUID taskId, final TaskStatus status) {
        this.taskId = taskId;
        this.status = status;
    }
    public UUID getTaskId() {
        return taskId;
    }
    public TaskStatus getStatus() {
        return status;
    }
}
