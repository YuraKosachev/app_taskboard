package tms.webapp.taskboard.core.models.properties;

import tms.webapp.taskboard.core.enums.TaskStatus;

import java.util.UUID;

public class StatusPropertyChange extends PropertyChange<TaskStatus>{
    public StatusPropertyChange(UUID id, TaskStatus value) {
        super(id, value);
    }
}
