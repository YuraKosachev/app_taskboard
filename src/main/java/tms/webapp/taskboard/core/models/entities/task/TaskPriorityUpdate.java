package tms.webapp.taskboard.core.models.entities.task;

import tms.webapp.taskboard.core.enums.TaskPriority;

import java.util.UUID;

public class TaskPriorityUpdate {
    private final UUID taskId;
    private final TaskPriority priority;
    public TaskPriorityUpdate(final UUID taskId, final TaskPriority priority) {
        this.taskId = taskId;
        this.priority = priority;
    }
    public UUID getTaskId() {
        return taskId;
    }

    public TaskPriority getPriority() {
        return priority;
    }
}