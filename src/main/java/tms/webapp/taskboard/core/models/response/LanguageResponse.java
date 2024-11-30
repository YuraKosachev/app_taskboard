package tms.webapp.taskboard.core.models.response;

import tms.webapp.taskboard.core.models.language.AlpinaLanguage;

import java.util.List;

public class LanguageResponse {
    private final String current;
    private final List<AlpinaLanguage> langs;

    public LanguageResponse(final String current, final List<AlpinaLanguage> langs) {
        this.current = current;
        this.langs = langs;
    }
    public String getCurrent() {
        return current;
    }
    public List<AlpinaLanguage> getLang() {
        return langs;
    }

    public static LanguageResponse getInstance(final String current, final List<AlpinaLanguage> lang) {
        return new LanguageResponse(current, lang);
    }
}
