package tms.webapp.taskboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.CookieConstants;
import tms.webapp.taskboard.core.constants.LanguageConstants;
import tms.webapp.taskboard.core.constants.ServletNameConstants;
import tms.webapp.taskboard.core.enums.ContentType;
import tms.webapp.taskboard.core.interfaces.services.LanguageService;
import tms.webapp.taskboard.core.interfaces.services.TaskService;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.core.models.entities.task.TaskPredicate;
import tms.webapp.taskboard.core.models.language.AlpinaLanguage;
import tms.webapp.taskboard.core.models.response.LanguageResponse;
import tms.webapp.taskboard.core.utils.CookieUtils;
import tms.webapp.taskboard.core.utils.JsonConverter;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(name= ServletNameConstants.LANGUAGE, value = AppUrlConstants.LANGUAGE_URL)
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LanguageService langService = ServiceFactory.getLanguageService();
        List<AlpinaLanguage> languages = langService.getAlpinaAvailableLanguage();
        Optional<Cookie> cookie = CookieUtils.getCookie(req, CookieConstants.LANG_COOKIE_VALUE);
        String currentLang = cookie.isPresent() ? cookie.get().getValue() : LanguageConstants.EN;
        LanguageResponse response = LanguageResponse.getInstance(currentLang, languages);

        resp.setContentType(ContentType.APPLICATION_JSON.toString());
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter pw = resp.getWriter()) {
            String json = JsonConverter.serialize(response);
            pw.write(json);
        }
    }
}
