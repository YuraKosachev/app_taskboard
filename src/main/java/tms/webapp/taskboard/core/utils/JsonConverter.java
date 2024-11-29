package tms.webapp.taskboard.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tms.webapp.taskboard.core.adapters.LocalDateTypeAdapter;
import tms.webapp.taskboard.core.adapters.TaskPriorityTypeAdapter;
import tms.webapp.taskboard.core.adapters.TaskStatusTypeAdapter;
import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;

import java.io.BufferedReader;
import java.time.LocalDate;

public final class JsonConverter {
    public static <T> String serialize(T obj)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(TaskStatus.class, new TaskStatusTypeAdapter())
                .registerTypeAdapter(TaskPriority.class, new TaskPriorityTypeAdapter())
                .create();
        return gson.toJson(obj);
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(TaskStatus.class, new TaskStatusTypeAdapter())
                .registerTypeAdapter(TaskPriority.class, new TaskPriorityTypeAdapter())
                .create();
        return (T)gson.fromJson(json, tClass);
    }
    public static <T> T deserialize(BufferedReader buffer, Class<T> tClass) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(TaskStatus.class, new TaskStatusTypeAdapter())
                .registerTypeAdapter(TaskPriority.class, new TaskPriorityTypeAdapter())
                .create();
        return (T)gson.fromJson(buffer, tClass);
    }
}
