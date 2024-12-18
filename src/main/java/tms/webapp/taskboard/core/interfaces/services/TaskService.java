package tms.webapp.taskboard.core.interfaces.services;

import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;
import tms.webapp.taskboard.core.models.entities.task.*;
import tms.webapp.taskboard.core.models.response.PagedResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TaskService {
    Map<TaskStatus, Integer> getCountStatuse(TaskCountRequest request);
    int create(TaskCreate task);
    int update(Task task);
    List<Task> getByPredicate(TaskPredicate predicate);
    PagedResponse<Task> getPagedByPredicate(TaskPredicate predicate);
    int delete(UUID id);
    int setStatus(UUID id, TaskStatus status);
    int setPriority(UUID id, TaskPriority priority);

}
