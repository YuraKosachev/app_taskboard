package tms.webapp.taskboard.core.models.entities.task;

import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;
import tms.webapp.taskboard.core.models.datetime.DateRange;

import java.util.UUID;

public class TaskPredicate {
    private final UUID[] uuids;
    private final String searchTerm;
    private final DateRange dateRange;
    private final TaskPriority[] priorities;
    private final TaskStatus[] statuses;
    private final Integer page;
    private final Integer size;
    private final UUID userId;

    public TaskPredicate(UUID[] uuids, String searchTerm,
                         DateRange dateRange, TaskPriority[] priorities,
                         TaskStatus[] statuses, UUID userId, Integer page, Integer size ) {
        this.uuids = uuids;
        this.searchTerm = searchTerm;
        this.dateRange = dateRange;
        this.priorities = priorities;
        this.statuses = statuses;
        this.page = page;
        this.size = size;
        this.userId = userId;
    }
    public UUID[] getUuids() {
        return uuids;
    }

    public UUID getUserId() {
        return userId;
    }

    public Integer getPage() {
        return page;
    }
    public Integer getSize() {
        return size;
    }

    public String getSearchTerm() {
        return searchTerm;
    }
    public DateRange getDateRange() {
        return dateRange;
    }
    public TaskPriority[] getPriorities() {
        return priorities;
    }
    public TaskStatus[] getStatuses() {
        return statuses;
    }
}
