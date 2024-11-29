package tms.webapp.taskboard.units.task;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.UpdateUnit;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.units.base.DbExecutorUpdateUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TaskUpdateUnitImpl
        extends DbExecutorUpdateUnit<Task>
        implements UpdateUnit<Task> {
    public TaskUpdateUnitImpl(DbConnector connector) {
        super(connector);
    }

    @Override
    public int update(Task model) {
        return execute(model);
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, Task model) throws SQLException {
        String sql = "UPDATE %s SET title = ? ,description = ? ,descriptionHtml = ? ,priority = ?,status = ? WHERE id = ?;"
                .formatted(DbConstants.DB_TABLE_TASK);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, model.getTitle());
        if (model.getDescription() != null)
            preparedStatement.setString(2, model.getDescription());
        else
            preparedStatement.setNull(2, Types.NVARCHAR);

        if (model.getDescriptionHtml() != null)
            preparedStatement.setString(3, model.getDescriptionHtml());
        else
            preparedStatement.setNull(3, Types.NVARCHAR);

        if(model.getPriority() != null)
            preparedStatement.setInt(4, model.getPriority().getValue());
        else
            preparedStatement.setNull(4, Types.INTEGER);

        if(model.getStatus() != null)
            preparedStatement.setInt(5, model.getStatus().getValue());
        else
            preparedStatement.setNull(5, Types.INTEGER);

        preparedStatement.setString(6, String.valueOf(model.getId()));
        return preparedStatement;
    }
}
