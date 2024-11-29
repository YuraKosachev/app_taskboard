package tms.webapp.taskboard.core.adapters;

import com.google.gson.*;
import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;

import java.lang.reflect.Type;

public class TaskPriorityTypeAdapter implements JsonSerializer<TaskPriority>, JsonDeserializer<TaskPriority> {
    @Override
    public TaskPriority deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return TaskPriority.getTaskPriority(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(TaskPriority taskStatus, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(taskStatus.getPriority());
    }

}