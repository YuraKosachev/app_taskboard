package tms.webapp.taskboard.services;

import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;
import tms.webapp.taskboard.core.interfaces.services.TaskService;
import tms.webapp.taskboard.core.interfaces.units.base.DeleteUnit;
import tms.webapp.taskboard.core.interfaces.units.base.InsertUnit;
import tms.webapp.taskboard.core.interfaces.units.base.SelectUnit;
import tms.webapp.taskboard.core.interfaces.units.base.UpdateUnit;
import tms.webapp.taskboard.core.models.entities.task.*;

import java.util.List;
import java.util.UUID;

public class TaskServiceImpl implements TaskService {
    private final SelectUnit<TaskPredicate, Task> selectUnit;
    private final UpdateUnit<Task> updateUnit;
    private final DeleteUnit<UUID> deleteUnit;
    private final InsertUnit<TaskCreate> insertUnit;
    private final UpdateUnit<TaskPriorityUpdate> priorityUpdateUnit;
    private final UpdateUnit<TaskStatusUpdate> statusUpdateUnit;

    public TaskServiceImpl(SelectUnit<TaskPredicate, Task> selectUnit,
                           UpdateUnit<Task> updateUnit,
                           DeleteUnit<UUID> deleteUnit,
                           InsertUnit<TaskCreate> insertUnit,
                           UpdateUnit<TaskPriorityUpdate> priorityUpdateUnit,
                           UpdateUnit<TaskStatusUpdate> statusUpdateUnit) {

        this.selectUnit = selectUnit;
        this.updateUnit = updateUnit;
        this.deleteUnit = deleteUnit;
        this.insertUnit = insertUnit;
        this.priorityUpdateUnit = priorityUpdateUnit;
        this.statusUpdateUnit = statusUpdateUnit;
    }


    @Override
    public int create(TaskCreate task) {
        return insertUnit.add(task);
    }

    @Override
    public int update(Task task) {
        return updateUnit.update(task);
    }

    @Override
    public List<Task> getByPredicate(TaskPredicate predicate) {
        return selectUnit.getAll(predicate);
    }

    @Override
    public int delete(UUID id) {
        return deleteUnit.remove(id);
    }


    @Override
    public int setStatus(UUID id, TaskStatus status) {
        return statusUpdateUnit.update(new TaskStatusUpdate(id, status));
    }

    @Override
    public int setPriority(UUID id, TaskPriority priority) {
        return priorityUpdateUnit.update(new TaskPriorityUpdate(id, priority));
    }
}
