package tms.webapp.taskboard.services;

import com.google.gson.Gson;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.LanguageConstants;
import tms.webapp.taskboard.core.interfaces.services.LanguageService;
import tms.webapp.taskboard.core.models.language.AlpinaLanguage;

import java.util.ArrayList;
import java.util.List;

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


}
