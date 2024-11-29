package tms.webapp.taskboard.units.task;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.UpdateUnit;
import tms.webapp.taskboard.core.models.entities.task.TaskPriorityUpdate;
import tms.webapp.taskboard.core.models.entities.task.TaskStatusUpdate;
import tms.webapp.taskboard.units.base.DbExecutorUpdateUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TaskSetStatusUpdateUnitImpl extends DbExecutorUpdateUnit<TaskStatusUpdate>
        implements UpdateUnit<TaskStatusUpdate> {
    public TaskSetStatusUpdateUnitImpl (DbConnector connector) {
        super(connector);
    }

    @Override
    public int update(TaskStatusUpdate model) {
        return execute(model);
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, TaskStatusUpdate model) throws SQLException {
        String sql = "UPDATE %s SET status = ? WHERE id = ?;".formatted(DbConstants.DB_TABLE_TASK);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if(model.getStatus() != null) {
            preparedStatement.setInt(1, model.getStatus().getValue());
        } else {
            preparedStatement.setNull(1, Types.INTEGER);
        }
        preparedStatement.setString(2, String.valueOf(model.getTaskId()));
        return preparedStatement;
    }
}