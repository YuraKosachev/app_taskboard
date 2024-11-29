package tms.webapp.taskboard.core.interfaces.services;

import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.core.models.entities.task.TaskCreate;
import tms.webapp.taskboard.core.models.entities.task.TaskPredicate;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    int create(TaskCreate task);
    int update(Task task);
    List<Task> getByPredicate(TaskPredicate predicate);

    int delete(UUID id);
    int setStatus(UUID id, TaskStatus status);
    int setPriority(UUID id, TaskPriority priority);

}
