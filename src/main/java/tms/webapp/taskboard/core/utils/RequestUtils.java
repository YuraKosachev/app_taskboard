package tms.webapp.taskboard.core.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public final class RequestUtils {

    public static  <T> T getRequestBody(HttpServletRequest req, Class<T> tclass) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            return (T)JsonConverter.deserialize(reader, tclass);
        }
    }
}
