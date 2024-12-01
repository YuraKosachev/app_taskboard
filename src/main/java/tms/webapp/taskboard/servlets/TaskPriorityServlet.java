package tms.webapp.taskboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.ServletNameConstants;
import tms.webapp.taskboard.core.enums.ContentType;
import tms.webapp.taskboard.core.interfaces.services.TaskService;
import tms.webapp.taskboard.core.models.properties.PriorityPropertyChange;
import tms.webapp.taskboard.core.models.response.BaseResponse;
import tms.webapp.taskboard.core.utils.JsonConverter;
import tms.webapp.taskboard.core.utils.RequestUtils;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = ServletNameConstants.TASK_PRIORITY, value = AppUrlConstants.TASK_PRIORITY_URL)
public class TaskPriorityServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseResponse baseResponse = BaseResponse.getInstance(true, "priority successfully changed");
        resp.setContentType(ContentType.APPLICATION_JSON.toString());
        resp.setCharacterEncoding("UTF-8");
        try {
            TaskService taskService = ServiceFactory.getTaskService();
            PriorityPropertyChange taskPriority = RequestUtils.getRequestBody(req, PriorityPropertyChange.class);

            taskService.setPriority(taskPriority.getEntityId(),taskPriority.getValue());
            resp.setStatus(HttpServletResponse.SC_OK);

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
