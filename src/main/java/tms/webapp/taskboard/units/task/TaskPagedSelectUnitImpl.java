package tms.webapp.taskboard.units.task;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.enums.TaskPriority;
import tms.webapp.taskboard.core.enums.TaskStatus;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.PagedSelectUnit;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.core.models.entities.task.TaskPredicate;
import tms.webapp.taskboard.core.models.response.PagedResponse;
import tms.webapp.taskboard.units.base.DbExecutorQueryUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskPagedSelectUnitImpl
        extends DbExecutorQueryUnit<PagedResponse<Task>, TaskPredicate>
        implements PagedSelectUnit<TaskPredicate, Task> {
    public TaskPagedSelectUnitImpl(DbConnector connector) {
        super(connector);
    }

    @Override
    public PagedResponse<Task> getPagedByPredicate(TaskPredicate predicate) {
        return execute(predicate);
    }
    @Override
    protected PagedResponse<Task> mapper(ResultSet rs) {
        try {
            List<Task> tasks = new ArrayList<>();
            Long rowCount= null;
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString(1));
                String title = rs.getString(2);
                String description = rs.getString(3);
                String descriptionHtml = rs.getString(4);
                TaskPriority priority = Optional.ofNullable(rs.getObject(5)).isPresent()
                        ? TaskPriority.getPriorityByValue(rs.getInt(5))
                        : null;
                TaskStatus status = Optional.ofNullable(rs.getObject(6)).isPresent()
                        ? TaskStatus.getTaskStatus(rs.getInt(6))
                        : null;
                LocalDate createdAt = rs.getDate(7).toLocalDate();

                if(Optional.ofNullable(rowCount).isEmpty())
                {
                    rowCount = rs.getLong(8);
                }

                tasks.add(new Task(id, title, createdAt, description, priority, status, descriptionHtml));
            }
            rowCount = Optional.ofNullable(rowCount).orElse(0L);
            return PagedResponse.getInstance(rowCount, tasks);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, TaskPredicate model) throws SQLException {

        final String PLACEHOLDER = "[COUNT_ELEMENTS]";
        String sql = "SELECT id, title, description, descriptionHtml, priority, status, createdAt%s FROM %s"
                .formatted(PLACEHOLDER, DbConstants.DB_TABLE_TASK);

        String pageRequestSql = "SELECT count(*) FROM %s".formatted(DbConstants.DB_TABLE_TASK);

        if (Optional.ofNullable(model).isPresent()) {
            StringBuilder sqlBuilder = new StringBuilder();

            if (model.getUuids() != null && model.getUuids().length > 0) {
                List<String> ids = Arrays.stream(model.getUuids()).map("'%s'"::formatted).toList();
                sqlBuilder.append("id IN (%s)".formatted(String.join(",", ids)));
            }

            if (model.getUserId() != null) {
                if (!sqlBuilder.isEmpty()) {
                    sqlBuilder.append(" AND ");
                }
                sqlBuilder.append("userId='%s'".formatted(String.valueOf(model.getUserId())));
            }

            if (model.getPriorities() != null && model.getPriorities().length > 0) {
                if (!sqlBuilder.isEmpty()) {
                    sqlBuilder.append(" AND ");
                }
                List<String> priorities = Arrays.stream(model.getPriorities()).map(x-> String.valueOf(x.getValue())).toList();
                sqlBuilder.append("priority IN (%s)".formatted(String.join(",", priorities)));
            }

            if (model.getStatuses() != null && model.getStatuses().length > 0) {
                if (!sqlBuilder.isEmpty()) {
                    sqlBuilder.append(" AND ");
                }
                List<String> statuses = Arrays.stream(model.getStatuses()).map(x-> String.valueOf(x.getValue())).toList();
                sqlBuilder.append("status IN (%s)".formatted(String.join(",", statuses)));
            }

            if (Optional.ofNullable(model.getSearchTerm()).isPresent()
                    && !model.getSearchTerm().isBlank()) {
                if (!sqlBuilder.isEmpty()) {
                    sqlBuilder.append(" AND ");
                }
                sqlBuilder.append("(title LIKE '%")
                        .append(model.getSearchTerm())
                        .append("%' OR description LIKE '%")
                        .append(model.getSearchTerm()).append("%')");
            }

            if (model.getDateRange() != null) {
                if (!sqlBuilder.isEmpty()) {
                    sqlBuilder.append(" AND ");
                }

                if(model.getDateRange().getEnd() != null && model.getDateRange().getStart() != null)
                {
                    String start = model.getDateRange().getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String end = model.getDateRange().getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    sqlBuilder.append("createdAt BETWEEN '%s' AND '%s'".formatted(start, end));
                } else if (model.getDateRange().getEnd() != null) {
                    String end = model.getDateRange().getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    sqlBuilder.append("createdAt < '%s'".formatted(end));
                } else{
                    String start = model.getDateRange().getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    sqlBuilder.append("createdAt < '%s'".formatted(start));
                }
            }

            if (!sqlBuilder.isEmpty()) {
                sql = "%s WHERE %s".formatted(sql, sqlBuilder.toString());
                pageRequestSql  = "%s WHERE %s".formatted(pageRequestSql, sqlBuilder.toString());
            }

            sql = sql.replace(PLACEHOLDER, ",(%s) as row_count".formatted(pageRequestSql));

            if(model.getPage() != null && model.getSize() != null) {
                Integer offset = model.getPage() * model.getSize();
                sql = "%s LIMIT %d OFFSET %d".formatted(sql, model.getSize(), offset);
            }
        }

        return connection.prepareStatement(sql);
    }

}
