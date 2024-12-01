package tms.webapp.taskboard.services;

import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.CookieConstants;
import tms.webapp.taskboard.core.constants.LanguageConstants;
import tms.webapp.taskboard.core.interfaces.services.LanguageService;
import tms.webapp.taskboard.core.models.language.AlpinaLanguage;
import tms.webapp.taskboard.core.utils.CookieUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LanguageServiceImpl implements LanguageService {

    @Override
    public List<AlpinaLanguage> getAlpinaAvailableLanguage() {
        List<AlpinaLanguage> alpinaLanguages = new ArrayList<>();
        for (int i = 0, j = 1; i < LanguageConstants.LANGUAGES.length; i++, j++) {
            String lang = LanguageConstants.LANGUAGES[i];
            alpinaLanguages.add(AlpinaLanguage.getAlpinaLanguage(j, lang, lang));
        }
        return alpinaLanguages;
    }

    public String getCurrentLanguage(HttpServletRequest request) {
        Optional<Cookie> cookie = CookieUtils.getCookie(request, CookieConstants.LANG_COOKIE_VALUE);
        return cookie.isPresent() ? cookie.get().getValue() : LanguageConstants.EN;
    }
}
