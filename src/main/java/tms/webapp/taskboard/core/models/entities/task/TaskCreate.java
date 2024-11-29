package tms.webapp.taskboard.core.models.entities.task;

import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;

import java.util.UUID;

public class TaskCreate {
    private final String title;
    private final String description;
    private final String descriptionHtml;
    private final TaskPriority priority;
    private final TaskStatus status;
    private UUID userId;

    public TaskCreate(String title, String description,
                      TaskPriority priority, TaskStatus status,
                      String descriptionHtml, UUID userId) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.descriptionHtml = descriptionHtml;
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public TaskPriority getPriority() {
        return priority;
    }
    public TaskStatus getStatus() {
        return status;
    }
}
