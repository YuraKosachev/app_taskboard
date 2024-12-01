package tms.webapp.taskboard.core.interfaces.services;

import jakarta.servlet.http.HttpServletRequest;

public interface TranslationService {
    String translate(String text);
    void setLocale(String locale);
}
