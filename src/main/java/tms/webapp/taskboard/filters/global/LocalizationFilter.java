package tms.webapp.taskboard.filters.global;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.CookieConstants;
import tms.webapp.taskboard.core.constants.LanguageConstants;
import tms.webapp.taskboard.core.utils.CookieUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(value="/*")
public class LocalizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Optional<Cookie> langCookie = CookieUtils.getCookie(request, CookieConstants.LANG_COOKIE_VALUE);
        Optional<String> lang = Optional.ofNullable(request.getParameter("lang"));

        if(lang.isPresent()
                && Arrays.stream(LanguageConstants.LANGUAGES).anyMatch(x->x.equals(lang.get()))
                && (langCookie.map(cookie -> !cookie.getValue().equals(lang.get())).orElse(true))) {

            Cookie newCookie = new Cookie(CookieConstants.LANG_COOKIE_VALUE, lang.get());
            newCookie.setMaxAge(CookieConstants.LANG_MAX_AGE);
            response.addCookie(newCookie);
        }

        chain.doFilter(request,response);
    }
}

