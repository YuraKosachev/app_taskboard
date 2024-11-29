package tms.webapp.taskboard.units.task;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.UpdateUnit;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.core.models.entities.task.TaskPriorityUpdate;
import tms.webapp.taskboard.units.base.DbExecutorUpdateUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TaskSetPriorityUpdateUnitImpl extends DbExecutorUpdateUnit<TaskPriorityUpdate>
        implements UpdateUnit<TaskPriorityUpdate> {
    public TaskSetPriorityUpdateUnitImpl (DbConnector connector) {
        super(connector);
    }

    @Override
    public int update(TaskPriorityUpdate model) {
        return execute(model);
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, TaskPriorityUpdate model) throws SQLException {
        String sql = "UPDATE %s SET priority = ? WHERE id = ?;".formatted(DbConstants.DB_TABLE_TASK);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if(model.getPriority() != null) {
            preparedStatement.setInt(1, model.getPriority().getValue());
        } else {
            preparedStatement.setNull(1, Types.INTEGER);
        }

        preparedStatement.setString(2, String.valueOf(model.getTaskId()));
        return preparedStatement;
    }
}
