package tms.webapp.taskboard.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.ServletNameConstants;
import tms.webapp.taskboard.core.enums.ContentType;
import tms.webapp.taskboard.core.interfaces.services.TaskService;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.core.models.entities.task.TaskCreate;
import tms.webapp.taskboard.core.models.response.BaseResponse;
import tms.webapp.taskboard.core.utils.JsonConverter;
import tms.webapp.taskboard.core.utils.RequestUtils;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.UUID;

import static tms.webapp.taskboard.core.utils.RequestUtils.getRequestBody;

@WebServlet(name = ServletNameConstants.TASK, value = AppUrlConstants.TASK_URL)
public class TaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseResponse boolResponse = BaseResponse.getInstance(true, "successfully added");
        resp.setContentType(ContentType.APPLICATION_JSON.toString());
        resp.setCharacterEncoding("UTF-8");
        try {
            TaskService taskService = ServiceFactory.getTaskService();
            TaskCreate model = buildCreateTask(req);
            taskService.create(model);
            resp.setStatus(200);

        } catch (Exception e) {
            resp.setStatus(400);
            boolResponse = BaseResponse.getInstance(false, e.getMessage());
        } finally {
            try (PrintWriter pw = resp.getWriter()) {
                String json = JsonConverter.serialize(boolResponse);
                pw.write(json);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseResponse boolResponse = BaseResponse.getInstance(true, "successfully deleted");
        resp.setContentType(ContentType.APPLICATION_JSON.toString());
        resp.setCharacterEncoding("UTF-8");
        try {
            UUID uuid = UUID.fromString(req.getParameter("id"));
            TaskService taskService = ServiceFactory.getTaskService();

            taskService.delete(uuid);
            resp.setStatus(200);

        } catch (Exception e) {
            resp.setStatus(400);
            boolResponse = BaseResponse.getInstance(false, e.getMessage());
        } finally {
            try (PrintWriter pw = resp.getWriter()) {
                String json = JsonConverter.serialize(boolResponse);
                pw.write(json);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseResponse boolResponse = BaseResponse.getInstance(true, "successfully updated");
        resp.setContentType(ContentType.APPLICATION_JSON.toString());
        resp.setCharacterEncoding("UTF-8");
        try {
            TaskService taskService = ServiceFactory.getTaskService();
            Task model = buildUpdateTask(req);
            taskService.update(model);
            resp.setStatus(200);
        } catch (Exception e) {
            resp.setStatus(400);
            boolResponse = BaseResponse.getInstance(false, e.getMessage());
        } finally {
            try (PrintWriter pw = resp.getWriter()) {
                String json = JsonConverter.serialize(boolResponse);
                pw.write(json);
            }
        }
    }

    private Task buildUpdateTask(HttpServletRequest req) throws IOException {
        return RequestUtils.getRequestBody(req, Task.class);
    }


    private TaskCreate buildCreateTask(HttpServletRequest req) throws IOException {
        Optional<UUID> objId = Optional.ofNullable((UUID) req.getAttribute("userid"));
        TaskCreate taskCreate = RequestUtils.getRequestBody(req, TaskCreate.class);
        taskCreate.setUserId(objId.orElse(null));
        return taskCreate;
    }
}
