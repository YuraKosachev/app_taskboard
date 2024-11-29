package tms.webapp.taskboard.core.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public final class CookieUtils {
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        if(request.getCookies() == null)
            return Optional.empty();
        return Arrays.stream(request.getCookies())
                .filter(c -> name.equals(c.getName()))
                .findAny();
    }
}