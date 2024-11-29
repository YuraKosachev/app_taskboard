package tms.webapp.taskboard.core.models.entities.task;

import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private final UUID id;
    private final String title;
    private final LocalDate createdAt;
    private final String description;
    private final String descriptionHtml;
    private final TaskPriority priority;
    private final TaskStatus status;

    public Task(UUID id, String title,
                LocalDate date, String description,
                TaskPriority priority, TaskStatus status,
                String descritionHtml ) {
        this.id = id;
        this.title = title;
        this.createdAt = date;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.descriptionHtml = descritionHtml;
    }

    public UUID getId() {
        return id;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public String getTitle() {
        return title;
    }
    public LocalDate getDate() {
        return createdAt;
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
