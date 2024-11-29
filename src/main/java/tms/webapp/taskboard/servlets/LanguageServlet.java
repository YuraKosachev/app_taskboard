package tms.webapp.taskboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.ServletNameConstants;
import tms.webapp.taskboard.core.enums.ContentType;
import tms.webapp.taskboard.core.interfaces.services.LanguageService;
import tms.webapp.taskboard.core.interfaces.services.TaskService;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.core.models.entities.task.TaskPredicate;
import tms.webapp.taskboard.core.models.language.AlpinaLanguage;
import tms.webapp.taskboard.core.utils.JsonConverter;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name= ServletNameConstants.LANGUAGE, value = AppUrlConstants.LANGUAGE_URL)
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LanguageService langService = ServiceFactory.getLanguageService();
        List<AlpinaLanguage> languages = langService.getAlpinaAvailableLanguage();
        resp.setContentType(ContentType.APPLICATION_JSON.toString());
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter pw = resp.getWriter()) {
            String json = JsonConverter.serialize(languages);
            pw.write(json);
        }
    }
}
