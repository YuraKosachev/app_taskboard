package tms.webapp.taskboard.core.models.properties;

import tms.webapp.taskboard.core.enums.TaskPriority;

import java.util.UUID;

public class PriorityPropertyChange extends PropertyChange<TaskPriority>{
    public PriorityPropertyChange(UUID id, TaskPriority value) {
        super(id, value);
    }
}
