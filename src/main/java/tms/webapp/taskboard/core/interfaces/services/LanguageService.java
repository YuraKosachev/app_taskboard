package tms.webapp.taskboard.core.interfaces.services;

import jakarta.servlet.http.HttpServletRequest;
import tms.webapp.taskboard.core.models.language.AlpinaLanguage;

import java.util.List;

public interface LanguageService {
    List<AlpinaLanguage> getAlpinaAvailableLanguage();
    String getCurrentLanguage(HttpServletRequest request);
}
