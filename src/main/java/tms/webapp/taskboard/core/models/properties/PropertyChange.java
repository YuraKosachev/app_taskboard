package tms.webapp.taskboard.core.models.properties;

import java.util.UUID;

public class PropertyChange<T> {
    private final UUID entityId;
    private final T value;
    public PropertyChange(UUID id, T value) {
        this.entityId = id;
        this.value = value;
    }
    public UUID getEntityId() {
        return entityId;
    }
    public T getValue() {
        return value;
    }
}
