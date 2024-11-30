package tms.webapp.taskboard.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.adapters.LocalDateTypeAdapter;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.ServletNameConstants;
import tms.webapp.taskboard.core.enums.ContentType;
import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;
import tms.webapp.taskboard.core.exceptions.CustomAuthorizationExcetion;
import tms.webapp.taskboard.core.interfaces.services.TaskService;
import tms.webapp.taskboard.core.models.datetime.DateRange;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.core.models.entities.task.TaskPredicate;
import tms.webapp.taskboard.core.models.response.PagedResponse;
import tms.webapp.taskboard.core.utils.JsonConverter;
import tms.webapp.taskboard.factories.ServiceFactory;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(name = ServletNameConstants.TODO_LIST, value = AppUrlConstants.TODOLIST_URL)
public class TodoListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TaskService taskService = ServiceFactory.getTaskService();
        TaskPredicate predicate = buildTaskPredicate(req);

        PagedResponse<Task> tasks = taskService.getPagedByPredicate(predicate);
        resp.setContentType(ContentType.APPLICATION_JSON.toString());
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter pw = resp.getWriter()) {
            String json = JsonConverter.serialize(tasks);
            pw.write(json);
        }
    }

    private TaskPredicate buildTaskPredicate(HttpServletRequest request) throws CustomAuthorizationExcetion {
        Optional<UUID> objId = Optional.ofNullable((UUID) request.getAttribute("userid"));

        UUID userId = objId.orElse(null);
        String search = request.getParameter("searchTerm");
        TaskPriority[] priorities = null;
        TaskStatus[] status = null;
        UUID[] ids = null;
        if (Optional.ofNullable(request.getParameterValues("priorities")).isPresent()) {
            priorities = Arrays.stream(request.getParameterValues("priorities"))
                    .map(TaskPriority::getTaskPriority).toArray(TaskPriority[]::new);
        }

        if (Optional.ofNullable(request.getParameterValues("statuses")).isPresent()) {
            status = Arrays.stream(request.getParameterValues("statuses"))
                    .map(TaskStatus::getTaskStatus).toArray(TaskStatus[]::new);
        }

        if (Optional.ofNullable(request.getParameterValues("ids")).isPresent()) {
            ids = Arrays.stream(request.getParameterValues("ids"))
                    .map(UUID::fromString).toArray(UUID[]::new);
        }

        LocalDate start = Optional.ofNullable(request.getParameter("start"))
                .map(d -> LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd"))).orElse(null);

        LocalDate end = Optional.ofNullable(request.getParameter("end"))
                .map(d -> LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd"))).orElse(null);

        DateRange range = null;

        if (start != null || end != null) {
            range = DateRange.getInstance(start, end);
        }

        Integer page = Optional.ofNullable(request.getParameter("page")).map(Integer::parseInt).orElse(0);
        Integer size = Optional.ofNullable(request.getParameter("size")).map(Integer::parseInt).orElse(20);

        return new TaskPredicate(ids, search, range, priorities, status, userId, page, size);
    }
}
