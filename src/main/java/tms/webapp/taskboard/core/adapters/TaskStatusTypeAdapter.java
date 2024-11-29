package tms.webapp.taskboard.core.adapters;

import com.google.gson.*;
import tms.webapp.taskboard.core.enums.TaskStatus;

import java.lang.reflect.Type;

public class TaskStatusTypeAdapter implements JsonSerializer<TaskStatus>, JsonDeserializer<TaskStatus> {
    @Override
    public TaskStatus deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return TaskStatus.getTaskStatus(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(TaskStatus taskStatus, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(taskStatus.getStatus());
    }

}
