package tms.webapp.taskboard.core.interfaces.services;

import tms.webapp.taskboard.core.models.language.AlpinaLanguage;

import java.util.List;

public interface LanguageService {
    List<AlpinaLanguage> getAlpinaAvailableLanguage();
}
