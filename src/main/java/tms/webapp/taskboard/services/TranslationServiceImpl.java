package tms.webapp.taskboard.services;

import jakarta.servlet.http.HttpServletRequest;
import tms.webapp.taskboard.core.interfaces.services.LanguageService;
import tms.webapp.taskboard.core.interfaces.services.TranslationService;
import tms.webapp.taskboard.core.interfaces.store.LocalizationStore;

public class TranslationServiceImpl implements TranslationService {

    private final LocalizationStore localizationStore;

    private String locale = null;

    public TranslationServiceImpl(LocalizationStore localizationStore) {
        this.localizationStore = localizationStore;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    @Override
    public String translate(String text) {
        return localizationStore.getTranslate(locale, text);
    }
}
