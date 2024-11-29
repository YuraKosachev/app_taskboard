package tms.webapp.taskboard.core.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import tms.webapp.taskboard.core.constants.CookieConstants;
import tms.webapp.taskboard.core.constants.LanguageConstants;

import java.util.Arrays;
import java.util.Optional;

public final class LanguageUtils {
    public static String getCurrentLanguage(HttpServletRequest request) {
        Optional<Cookie> cookie = CookieUtils.getCookie(request, CookieConstants.LANG_COOKIE_VALUE);
        if(cookie.isPresent() && Arrays.stream(LanguageConstants.LANGUAGES).anyMatch(x->x.equals(cookie.get().getValue()))) {
            return cookie.get().getValue();
        }
        return LanguageConstants.EN;
    }
}
