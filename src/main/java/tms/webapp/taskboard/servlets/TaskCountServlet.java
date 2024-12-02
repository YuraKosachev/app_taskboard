package tms.webapp.taskboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.ServletNameConstants;
import tms.webapp.taskboard.core.enums.ContentType;
import tms.webapp.taskboard.core.enums.TaskStatus;
import tms.webapp.taskboard.core.interfaces.services.TaskService;
import tms.webapp.taskboard.core.models.entities.task.TaskCountRequest;
import tms.webapp.taskboard.core.models.response.BaseResponse;
import tms.webapp.taskboard.core.utils.JsonConverter;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = ServletNameConstants.TASK_COUNT, value = AppUrlConstants.TASK_COUNT_URL)
public class TaskCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BaseResponse baseResponse = null;
        try {
            TaskService taskService = ServiceFactory.getTaskService();
            UUID userId = (UUID) req.getAttribute("userid");
            Map<TaskStatus, Integer> map = taskService.getCountStatuse(new TaskCountRequest(userId));
            resp.setContentType(ContentType.APPLICATION_JSON.toString());
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            baseResponse = BaseResponse.getInstance(true,JsonConverter.serialize(map));

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            baseResponse = BaseResponse.getInstance(false, e.getMessage());
        } finally {
            try (PrintWriter pw = resp.getWriter()) {
                String json = JsonConverter.serialize(baseResponse);
                pw.write(json);
            }
        }
    }
}
