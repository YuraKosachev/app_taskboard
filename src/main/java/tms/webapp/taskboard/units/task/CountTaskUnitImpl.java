package tms.webapp.taskboard.units.task;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.enums.TaskStatus;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.FindUnit;
import tms.webapp.taskboard.core.models.entities.task.TaskCountRequest;
import tms.webapp.taskboard.units.base.DbExecutorQueryUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CountTaskUnitImpl
        extends DbExecutorQueryUnit<Map<TaskStatus, Integer>, TaskCountRequest>
        implements FindUnit<TaskCountRequest, Map<TaskStatus, Integer>>
{

    public CountTaskUnitImpl(DbConnector connector) {
        super(connector);
    }

    @Override
    public Map<TaskStatus, Integer> find(TaskCountRequest predicate) {
        return execute(predicate);
    }

    @Override
    protected Map<TaskStatus, Integer> mapper(ResultSet rs) {
        try {
            if (!rs.next()) {
                return null;
            }
            Map<TaskStatus, Integer> result = new HashMap<>();
            TaskStatus[] statuses = TaskStatus.values();

            for (TaskStatus status : statuses) {
                result.put(status, rs.getInt(status.getStatus()));
            }
            return result;

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, TaskCountRequest model) throws SQLException {

        final String PLACEHOLDER = "[STATUSES]";
        String sql = "SELECT %s LIMIT 1".formatted(PLACEHOLDER);

        TaskStatus[] statuses = TaskStatus.values();
        StringBuilder sqlBuilder = new StringBuilder();
        for (TaskStatus status : statuses) {
            if(!sqlBuilder.isEmpty())
                sqlBuilder.append(", ");
            sqlBuilder.append("(SELECT COUNT(*) FROM %s WHERE status = %d AND userId = '%s') as '%s'"
                    .formatted(DbConstants.DB_TABLE_TASK, status.getValue(),model.getUserId(), status.getStatus()));
        }

        sql = sql.replace(PLACEHOLDER, sqlBuilder.toString());

        return connection.prepareStatement(sql);
    }

}
